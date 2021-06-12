package radiantMoramMoram.MoramMoram.payload.request.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private String title;

    private String content;

    private String userId;

    private MultipartFile[] image;

    private String[] category;

}
