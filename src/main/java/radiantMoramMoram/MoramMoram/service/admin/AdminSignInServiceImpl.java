package radiantMoramMoram.MoramMoram.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.entity.post.Post;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportListResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportPostResponse;
import radiantMoramMoram.MoramMoram.payload.response.admin.ReportResponse;
import radiantMoramMoram.MoramMoram.repository.admin.ReportRepository;
import radiantMoramMoram.MoramMoram.repository.post.PostRepository;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminSignInServiceImpl implements AdminSignInService {

    private final ReportRepository reportRepository;
    private final ImageRepository imageRepository;

    @Override
    public ReportListResponse getReportList(Pageable pageable) {
        Page<Post> posts = reportRepository.findAllBy(pageable);

        return new ReportListResponse(posts.getTotalPages(), posts.map(this::buildReport)
                .stream().collect(Collectors.toList()));
    }

    private ReportResponse buildReport(Post post, int postId) {

        List<String> fileNames = imageRepository.findByPostOrderById(postId)
                .stream().map(Image::getFileName)
                .collect(Collectors.toList());

        return ReportResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .writer(post.getUserId())
                .date(post.getDate())
                .content(post.getContent())
                .image(fileNames)
                .build();
    }

    @Override
    public ReportPostResponse getReportPost(int id) {
        return null;
    }
}
