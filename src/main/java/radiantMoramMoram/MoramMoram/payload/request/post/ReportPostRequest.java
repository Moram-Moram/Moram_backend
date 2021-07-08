package radiantMoramMoram.MoramMoram.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportPostRequest {

    private int postId;

    private String userId;

}
