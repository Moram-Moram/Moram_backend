package radiantMoramMoram.MoramMoram.payload.response.comment;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CommentListResponse {
    private List<CommentResponse> comments;
}
