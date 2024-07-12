package netflix.netflix.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAccountRequest {
    private String id;
    private String name;
    private String email;
    private String username;
    private String password;

}
