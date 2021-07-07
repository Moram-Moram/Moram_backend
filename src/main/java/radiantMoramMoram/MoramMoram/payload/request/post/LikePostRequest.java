package radiantMoramMoram.MoramMoram.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.domain.post.Post;
import radiantMoramMoram.MoramMoram.domain.user.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikePostRequest {

    private User user;

    private Post post;
}
