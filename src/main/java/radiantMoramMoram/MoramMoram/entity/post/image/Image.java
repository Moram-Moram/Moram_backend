package radiantMoramMoram.MoramMoram.entity.post.image;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.entity.post.Post;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_image")
@Getter
@Builder
@NoArgsConstructor
public class Image {

    @Id
    private int id;

    @ManyToOne
    private Post post;

    private String fileName;

}
