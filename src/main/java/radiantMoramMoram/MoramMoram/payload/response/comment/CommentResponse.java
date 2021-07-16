package radiantMoramMoram.MoramMoram.payload.response.comment;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.sql.Timestamp;

@ToString
@Builder
@Getter
public class CommentResponse {
    private String content;
    private String userNickName;
    private Timestamp regDate;
}
