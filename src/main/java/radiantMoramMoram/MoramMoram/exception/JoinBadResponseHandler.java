package radiantMoramMoram.MoramMoram.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import radiantMoramMoram.MoramMoram.error.ErrorResponse;

@RestControllerAdvice
public class JoinBadResponseHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> vaildException(MethodArgumentNotValidException e){

        String message = "Validation failed";
        ErrorResponse response = new ErrorResponse(400, message);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
