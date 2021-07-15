package radiantMoramMoram.MoramMoram.exception;

import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;

public class InvalidTokenException extends BasicException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
