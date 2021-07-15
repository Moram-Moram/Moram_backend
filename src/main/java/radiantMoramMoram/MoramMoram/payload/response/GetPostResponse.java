package radiantMoramMoram.MoramMoram.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.user.User;

import java.util.List;

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
