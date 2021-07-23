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
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    private int id;

    @JoinColumn(name = "post_id")
    @ManyToOne
    private Post post;

    @Column(name = "path")
    private String fileName;

}
