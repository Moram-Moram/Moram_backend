package radiantMoramMoram.MoramMoram.repository.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import radiantMoramMoram.MoramMoram.entity.admin.Admin;

@Repository
public interface AdminRepositpry extends CrudRepository<Admin, String> {

}
