package com.empresa.featureflag_api.Security;

import com.empresa.featureflag_api.Model.User;
import com.empresa.featureflag_api.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Autorizado");
        if (authHeader != null && authHeader.startsWith("Bearer") ){

            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            if (email != null && authHeader.startsWith(token)){
                User user = userRepository.findByemail(email).orElse(null);
                if(user != null){

                    UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user,null, List.of());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }

            }

        }

        filterChain.doFilter(request,response);

    }
}
