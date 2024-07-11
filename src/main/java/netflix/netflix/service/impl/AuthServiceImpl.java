package netflix.netflix.service.impl;

import lombok.RequiredArgsConstructor;
import netflix.netflix.dto.request.LoginRequest;
import netflix.netflix.dto.request.RegisterRequest;
import netflix.netflix.dto.response.LoginResponse;
import netflix.netflix.dto.response.RegisterResponse;
import netflix.netflix.entity.UserAccount;
import netflix.netflix.repository.UserAccountRepository;
import netflix.netflix.service.AuthService;
import netflix.netflix.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAccountRepository userAccountRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        UserAccount account = UserAccount.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        userAccountRepository.saveAndFlush(account);

        return RegisterResponse.builder()
                .name(account.getName())
                .email(account.getEmail())
                .username(account.getUsername())
                .build();

    }

    @Transactional(readOnly = true)
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
