package radiantMoramMoram.MoramMoram.entity.post.like;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class LikePostKey implements Serializable {

    private int postId;

    private String userId;

}
