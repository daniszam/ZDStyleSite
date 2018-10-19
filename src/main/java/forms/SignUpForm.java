package forms;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpForm {

    private String email;
    private String password;
    private String rePassword;
    private Character sex;
    private Date birthday;
    private String country;
}
