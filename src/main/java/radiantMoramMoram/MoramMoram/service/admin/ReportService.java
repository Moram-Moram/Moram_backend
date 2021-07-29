package radiantMoramMoram.MoramMoram.service.admin;

import org.springframework.data.domain.Pageable;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;

import java.util.List;

public interface ReportService {

    List<ReportPostResponse> getReportList();

    ReportPostResponse getReportPost(int postId);

    void deletePost(int postId);

}
