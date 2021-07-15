package radiantMoramMoram.MoramMoram.service.admin;

import org.springframework.data.domain.Pageable;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportListResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;

public interface ReportService {

    ReportListResponse getReportList(Pageable pageable);

    ReportPostResponse getReportPost(int id);

}
