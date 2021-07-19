package radiantMoramMoram.MoramMoram.exception;

public class InvalidTokenException extends BasicException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
