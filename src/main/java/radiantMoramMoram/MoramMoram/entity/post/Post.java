package radiantMoramMoram.MoramMoram.entity.post;

import lombok.*;
import radiantMoramMoram.MoramMoram.entity.user.User;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "create_at")
    private LocalDate date;

    @ManyToOne
    private User user;

    private boolean report;

    public Post setReport(boolean report) {
        this.report = report;
        return this;
    }

}