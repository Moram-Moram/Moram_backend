package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryKey;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, CategoryKey> {

    @Query(value = "select c.post_id from moram_moram.tbl_category c where c.name = :category", nativeQuery = true)
    List<Integer> categoryPostListReturn(String category);

    Optional<Post> findByPostId(Integer postId);

    void deleteByPostId(Integer postId);
}
