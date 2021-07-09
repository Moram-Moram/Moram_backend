package radiantMoramMoram.MoramMoram.entity.post.category;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class CategoryKey implements Serializable {

    private int postId;

    private Category category;

}