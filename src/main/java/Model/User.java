package Model;


import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Builder
@Data
public class User {

    private String email;
    private String hashPassword;
    private Character sex;
    private Date birthday;
    private String country;

}
