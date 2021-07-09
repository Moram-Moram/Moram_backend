package radiantMoramMoram.MoramMoram.service.post;

import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
<<<<<<< Updated upstream
import radiantMoramMoram.MoramMoram.payload.response.GetPostResponse;
=======
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;
>>>>>>> Stashed changes

public interface PostService {

    void writePost(WritePostRequest writePostRequest);

    GetPostResponse getPost(Integer postId);

    void deletePost(Integer postId);

    void likePost(LikePostRequest likePostRequest);

    void reportPost(ReportPostRequest reportPostRequest);

}
