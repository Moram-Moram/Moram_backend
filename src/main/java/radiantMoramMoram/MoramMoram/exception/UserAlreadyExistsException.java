package radiantMoramMoram.MoramMoram.exception;

import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;

public class UserAlreadyExistsException extends BasicException {

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
