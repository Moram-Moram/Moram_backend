package radiantMoramMoram.MoramMoram.service.post;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryEnum;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.entity.post.like.LikePost;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.GetPostResponse;
import radiantMoramMoram.MoramMoram.repository.post.CategoryRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.LikePostRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final LikePostRepository likePostRepository;

    @Value("${post.image.path}")
    private String imagePath;

    @SneakyThrows
    @Override
    public void writePost(WritePostRequest writePostRequest) {

        Post post = postRepository.save(
                Post.builder()
                        .title(writePostRequest.getTitle())
                        .content(writePostRequest.getContent())
                        .userId(writePostRequest.getUserId())
                        .build()
        );

        for (MultipartFile image : writePostRequest.getImage()) {

            String fileName = UUID.randomUUID().toString();

            File file = new File(imagePath, fileName);

            imageRepository.save(
                    Image.builder()
                            .post(writePostRequest.getPost())
                            .fileName(fileName)
                            .build()
            );

            image.transferTo(file);

        }

        for (String category : writePostRequest.getCategory()) {
            categoryRepository.save(
                    Category.builder()
                            .post(writePostRequest.getPost())
                            .category(CategoryEnum.valueOf(category))
                            .build()
            );
        }

    }

    @Override
    public GetPostResponse getPost(Integer postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        List<String> fileNames = imageRepository.findByPostOrderById(postId)
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        List<String> categoryNames = categoryRepository.findByPostIdOrderById(postId).stream()
                .map(Category::getCategory)
                .map(CategoryEnum::getName)
                .collect(Collectors.toList());

        return GetPostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUserId())
                .category(categoryNames)
                .image(fileNames)
                .build();
    }

    @Override
    public void deletePost(Integer postId) {
        postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.deleteByPostId(postId);
    }

    @Override
    public void likePost(LikePostRequest likePostRequest) {

        User user = likePostRequest.getUser();
        Post post = likePostRequest.getPost();

        boolean isLikePosted = likePostRepository.existsByPostAndUser(post, user);

        if(isLikePosted) {
            likePostRepository.deleteByPostAndUser(post, user);
        } else {
            likePostRepository.save(
                    LikePost.builder()
                            .post(post)
                            .user(user)
                            .build()
            );
        }
    }

    @Override
    public void reportPost(ReportPostRequest reportPostRequest) {

        int postId = reportPostRequest.getPostId();

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if(!post.isReport()) {
            postRepository.save(
                    post.setReport(true)
            );
        }

    }


}
