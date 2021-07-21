package radiantMoramMoram.MoramMoram.service.post;

import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;

public interface PostService {

    void writePost(WritePostRequest writePostRequest, String token);

    GetPostResponse getPost(Integer postId);

    void deletePost(Integer postId);

    void likePost(LikePostRequest likePostRequest);

    void reportPost(ReportPostRequest reportPostRequest);

}
