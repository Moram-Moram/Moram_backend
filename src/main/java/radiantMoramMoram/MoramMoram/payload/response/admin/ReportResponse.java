package radiantMoramMoram.MoramMoram.payload.response.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ReportResponse {

    private final int id;

    private final String title;

    private final String writer;

    private final LocalDate date;

    private final List<String> image;

}
