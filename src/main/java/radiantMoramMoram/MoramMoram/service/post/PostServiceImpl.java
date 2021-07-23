package radiantMoramMoram.MoramMoram.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.PostListResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.PostsResponse;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
<<<<<<< HEAD
=======
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
>>>>>>> user
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryEnum;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.entity.post.like.LikePost;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.exception.UserNotFoundException;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
<<<<<<< HEAD
=======
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;
>>>>>>> user
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.repository.post.CategoryRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.LikePostRepository;
<<<<<<< HEAD
=======
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
>>>>>>> user
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;

import javax.transaction.Transactional;
import java.io.File;
import java.net.UnknownServiceException;
import java.util.*;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
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

    @Value("${post.image.path}")
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

        for (MultipartFile image : writePostRequest.getFileName()) {

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
    public GetPostResponse getPost(Integer postId, String token) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        User user = userRepository.findById(jwtUtil.getUserIdFromJwtToken(token))
                .orElseThrow(UserNotFoundException::new);

        int likePostNum = likePostRepository.postLikeNum(postId);

        List<String> fileNames = imageRepository.findByPostOrderById(post)
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        return GetPostResponse.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .writer(user.getNickname())
                .fileName(fileNames)
                .likeNum(likePostNum)
                .build();
    }

    @Override
    public void deletePost(Integer postId, String token) {

        User user = userRepository.findById(jwtUtil.getUserIdFromJwtToken(token))
                .orElseThrow(UserNotFoundException::new);

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
    public void reportPost(Integer postId, String token) {

        User user = userRepository.findById(jwtUtil.getUserIdFromJwtToken(token))
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if(!post.isReport()) {
            postRepository.save(
                    post.setReport(true)
            );
        }

    }

    @Override
    public GetPostResponse randomPost(int num){

        Long number = postRepository.count();
        Random random = new Random();
        int r = random.nextInt(number.intValue());
        if(r==0) r++;

        Optional<Post> p = postRepository.findById(r);
        Post post = p.orElseGet(postRepository::findRandomPost);

        List<String> fileNames = imageRepository.findByPostOrderById(post.getId())
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        int likeNum = likePostRepository.postLikeNum(post.getId());

        return GetPostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getNickname())
                .user(post.getUser().getNickname())
                .date(post.getDate())
                .likeNum(likeNum)
                .fileName(fileNames)
                .build();
    }

<<<<<<< HEAD
    @Transactional
    public PostsResponse getPostList(String category){

        List<Integer> postIdList = categoryRepository.categoryPostListReturn(category);
=======
    @Override
    public List<MyPagePostResponse> getMyPagePost(String userId) {

        User users = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return postRepository.findByUser(users).stream()
                .map(post -> MyPagePostResponse.builder()
                        .id(post.getId())
                        .writer(post.getUser().getNickname())
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
                            .writer(post.getUser().getNickname())
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
>>>>>>> user

        if(postIdList.isEmpty()){
            throw new BasicException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        List<Post> posts = new ArrayList<>();
        for(int postId : postIdList){
            posts.add(postRepository.findById(postId).orElseThrow(PostNotFoundException::new));
        }

        List<PostListResponse> postList = new ArrayList<>();

        for(Post p : posts){
            List<String> fileNames = getFileFromPost(p);
            postList.add(
                    PostListResponse.builder()
                    .content(p.getContent())
                    .date(p.getDate())
                    .image(fileNames.get(0))
                    .postId(p.getId())
                    .title(p.getTitle())
                    .writer(p.getUser().getNickname())
                    .build()
            );
        }

        return PostsResponse.builder()
                .category(category)
                .posts(postList)
                .build();

    }

    private List<String> getFileFromPost(Post post){
        return imageRepository.findByPostOrderById(post)
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());
    }
}