package netflix.netflix.service;

import netflix.netflix.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
}
