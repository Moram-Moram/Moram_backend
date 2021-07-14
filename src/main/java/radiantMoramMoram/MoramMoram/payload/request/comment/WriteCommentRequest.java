package radiantMoramMoram.MoramMoram.payload.request.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WriteCommentRequest {
    private String content;
}
