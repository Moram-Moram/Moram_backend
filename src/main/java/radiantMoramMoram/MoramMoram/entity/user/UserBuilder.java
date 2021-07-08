package radiantMoramMoram.MoramMoram.entity.user;

public class UserBuilder {
    private String id;
    private String password;
    private String nickname;
    private boolean whiteCheck;

    public UserBuilder setId(String id){
        this.id = id;
        return this;
    }
    public UserBuilder setPassword(String password){
        this.password = User.pwEncrypt(password);
        return this;
    }
    public UserBuilder setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public UserBuilder setWhiteCheck(boolean whiteCheck){
        this.whiteCheck = whiteCheck;
        return this;
    }

    public User build(){
        User user = new User(id, password, nickname, whiteCheck);
        return user;
    }
}

