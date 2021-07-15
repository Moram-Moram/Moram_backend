package radiantMoramMoram.MoramMoram.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.user.User;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WritePostRequest {

    private int post;

    private String title;

    private String content;

    private String  user;

    private MultipartFile[] image;

    private String[] category;
}
