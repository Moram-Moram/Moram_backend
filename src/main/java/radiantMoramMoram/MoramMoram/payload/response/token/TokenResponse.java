package radiantMoramMoram.MoramMoram.payload.response.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class TokenResponse {
    @JsonProperty("access-token")
    private String accessJws;
    @JsonProperty("refresh-token")
    private String refreshJws;


    public TokenResponse(String accessJws, String refreshJws){
        this.accessJws = accessJws;
        this.refreshJws = refreshJws;
    }
}
