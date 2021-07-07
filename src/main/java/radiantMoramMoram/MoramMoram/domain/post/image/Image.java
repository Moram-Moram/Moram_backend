package radiantMoramMoram.MoramMoram.domain.post.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.domain.post.Post;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_image")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Image {

    @Id
    @ManyToOne
    private Post post;

    @Id
    private String fileName;

}
