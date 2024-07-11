package netflix.netflix.entity;

import lombok.*;
import netflix.netflix.constant.ConstantTable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.GenericGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ConstantTable.USER_ACCOUNT)
public class UserAccount{
    @Id
    @GeneratedValue(generator = "user-id")
    @GenericGenerator(name = "user-id", strategy = "netflix.netflix.utils.UserIdGenerator")
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;






}
