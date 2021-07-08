package radiantMoramMoram.MoramMoram.domain.user;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Table(name = "tbl_user")
@Entity
public class User {
    @Id
    private String id;
    private String password;
    private String name;

    public User() {

    }

    User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    static public String pwEncrypt(String pw){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes());
            StringBuilder bilder = new StringBuilder();
            for(byte b: md.digest()){
                bilder.append(String.format("%02x",b));
            }
            return bilder.toString();
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            System.out.println("Password encryption failed");
        }
        return "";
    }
}
