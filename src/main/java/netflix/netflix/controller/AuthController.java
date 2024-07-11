package netflix.netflix.controller;

import lombok.RequiredArgsConstructor;
import netflix.netflix.constant.APIUrl;
import netflix.netflix.dto.request.LoginRequest;
import netflix.netflix.dto.response.CommonResponse;
import netflix.netflix.dto.response.LoginResponse;
import netflix.netflix.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = APIUrl.AUTH_API)
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<CommonResponse<?>> login(@RequestBody LoginRequest request)
    {
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("login successfully")
                .data(loginResponse)
                .build();

        return ResponseEntity.ok(response);
    }
}
