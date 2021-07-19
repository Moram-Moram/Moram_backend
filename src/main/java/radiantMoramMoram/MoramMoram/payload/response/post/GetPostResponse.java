package radiantMoramMoram.MoramMoram.payload.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponse {

    private Integer postId;

    private String title;

    private String content;

    private String writer;

    private LocalDate date;

    private List<String> image;

    private List<String> category;

}
