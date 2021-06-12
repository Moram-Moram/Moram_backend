package radiantMoramMoram.MoramMoram.service.post;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.domain.post.category.Category;
import radiantMoramMoram.MoramMoram.domain.post.category.CategoryEnum;
import radiantMoramMoram.MoramMoram.domain.post.Post;
import radiantMoramMoram.MoramMoram.domain.post.image.Image;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.post.PostRequest;
import radiantMoramMoram.MoramMoram.payload.response.PostResponse;
import radiantMoramMoram.MoramMoram.repository.post.CategoryRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Value("${post.image.path}")
    private String imagePath;

    @SneakyThrows
    @Override
    public void writePost(PostRequest postRequest) {

        Post post = postRepository.save(
                Post.builder()
                        .title(postRequest.getTitle())
                        .content(postRequest.getContent())
                        .userId(postRequest.getUserId())
                        .build()
        );

        for (MultipartFile image : postRequest.getImage()) {

            String fileName = UUID.randomUUID().toString();

            File file = new File(imagePath, fileName);

            imageRepository.save(
                    Image.builder()
                            .postId(post.getId())
                            .fileName(fileName)
                            .build()
            );

            image.transferTo(file);

        }

        for (String category : postRequest.getCategory()) {
            categoryRepository.save(
                    Category.builder()
                            .postId(post.getId())
                            .category(CategoryEnum.valueOf(category))
                            .build()
            );
        }

    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.deleteByPostId(postId);
    }

}
