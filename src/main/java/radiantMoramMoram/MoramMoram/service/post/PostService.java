package radiantMoramMoram.MoramMoram.service.post;
import radiantMoramMoram.MoramMoram.payload.request.post.WritePostRequest;

import radiantMoramMoram.MoramMoram.payload.request.post.LikePostRequest;
import radiantMoramMoram.MoramMoram.payload.request.post.ReportPostRequest;
import radiantMoramMoram.MoramMoram.payload.response.post.GetPostResponse;
import radiantMoramMoram.MoramMoram.payload.response.post.PostsResponse;

public interface PostService {

    void writePost(WritePostRequest writePostRequest, String token);

    GetPostResponse getPost(Integer postId, String token);

    void deletePost(Integer postId);

    void likePost(LikePostRequest likePostRequest);

    void reportPost(ReportPostRequest reportPostRequest);

    GetPostResponse randomPost(int num);

    PostsResponse getPostList(String category);

}
