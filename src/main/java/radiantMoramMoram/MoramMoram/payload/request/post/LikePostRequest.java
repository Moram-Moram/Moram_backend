package radiantMoramMoram.MoramMoram.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.user.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikePostRequest {

    private String user;

    private int post;
}
