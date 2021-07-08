package radiantMoramMoram.MoramMoram.domain.post.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.domain.post.Post;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "tbl_category")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @ManyToOne
    private Post post;

    @Id
    private CategoryEnum category;

}
