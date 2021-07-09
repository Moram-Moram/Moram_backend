package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryKey;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, CategoryKey> {

    List<Category> findByPostIdOrderById(Integer postId);

}
