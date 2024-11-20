package hoop.api.api.infra.security;

import hoop.api.api.domain.user.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String tokenJWT = getTokenPerRequest(request);

        if(tokenJWT != null){
            String subjectEmail = tokenService.getSubject(tokenJWT);
            var userOptional = userRepository.findByEmail(subjectEmail);

            if(userOptional.isPresent()){
                var user = userOptional.get();
                var role = tokenService.getUserRole(tokenJWT);
                var authorites = List.of(new SimpleGrantedAuthority(role));
                var auth = new UsernamePasswordAuthenticationToken(user,null,authorites);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }


    private String getTokenPerRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader !=null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
