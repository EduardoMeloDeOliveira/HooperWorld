package hoop.api.api.handler;


import hoop.api.api.handler.exceptions.ConflitException;
import hoop.api.api.handler.exceptions.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handlerObjectNotFoundException(ObjectNotFoundException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(400).body("é esperado que todos os campos estejam preenchidos");
    }


    @ExceptionHandler(ConflitException.class)
    public ResponseEntity<String> conflitException(ConflitException ex) {
        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return ResponseEntity.status(404).body("Usuário não encontrado");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
        return ResponseEntity.status(401).body("E-mail ou senha incorretos");
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorFieldDto>> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();

        return ResponseEntity.status(400).body(
                fieldErrors.stream()
                        .map(ErrorFieldDto::new)
                        .collect(Collectors.toList())
        );

    }
    private record ErrorFieldDto(String field, String message) {

        public ErrorFieldDto(FieldError fieldError) {
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
