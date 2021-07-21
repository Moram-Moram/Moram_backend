package radiantMoramMoram.MoramMoram;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import radiantMoramMoram.MoramMoram.service.admin.ReportServiceImpl;

@SpringBootTest
public class AdminTests {
    @Autowired
    ReportServiceImpl reportService;
    @Test
    public void deleteTest(){
        reportService.deletePost(1);
    }
}
