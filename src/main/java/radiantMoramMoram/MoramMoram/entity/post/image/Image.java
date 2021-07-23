package radiantMoramMoram.MoramMoram.entity.post.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.entity.post.Post;

import javax.persistence.*;

@Entity(name = "tbl_image")
@Getter
@Builder
@IdClass(ImageKey.class)
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    private int id;

    @Id
    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    private String path;

}
