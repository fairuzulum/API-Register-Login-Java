package netflix.netflix.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import netflix.netflix.dto.response.JwtClaims;
import netflix.netflix.service.JwtService;
import netflix.netflix.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {
    final String AUTH_HEADER = "Authorization";
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader(AUTH_HEADER);

            if (bearerToken != null && jwtService.verifyJwtToken(bearerToken)){
                JwtClaims decodeJwt = jwtService.getClaimsByToken(bearerToken);

            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
