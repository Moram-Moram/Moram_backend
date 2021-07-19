package radiantMoramMoram.MoramMoram.exception;

public class UserNotFoundException extends BasicException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
