package br.com.bookrestaurant.external.config;


import br.com.bookrestaurant.entity.reserve.exception.ReserveInvalidException;
import br.com.bookrestaurant.entity.reserve.exception.ReserveNotFoundException;
import br.com.bookrestaurant.external.dto.ErrorApi;
import br.com.bookrestaurant.external.dto.FieldError;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public class ErrorsHandlerConfig {
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> errorArgumentsNotValid(MethodArgumentNotValidException ex) {
        log.error(ex);
        ErrorApi errorApi = new ErrorApi("Request com argumento(s) inválido(s).");
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorApi.addError(new FieldError(error.getField(),
                    messageSource.getMessage(error, LocaleContextHolder.getLocale())));
        });
        return ResponseEntity.badRequest().body(errorApi);
    }

    @ExceptionHandler(ReserveInvalidException.class)
    public ResponseEntity<ErrorApi> errorReserveInvalid(ReserveInvalidException ex) {
        log.error(ex);
        return ResponseEntity.badRequest().body(new ErrorApi(ex.getMessage()));
    }

    @ExceptionHandler(ReserveNotFoundException.class)
    public ResponseEntity<ErrorApi> errorReserveNorFound(ReserveNotFoundException ex) {
        log.error(ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> errorReserve(Exception ex) {
        log.error(ex);
        return ResponseEntity.internalServerError().build();
    }
}
