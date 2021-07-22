package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select * from moram_moram.tbl_post order by rand() limit 1",nativeQuery = true)
    Post findRandomPost();
}
