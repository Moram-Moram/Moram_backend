package radiantMoramMoram.MoramMoram.domain.user;

import com.sun.istack.Builder;

public class UserBuilder {
    private String id;
    private String password;
    private String name;

    public UserBuilder setId(String id){
        this.id = id;
        return this;
    }
    public UserBuilder setPassword(String password){
        this.password = User.pwEncrypt(password);
        return this;
    }
    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public User build(){
        User user = new User(id, password, name);
        return user;
    }
}

