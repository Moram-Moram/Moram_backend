package radiantMoramMoram.MoramMoram.service.post;

import org.springframework.data.domain.Pageable;
import radiantMoramMoram.MoramMoram.entity.user.User;
import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.mypage.MyPagePostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;

import java.util.List;

public interface PostService {

    void writePost(WritePostRequest writePostRequest, String token);

    GetPostResponse getPost(Integer postId);

    void deletePost(Integer postId);

    void likePost(LikePostRequest likePostRequest);

    void reportPost(ReportPostRequest reportPostRequest);

    List<MyPagePostResponse> getMyPagePost(String userId);

    List<MyPagePostResponse> getLikePost(String userId);

}
