package radiantMoramMoram.MoramMoram.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_REQUEST(400, "Bad Request"),
    INVALID_TOKEN(401,"Invalid Token"),
    EXPIRED_TOKEN(401,"Expired Token"),
    USER_NOT_FOUND(404,"User Not Found"),
    POST_NOT_FOUND(404, "Post Not Found"),
    USER_ALREADY_EXISTS(409,"User Already Exists"),
    IMAGE_NOT_FOUND(404, "image not found");

    private final int status;

    private final String message;
}
