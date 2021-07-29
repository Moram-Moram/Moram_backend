package radiantMoramMoram.MoramMoram.service.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.exception.*;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.repository.CommentRepository;
import radiantMoramMoram.MoramMoram.repository.post.CategoryRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.LikePostRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final CategoryRepository categoryRepository;
    private final CommentRepository commentRepository;
    private final LikePostRepository likeRepository;

    @Override
    public List<ReportPostResponse> getReportList() {

        return postRepository.findByReport(true).stream()
                .map(post -> ReportPostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .writer(post.getUser().getNickname())
                        .date(post.getDate())
                        .image(getImage(post))
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public ReportPostResponse getReportPost(int postId) {

        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return ReportPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getUser().getNickname())
                .content(post.getContent())
                .date(post.getDate())
                .image(getImage(post))
                .build();
    }

    private List<String> getImage(Post post) {
        return imageRepository.findByPostOrderById(post)
                .stream().map(Image::getPath).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public void deletePost(int postId){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isEmpty()){
            throw new PostNotFoundException();
        }

        if(likeRepository.findByPost_Id(postId)!=null){
            likeRepository.deleteAllByPost_Id(postId);
        }

        imageRepository.deleteByPostId(postId);
        categoryRepository.deleteByPostId(postId);
        if(commentRepository.findByPost_Id(postId).isEmpty()){
            postRepository.deleteById(postId);
            return;
        }
        commentRepository.deleteByPostId(postId);
        postRepository.deleteById(postId);
    }

    private boolean findByPost(int postId){
        return postRepository.findById(postId).isEmpty();
    }
}
