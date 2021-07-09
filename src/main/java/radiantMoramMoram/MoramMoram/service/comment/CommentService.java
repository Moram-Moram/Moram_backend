package radiantMoramMoram.MoramMoram.service.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.repository.CommentRepository;

@RequiredArgsConstructor
@Service
public class CommentService {
    private CommentRepository commentRepository;

    public void writeComment(){
        
    }
}
