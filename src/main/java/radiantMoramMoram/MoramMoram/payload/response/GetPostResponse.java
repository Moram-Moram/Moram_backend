package radiantMoramMoram.MoramMoram.payload.response;

import lombok.*;
import radiantMoramMoram.MoramMoram.entity.user.User;

import java.util.List;

@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetPostResponse {

    private Integer postId;

    private String title;

    private String content;

    private User user;

    private List<String> image;

    private List<String> category;

}
