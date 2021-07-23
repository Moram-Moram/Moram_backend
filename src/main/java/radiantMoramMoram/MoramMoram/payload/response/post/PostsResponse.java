package radiantMoramMoram.MoramMoram.payload.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostsResponse {

    private String category;

    private List<PostListResponse> posts;

}
