package radiantMoramMoram.MoramMoram.entity.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "tbl_post")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private int id;

    private String title;

    private String content;

    private String userId;

    private boolean report;

    public Post setReport(boolean report) {
        this.report = report;
        return this;
    }

}