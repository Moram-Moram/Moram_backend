package radiantMoramMoram.MoramMoram.payload.response.post;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@Builder
public class PostListResponse {

    private Integer postId;

    private String title;

    private String content;

    private String writer;

    private String image;

    private LocalDate date;

}
