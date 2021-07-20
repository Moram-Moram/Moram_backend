package radiantMoramMoram.MoramMoram.payload.response.post;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Builder
public class PostsResponse {
    String category;
    List<PostListResponse> posts;
}
