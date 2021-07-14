package radiantMoramMoram.MoramMoram.security.auth;


import lombok.Getter;

@Getter
public enum Authority {
    WATER_DROP(0, "물방울"),
    SCOTCH_MIST(1, "안개비"),
    DRIZZLE(2, "이슬비"),
    SHOWER(3, "소나기"),
    ADMIN(4, "어드민");

    public int num;
    public String ex;

    Authority(int num, String ex){
        this.num = num;
        this.ex = ex;
    }
}
