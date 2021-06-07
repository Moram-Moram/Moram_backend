package radiantMoramMoram.MoramMoram.domain.user;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "tbl_user")
@Entity
public class User {
    @Id
    private String id;
    // private String password;
    @Embedded
    private Password password;
    private String name;

    public User(String id, Password pw, String name){
        this.id = id;
        this.password = pw;
        this.name = name;
    }

    public User() {

    }
}
