package radiantMoramMoram.MoramMoram.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportListResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.service.admin.ReportService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/Report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ReportListResponse getReportList(Pageable pageable) {

        return reportService.getReportList(pageable);

    }

    @GetMapping("/postId")
    public ReportPostResponse getReportPost(int postId) {

        return reportService.getReportPost(postId);

    }

}
