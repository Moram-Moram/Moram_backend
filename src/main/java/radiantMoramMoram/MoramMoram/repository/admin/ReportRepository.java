package radiantMoramMoram.MoramMoram.repository.admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import radiantMoramMoram.MoramMoram.entity.post.Post;

public interface ReportRepository extends CrudRepository<Post, Integer> {

    Page<Post> findAllBy(Pageable pageable);

}
