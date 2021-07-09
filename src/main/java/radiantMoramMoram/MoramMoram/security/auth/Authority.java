package radiantMoramMoram.MoramMoram.security.auth;


import lombok.Getter;

@Getter
public enum Authority {
    ROLE_WATER_DROP(0, "물방울"),
    ROLE_SCOTCH_MIST(1, "안개비"),
    ROLE_DRIZZLE(2, "이슬비"),
    ROLE_SHOWER(3, "소나기"),
    ROLE_ADMIN(4, "어드민");

    public int num;
    public String ex;

    Authority(int num, String ex){
        this.num = num;
        this.ex = ex;
    }
}
