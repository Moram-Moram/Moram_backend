package radiantMoramMoram.MoramMoram.entity.post.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.entity.post.Post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_image")
@Getter
@Builder
@IdClass(ImageKey.class)
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    private int id;

    @ManyToOne
    private Post post;

    @Column(name = "path")
    private String fileName;

}
