package radiantMoramMoram.MoramMoram.exception;

import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;

public class UserNotFoundException extends BasicException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }

}
