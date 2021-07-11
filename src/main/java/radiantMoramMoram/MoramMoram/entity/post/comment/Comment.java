package radiantMoramMoram.MoramMoram.entity.post.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.user.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Table(name = "tbl_comment")
@Entity
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post post;


    @CreationTimestamp
    @Column(name = "reg_date")
    private Timestamp regDate;

    @Builder
    Comment(User user, Post post, String content){
        this.user = user;
        this.post = post;
        this.content = content;
    }

}
