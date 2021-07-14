package radiantMoramMoram.MoramMoram.payload.response.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ReportListResponse {

    private final int totalPages;
    private final List<ReportResponse> reportResponses;

}
