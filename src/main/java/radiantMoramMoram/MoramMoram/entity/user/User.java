package radiantMoramMoram.MoramMoram.entity.user;

import lombok.Builder;
import lombok.Getter;
import radiantMoramMoram.MoramMoram.entity.role.Role;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.security.auth.AuthorityAttributeConverter;

import javax.persistence.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 루트 엔티티 (애그리거트)
@Getter
@Table(name = "tbl_user")
@Entity
public class User {
    @Id
    private String id;
    private String password;
    private String name;
    private boolean whiteCheck;
    @Enumerated(EnumType.ORDINAL)
    private Authority role = Authority.ROLE_WATER_DROP;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    public User() {

    }

    User(String id, String password, String name, boolean whiteCheck) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.whiteCheck = whiteCheck;
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
