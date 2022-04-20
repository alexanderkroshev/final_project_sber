package server.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UserSaveException extends RuntimeException{
    public UserSaveException(String message) {
        super(message);
    }
}
