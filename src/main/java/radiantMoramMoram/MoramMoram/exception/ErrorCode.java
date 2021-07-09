package radiantMoramMoram.MoramMoram.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(400, "Bad Request"),
    INVALID_TOKEN(401,"Invalid Token"),
    USER_NOT_FOUND(404,"User Not Found");

    private final int status;

    private final String message;
}
