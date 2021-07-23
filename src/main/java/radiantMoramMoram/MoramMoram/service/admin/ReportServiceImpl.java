package radiantMoramMoram.MoramMoram.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportResponse;
import radiantMoramMoram.MoramMoram.repository.admin.ReportRepository;
import radiantMoramMoram.MoramMoram.repository.post.ImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<ReportPostResponse> getReportList() {

        return reportRepository.findAllBy().stream()
                .map(post -> ReportPostResponse.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .writer(post.getUser().getNickname())
                        .date(post.getDate())
                        .image(getImage(post.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ReportPostResponse getReportPost(int postId) {

        Post post = reportRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return ReportPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getUser().getNickname())
                .content(post.getContent())
                .date(post.getDate())
                .image(getImage(postId))
                .build();
    }

    private List<String> getImage(Integer postId) {
        return imageRepository.findByPostOrderById(postId)
                .stream().map(Image::getFileName).collect(Collectors.toList());
    }


    @Override
    public void deletePost(int postId){
        if(findByPost(postId)){
            throw new PostNotFoundException();
        }
        reportRepository.deleteById(postId);
    }

    private boolean findByPost(int postId){
        return reportRepository.findById(postId).isEmpty();
    }
}
