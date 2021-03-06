package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryKey;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, CategoryKey> {

    @Query(value = "select c.post_id from moram_moram.tbl_category c where c.name = :category", nativeQuery = true)
    List<Integer> categoryPostListReturn(String category);

    @Transactional
    void deleteByPostId(int postId);

}
