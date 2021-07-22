package radiantMoramMoram.MoramMoram.payload.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class MyPagePostResponse {

    private final int id;

    private final String title;

    private final String writer;

    private final String content;

    private final LocalDate date;

    private final List<String> image;

}
