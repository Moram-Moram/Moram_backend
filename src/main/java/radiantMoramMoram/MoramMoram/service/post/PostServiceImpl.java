package radiantMoramMoram.MoramMoram.service.post;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.domain.post.Post;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.post.PostRequest;
import radiantMoramMoram.MoramMoram.repository.PostRepository;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Value("${post.image.path}")
    private String imagePath;

    @SneakyThrows
    @Override
    public void writePost(PostRequest postRequest) {

        String fileName = UUID.randomUUID().toString();

        File file = new File(imagePath, fileName);

        postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .content(postRequest.getContent())
                        .userId(postRequest.getUserId())
                        .build()
        );

        postRequest.getImage().transferTo(file);

    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.deleteByPostId(postId);
    }
}
