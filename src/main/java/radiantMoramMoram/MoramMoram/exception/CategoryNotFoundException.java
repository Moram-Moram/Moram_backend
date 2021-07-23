package radiantMoramMoram.MoramMoram.exception;

import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;

public class CategoryNotFoundException extends BasicException {

    public CategoryNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }

}
