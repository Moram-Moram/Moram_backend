package radiantMoramMoram.MoramMoram.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenErrorCode {
    INVALID_SIGNATURE(401, "Invalid signature"),
    TOKEN_EXPIRED(401, "Token expired"),
    UNSUPPORTED_TOKEN(401, "Unsupported token"),
    INVALID_TOKEN(401,"Invalid Token");

    private final int status;

    private final String message;
}
