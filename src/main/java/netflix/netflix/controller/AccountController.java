package netflix.netflix.controller;

import lombok.RequiredArgsConstructor;
import netflix.netflix.constant.APIUrl;
import netflix.netflix.dto.request.UpdateAccountRequest;
import netflix.netflix.dto.response.AccountResponse;
import netflix.netflix.dto.response.CommonResponse;
import netflix.netflix.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = APIUrl.ACCOUNT_API)
public class AccountController {
    final UserService userService;

    @PutMapping
    public ResponseEntity<CommonResponse<AccountResponse>> updateAccount(@RequestBody UpdateAccountRequest request) {
        AccountResponse update = userService.updateAccount(request);
        CommonResponse<AccountResponse> response = CommonResponse.<AccountResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Account updated")
                .data(update)
                .build();
        return ResponseEntity.ok().body(response);
    }
}
