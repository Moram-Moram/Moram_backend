package radiantMoramMoram.MoramMoram.security.auth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AuthorityAttributeConverter implements AttributeConverter<Authority, Integer> {

    // db에 들어갈때 어떤 값을 저장할 것인가
    @Override
    public Integer convertToDatabaseColumn(Authority a) {
        return a.num;
    }

    // db에서 entity로값을 넣어줄때 어떤값을 리턴해줄것이냐
    @Override
    public Authority convertToEntityAttribute(Integer num) {

        return Stream.of(Authority.values())
                .filter(c -> c.num == num)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
