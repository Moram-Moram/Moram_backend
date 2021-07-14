package radiantMoramMoram.MoramMoram.exception;


import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;

public class PostNotFoundException extends BasicException {

    public PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }

}
