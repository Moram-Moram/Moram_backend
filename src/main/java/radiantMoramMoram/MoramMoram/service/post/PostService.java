package radiantMoramMoram.MoramMoram.service.post;

import radiantMoramMoram.MoramMoram.payload.request.post.PostRequest;
import radiantMoramMoram.MoramMoram.payload.response.PostResponse;

public interface PostService {

    void writePost(PostRequest postRequest);

    void deletePost(Integer postId);

}
