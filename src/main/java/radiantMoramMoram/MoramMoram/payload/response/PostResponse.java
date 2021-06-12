package radiantMoramMoram.MoramMoram.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {

    private Integer postId;

    private String title;

    private String content;

    private String userId;

    private MultipartFile[] image;

    private String[] category;

}
