package radiantMoramMoram.MoramMoram.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query(value = "select * from moram_moram.tbl_post order by rand() limit 1",nativeQuery = true)
    Post findRandomPost();

    List<Post> findByUser(User user);

}
