package radiantMoramMoram.MoramMoram.payload.response.mypage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPageResponse {

    private final String id;

    private final String name;

    private final String role;

}
