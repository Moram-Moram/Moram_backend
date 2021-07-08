package radiantMoramMoram.MoramMoram.domain.post.like;

import lombok.Builder;
import org.springframework.boot.SpringApplicationExtensionsKt;

import java.io.Serializable;

@Builder
public class LikePostKey implements Serializable {

    private int postId;

    private String userId;

}
