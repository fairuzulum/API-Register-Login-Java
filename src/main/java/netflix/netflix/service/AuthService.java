package netflix.netflix.service;

import netflix.netflix.dto.request.LoginRequest;
import netflix.netflix.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
