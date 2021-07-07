package radiantMoramMoram.MoramMoram.domain.user;

import lombok.Getter;

@Getter
public class UserDTO {
    private String id;
    private String pw;

    public UserDTO(String id, String pw){
        this.id = id;
        this.pw = pw;
    }
}
