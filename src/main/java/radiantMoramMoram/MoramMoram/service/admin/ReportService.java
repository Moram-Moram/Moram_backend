package radiantMoramMoram.MoramMoram.service.admin;

import org.springframework.data.domain.Pageable;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportListResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportResponse;

import java.util.List;

public interface ReportService {

    ReportListResponse getReportList(Pageable pageable);

    ReportPostResponse getReportPost(int id);

    void deletePost(int postId);

}
