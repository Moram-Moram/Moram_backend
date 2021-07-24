package radiantMoramMoram.MoramMoram.entity.post.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikePostKey implements Serializable {

    private int post;

    private String user;

}
