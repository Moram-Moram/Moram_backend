package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryKey;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, CategoryKey> {
}
