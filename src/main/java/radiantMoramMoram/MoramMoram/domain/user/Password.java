package radiantMoramMoram.MoramMoram.domain.user;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Embeddable
@Getter
public class Password {
    private String password;

    public Password(String password){
        this.password = pwEncrypt(password);
        //this.password = password;
    }

    protected Password() {
    }

    // 암호화
    private String pwEncrypt(String pw){
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
            System.out.println("비밀번호 암호화 실패");
        }
        return "";
    }
}
