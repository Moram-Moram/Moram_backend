package radiantMoramMoram.MoramMoram.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.post.Post;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WritePostRequest {

    private Post post;

    private String title;

    private String content;

    private String userId;

    private MultipartFile[] image;

    private String[] category;

}
