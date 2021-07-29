package radiantMoramMoram.MoramMoram.security.auth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class AuthorityAttributeConverter implements AttributeConverter<Authority, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Authority a) {
        return a.num;
    }

    @Override
    public Authority convertToEntityAttribute(Integer num) {
        return Stream.of(Authority.values())
                .filter(c -> c.num == num)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
