package radiantMoramMoram.MoramMoram.service.image;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import radiantMoramMoram.MoramMoram.exception.ImageNotFoundException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService{

    @Value("${post.image.path}")
    private String imagePath;

    @SneakyThrows
    @Override
    public byte[] getImage(String imageName) {

        File file = new File(imagePath, imageName);

        if(!file.exists()) {
            throw new ImageNotFoundException();
        }
        InputStream inputStream = new FileInputStream(file);

        return IOUtils.toByteArray(inputStream);
    }
}
