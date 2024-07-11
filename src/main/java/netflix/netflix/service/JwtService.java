package netflix.netflix.service;

import netflix.netflix.dto.response.JwtClaims;
import netflix.netflix.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    boolean verifyJwtToken(String token);
    JwtClaims getClaimsByToken(String token);

}
