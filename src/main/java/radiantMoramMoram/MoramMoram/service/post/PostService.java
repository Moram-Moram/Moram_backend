package radiantMoramMoram.MoramMoram.service.post;

import radiantMoramMoram.MoramMoram.payload.request.post.PostRequest;

public interface PostService {

    void writePost(PostRequest postRequest);

    void deletePost(Integer postId);

}
