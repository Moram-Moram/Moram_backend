package radiantMoramMoram.MoramMoram.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.entity.post.image.Image;
import radiantMoramMoram.MoramMoram.exception.PostNotFoundException;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportListResponse;
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
    public ReportListResponse getReportList(Pageable pageable) {

        Page<Post> posts = reportRepository.findAllBy(pageable);

        int totalPage = posts.getTotalPages();

        return new ReportListResponse(totalPage, posts.map((Post post) -> buildReport(post, post.getId()))
                .stream().collect(Collectors.toList()));
    }

    private ReportResponse buildReport(Post post, int postId) {

        List<String> fileNames = imageRepository.findByPostOrderById(postId)
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        return ReportResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getUser().getId())
                .date(post.getDate())
                .image(fileNames)
                .build();
    }

    @Override
    public ReportPostResponse getReportPost(int postId) {

        Post post = reportRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        List<String> fileNames = imageRepository.findByPostOrderById(postId)
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        return ReportPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getUser().getId())
                .content(post.getContent())
                .date(post.getDate())
                .image(fileNames)
                .build();
    }

    @Override
    public void deletePost(int postId){
        reportRepository.deleteById(postId);
    }
}
