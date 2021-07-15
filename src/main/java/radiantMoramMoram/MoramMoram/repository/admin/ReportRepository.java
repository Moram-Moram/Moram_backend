package radiantMoramMoram.MoramMoram.repository.admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.Post;

@Repository
public interface ReportRepository extends CrudRepository<Post, Integer> {

    Page<Post> findAllBy(Pageable pageable);

}
