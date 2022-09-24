package server.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public class LegalEntityNotFoundException extends RuntimeException {
        public LegalEntityNotFoundException(String message) {
            super(message);
        }
}
