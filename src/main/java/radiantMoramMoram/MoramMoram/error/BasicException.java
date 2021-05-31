package radiantMoramMoram.MoramMoram.error;

import lombok.Getter;

@Getter
public class BasicException extends RuntimeException{

    private final ErrorCode errorCode;

    public BasicException(ErrorCode errorCode, ErrorCode errorCode1) {

        super(errorCode.getMessage());

        this.errorCode = errorCode1;

    }

}
