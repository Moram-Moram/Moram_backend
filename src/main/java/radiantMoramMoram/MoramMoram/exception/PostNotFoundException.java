package radiantMoramMoram.MoramMoram.exception;


public class PostNotFoundException extends BasicException {

    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }

}
