package radiantMoramMoram.MoramMoram.payload.request.comment;

import lombok.Getter;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.user.User;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class WriteCommentRequest {
    private String content;
}
