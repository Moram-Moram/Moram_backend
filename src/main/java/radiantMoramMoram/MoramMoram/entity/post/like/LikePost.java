package radiantMoramMoram.MoramMoram.entity.post.like;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name = "tbl_like")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LikePost {

    @Id
    @OneToOne
    private Post post;

    @Id
    @OneToOne
    private User user;
}
