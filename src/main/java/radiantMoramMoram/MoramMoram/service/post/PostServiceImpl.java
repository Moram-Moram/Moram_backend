package radiantMoramMoram.MoramMoram.service.post;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryEnum;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.entity.post.like.LikePost;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.exception.UserNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.repository.post.CategoryRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.LikePostRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;

import java.io.File;
import java.net.UnknownServiceException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final LikePostRepository likePostRepository;

    @Value("${image.upload.dir}")
    private String imagePath;

    @SneakyThrows
    @Override
    public void writePost(WritePostRequest writePostRequest, String token) {

        User user = userRepository.findById(jwtUtil.getUserIdFromJwtToken(token))
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.save(
                Post.builder()
                        .title(writePostRequest.getTitle())
                        .content(writePostRequest.getContent())
                        .user(user)
                        .build()
        );

        for (MultipartFile image : writePostRequest.getImage()) {

            String fileName = UUID.randomUUID().toString();

            File file = new File(imagePath, fileName);

            imageRepository.save(
                    Image.builder()
                            .post(post)
                            .fileName(fileName)
                            .build()
            );

            image.transferTo(file);

        }

        for (String category : writePostRequest.getCategory()) {
            categoryRepository.save(
                    Category.builder()
                            .post(post)
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

        return GetPostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .image(fileNames)
                .build();
    }

    @Override
    public void deletePost(Integer postId) {

        postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        postRepository.deleteById(postId);
    }

    @Override
    public void likePost(LikePostRequest likePostRequest) {

        User user = userRepository.findById(likePostRequest.getUser())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(likePostRequest.getPost())
                .orElseThrow(PostNotFoundException::new);

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

    public GetPostResponse randomPost(){

        Long number = postRepository.count();
        Random random = new Random();
        int r = random.nextInt(number.intValue());
        if(r==0) r++;

        Optional<Post> post = postRepository.findById(r);
        post.orElse(postRepository.findRandomPost());

        List<String> fileNames = imageRepository.findByPostOrderById(post.get().getId())
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        return GetPostResponse.builder()
                .postId(post.get().getId())
                .title(post.get().getTitle())
                .content(post.get().getContent())
                .userId(post.get().getUser().getId())
                .image(fileNames)
                .build();
    }

    @Override
    public List<MyPagePostResponse> getMyPagePost(String userId) {

        User users = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return postRepository.findByUser(users).stream()
                .map(post -> MyPagePostResponse.builder()
                        .id(post.getId())
                        .writer(post.getUser().toString())
                        .title(post.getTitle())
                        .date(post.getDate())
                        .image(getImage(post.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<MyPagePostResponse> getLikePost(String userId) {

        User users = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);


        if(users.getRole().equals(Authority.SHOWER) ) {
            return likePostRepository.findByUser(users).stream()
                    .map(post -> MyPagePostResponse.builder()
                            .id(post.getPost().getId())
                            .writer(post.getUser().toString())
                            .title(post.getPost().getTitle())
                            .date(post.getPost().getDate())
                            .image(getImage(post.getPost().getId()))
                            .build())
                    .collect(Collectors.toList());
        }
        else {
            return null;
        }
    }

    private List<String> getImage(Integer postId) {
        return imageRepository.findByPostOrderById(postId)
                .stream().map(Image::getFileName).collect(Collectors.toList());
    }

}
