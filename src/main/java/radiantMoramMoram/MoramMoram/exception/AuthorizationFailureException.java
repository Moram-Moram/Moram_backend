package radiantMoramMoram.MoramMoram.exception;

import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;

public class AuthorizationFailureException extends BasicException {

    public AuthorizationFailureException() {
        super(ErrorCode.AUTHORIZATION_FAILURE);
    }
}
