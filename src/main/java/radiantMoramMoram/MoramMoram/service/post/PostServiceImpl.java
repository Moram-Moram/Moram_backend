package radiantMoramMoram.MoramMoram.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.like.LikePostKey;
import radiantMoramMoram.MoramMoram.error.BasicException;
import radiantMoramMoram.MoramMoram.error.ErrorCode;
import radiantMoramMoram.MoramMoram.exception.*;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.post.PostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.PostListResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.PostsResponse;
import radiantMoramMoram.MoramMoram.repository.CommentRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import radiantMoramMoram.MoramMoram.entity.post.category.Category;
import radiantMoramMoram.MoramMoram.entity.post.category.CategoryEnum;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.entity.post.like.LikePost;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.repository.UserRepository;
import radiantMoramMoram.MoramMoram.repository.post.CategoryRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.LikePostRepository;
import radiantMoramMoram.MoramMoram.security.auth.Authority;
import radiantMoramMoram.MoramMoram.security.token.JwtUtil;

import java.io.File;
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
    private final CommentRepository commentRepository;

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
                            .path(fileName)
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
    public PostResponse getPost(Integer postId, String token) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        User user = userRepository.findById(jwtUtil.getUserIdFromJwtToken(token))
                .orElseThrow(UserNotFoundException::new);

        int likePostNum = likePostRepository.postLikeNum(postId);

        List<String> fileNames = imageRepository.findByPostOrderById(post)
                .stream().map(Image::getPath)
                .collect(Collectors.toList());

        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getNickname())
                .date(post.getDate())
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

        imageRepository.findByPostId(postId)
                .orElseThrow(ImageNotFoundException::new);

        categoryRepository.findByPostId(postId)
                .orElseThrow(CategoryNotFoundException::new);

        commentRepository.findByPostId(postId)
                .orElseThrow(CommentNotFoundException::new);

        postRepository.deleteById(postId);

        imageRepository.deleteByPostId(postId);

        categoryRepository.deleteByPostId(postId);

        commentRepository.deleteByPostId(postId);

    }

    @Override
    public void likePost(LikePostRequest likePostRequest) {

        User user = userRepository.findById(likePostRequest.getUser())
                .orElseThrow(UserNotFoundException::new);

        Post post = postRepository.findById(likePostRequest.getPost())
                .orElseThrow(PostNotFoundException::new);

        boolean isLikePosted = likePostRepository.existsByPostAndUser(post, user);

        if (isLikePosted) {
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

        if (!post.isReport()) {
            postRepository.save(
                    post.setReport(true)
            );
        }

    }

    @Override
    public PostResponse randomPost(int num, String token) {

        Long number = postRepository.count();
        Random random = new Random();
        int r = random.nextInt(number.intValue());
        if (r == 0) r++;

        Optional<Post> p = postRepository.findById(r);
        Post post = p.orElseGet(postRepository::findRandomPost);

        List<String> fileNames = imageRepository.findByPostOrderById(post)
                .stream().map(Image::getPath)
                .collect(Collectors.toList());

        int likeNum = likePostRepository.postLikeNum(post.getId());

        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .writer(post.getUser().getNickname())
                .user(post.getUser().getNickname())
                .date(post.getDate())
                .likeNum(likeNum)
                .fileName(fileNames)
                .userCheck(checkUser(token, post.getId()))
                .likeCheck(checkLike(token, post.getId()))
                .reportCheck(false)
                .build();
    }

    @Override
    public PostsResponse getPostList(String category) {
        List<Integer> postIdList = categoryRepository.categoryPostListReturn(category);

        if (postIdList.isEmpty()) {
            throw new BasicException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        List<Post> posts = new ArrayList<>();
        for (int postId : postIdList) {
            posts.add(postRepository.findById(postId).orElseThrow(PostNotFoundException::new));
        }

        List<PostListResponse> postList = new ArrayList<>();

        for (Post p : posts) {
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

        return new PostsResponse(category, postList);
    }

    @Override
    public List<MyPagePostResponse> getMyPagePost (String userId, String token){

        User user = userRepository.findById(jwtUtil.getUserIdFromJwtToken(token))
                    .orElseThrow(UserNotFoundException::new);

        return postRepository.findByUser(user).stream()
                    .map(post -> MyPagePostResponse.builder()
                            .id(post.getId())
                            .writer(post.getUser().getNickname())
                            .title(post.getTitle())
                            .date(post.getDate())
                            .image(getImage(post))
                            .build())
                    .collect(Collectors.toList());
    }

    @Override
    public List<MyPagePostResponse> getLikePost (String userId, String token){

            User user = userRepository.findById(userId)
                    .orElseThrow(UserNotFoundException::new);


            if (user.getRole().equals(Authority.SHOWER)) {
                return likePostRepository.findByUser(user).stream()
                        .map(post -> MyPagePostResponse.builder()
                                .id(post.getPost().getId())
                                .writer(post.getUser().getNickname())
                                .title(post.getPost().getTitle())
                                .date(post.getPost().getDate())
                                .image(getImage(post.getPost()))
                                .build())
                        .collect(Collectors.toList());
            } else {
                return null;
            }
    }

    private List<String> getImage (Post post){
        return imageRepository.findByPostOrderById(post)
                    .stream().map(Image::getPath).collect(Collectors.toList());
    }

    private List<String> getFileFromPost (Post post){
        return imageRepository.findByPostOrderById(post)
                    .stream().map(Image::getPath)
                    .collect(Collectors.toList());
    }

    private boolean checkUser(String token, int postId){
        if(token == null) {
            return false;
        }
        Optional<Post> post = postRepository.findById(postId);
        post.orElseThrow(PostNotFoundException::new);
        return post.get().getUser().getId().equals(jwtUtil.getUserIdFromJwtToken(token));
    }

    private boolean checkLike(String token, int postId){
        if(token == null){
            return false;
        }
        LikePostKey likeKey = LikePostKey.builder()
                .post(postId)
                .user(jwtUtil.getUserIdFromJwtToken(token))
                .build();
        Optional<LikePost> like = likePostRepository.findById(likeKey);
        return like.isPresent();
    }
}