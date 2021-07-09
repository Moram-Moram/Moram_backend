package radiantMoramMoram.MoramMoram.exception;

public class ImageNotFoundException extends BasicException {

    public ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }

}
