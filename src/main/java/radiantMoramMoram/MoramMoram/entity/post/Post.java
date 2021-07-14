package radiantMoramMoram.MoramMoram.entity.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import radiantMoramMoram.MoramMoram.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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

    @ManyToOne
    private User user;

    private boolean report;

    public Post setReport(boolean report) {
        this.report = report;
        return this;
    }

}