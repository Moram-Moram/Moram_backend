package radiantMoramMoram.MoramMoram.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.service.admin.ReportService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public List<ReportPostResponse> getReportList() {
        return reportService.getReportList();
    }

    @GetMapping("/postId")
    public ReportPostResponse getReportPost(@PathVariable int postId) {
        return reportService.getReportPost(postId);
    }

    @DeleteMapping("delete/{postId}")
    public void deletePost(@PathVariable int postId){
        reportService.deletePost(postId);
    }


}
