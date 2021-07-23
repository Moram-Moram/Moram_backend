package radiantMoramMoram.MoramMoram.repository.admin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.post.Post;

import java.util.List;

@Repository
public interface ReportRepository extends CrudRepository<Post, Integer> {

    List<Post> findAllBy();

}
