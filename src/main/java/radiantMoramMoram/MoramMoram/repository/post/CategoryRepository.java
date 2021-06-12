package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.domain.post.category.Category;
import radiantMoramMoram.MoramMoram.domain.post.category.CategoryKey;

public interface CategoryRepository extends JpaRepository<Category, CategoryKey> {

}
