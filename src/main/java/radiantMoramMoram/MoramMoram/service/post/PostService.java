package radiantMoramMoram.MoramMoram.service.post;

import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;
import radiantMoramMoram.MoramMoram.payload.response.GetPostResponse;

public interface PostService {

    void writePost(WritePostRequest writePostRequest);

    GetPostResponse getPost(Integer postId);

    void deletePost(Integer postId);

    void likePost(LikePostRequest likePostRequest);

}
