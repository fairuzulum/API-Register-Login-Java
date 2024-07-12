package netflix.netflix.service;

import netflix.netflix.dto.request.UpdateAccountRequest;
import netflix.netflix.dto.response.AccountResponse;
import netflix.netflix.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends UserDetailsService {
    UserAccount getByUserId(String userId);
    AccountResponse updateAccount(UpdateAccountRequest updateAccountRequest);
    UserAccount getByContext();
}
