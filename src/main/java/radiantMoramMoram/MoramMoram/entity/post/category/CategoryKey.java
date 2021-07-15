package radiantMoramMoram.MoramMoram.entity.post.category;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class CategoryKey implements Serializable {

    private int post;

    private Category category;

}