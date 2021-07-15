package radiantMoramMoram.MoramMoram.entity.post.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryEnum {

    ANIMAL(1, "동물"),
    SPORT(2, "운동"),
    FOOD(3, "음식"),
    TRIP(4, "여행"),
    IT(5, "IT"),
    FASHION(6, "패션"),
    COSMETIC(7,"미용"),
    STUDY(8, "공부"),
    WORRY(9, "고민"),
    MORAM(10, "모람?");

    private final int id;

    private final String name;

}
