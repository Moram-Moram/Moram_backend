package radiantMoramMoram.MoramMoram.payload.request.comment;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WriteCommentRequest {
    private String content;
}
