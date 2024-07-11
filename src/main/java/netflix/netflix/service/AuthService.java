package netflix.netflix.service;

import netflix.netflix.dto.request.LoginRequest;
import netflix.netflix.dto.request.RegisterRequest;
import netflix.netflix.dto.response.LoginResponse;
import netflix.netflix.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest request);
}
