package radiantMoramMoram.MoramMoram.service.admin;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;

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


    @Override
    public void deletePost(int postId){
        if(findByPost(postId)){
            throw new PostNotFoundException();
        }
        postRepository.deleteById(postId);
    }

    private boolean findByPost(int postId){
        return postRepository.findById(postId).isEmpty();
    }
}
