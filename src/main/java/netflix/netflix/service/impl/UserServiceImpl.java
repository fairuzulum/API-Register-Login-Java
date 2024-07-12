package netflix.netflix.service.impl;

import lombok.RequiredArgsConstructor;
import netflix.netflix.dto.request.UpdateAccountRequest;
import netflix.netflix.dto.response.AccountResponse;
import netflix.netflix.entity.UserAccount;
import netflix.netflix.repository.UserAccountRepository;
import netflix.netflix.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserAccount getByUserId(String userId) {
        return userAccountRepository.findById(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
        );
    }

    @Override
    public AccountResponse updateAccount(UpdateAccountRequest updateAccountRequest) {
        UserAccount update = UserAccount.builder()
                .id(updateAccountRequest.getId())
                .name(updateAccountRequest.getName())
                .email(updateAccountRequest.getEmail())
                .username(updateAccountRequest.getUsername())
                .password(updateAccountRequest.getPassword())
                .build();
        userAccountRepository.saveAndFlush(update);

        return  AccountResponse.builder()
                .name(update.getName())
                .email(update.getEmail())
                .username(update.getUsername())
                .build();
    }

    @Override
    public UserAccount getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userAccountRepository.findByUsername(authentication.getPrincipal().toString()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found from contex")
        );
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
    }
}
