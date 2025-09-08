package com.empresa.featureflag_api.Service;

import com.empresa.featureflag_api.Dto.AuthResponse;
import com.empresa.featureflag_api.Dto.LoginRequest;
import com.empresa.featureflag_api.Dto.RegisterRequest;
import com.empresa.featureflag_api.Model.Role;
import com.empresa.featureflag_api.Model.User;
import com.empresa.featureflag_api.Repository.UserRepository;
import com.empresa.featureflag_api.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository ;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request){
        if(userRepository.existsByusername(request.getUsername())){
            throw new RuntimeException("El nombre de Usuario ya existe");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.User);

        userRepository.save(user);

    }

    public AuthResponse Login(LoginRequest request){

        User user = userRepository.findByemail(request.getEmail())
                .orElseThrow(()->new RuntimeException("El Usuario no a sido encontrado"));

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new RuntimeException("Contraseña incorrecta");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);

    }

}
