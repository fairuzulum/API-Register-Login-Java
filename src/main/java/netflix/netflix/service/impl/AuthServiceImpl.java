package netflix.netflix.service.impl;

import lombok.RequiredArgsConstructor;
import netflix.netflix.dto.request.LoginRequest;
import netflix.netflix.dto.response.LoginResponse;
import netflix.netflix.entity.UserAccount;
import netflix.netflix.repository.UserAccountRepository;
import netflix.netflix.service.AuthService;
import netflix.netflix.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 1. Daftar
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        // 2. Pengecekan data yang sebelumnya sudah valid

        Authentication authenticated = authenticationManager.authenticate(authentication);

        UserAccount userAccount = (UserAccount) authenticated.getPrincipal();
        String token = jwtService.generateToken(userAccount);
        return LoginResponse.builder()
                .token(token)
                .username(userAccount.getUsername())
                .build();
    }
}
