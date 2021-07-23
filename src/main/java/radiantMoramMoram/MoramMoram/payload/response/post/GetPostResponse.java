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

    private String user;

    private LocalDate date;

    private List<String> fileName;

    private Integer likeNum;

    private boolean userCheck;

    private boolean likeCheck;

    private boolean reportCheck;

}
