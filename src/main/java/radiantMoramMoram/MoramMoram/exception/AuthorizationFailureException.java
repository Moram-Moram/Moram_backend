package radiantMoramMoram.MoramMoram.exception;

public class AuthorizationFailureException extends BasicException {

    public AuthorizationFailureException() {
        super(ErrorCode.AUTHORIZATION_FAILURE);
    }
}
