package pl.mjuapps.flightplannerutil.web.translator;

import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;

/**
 * Custom, global exception handler
 */
@Log4j2
@ControllerAdvice
public class ExceptionTranslator  {

    /**
     * Translate {@link IllegalArgumentException} to HTTP Bad Request
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request) {
        log.info("Handling {} with message {}", ex.getClass(), ex.getMessage());
        ErrorResponse response = ErrorResponse.builder()
                .timestamp(OffsetDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @Data
    @Builder
    static class ErrorResponse {
        OffsetDateTime timestamp;
        Integer status;
        String error;
        String message;
        String path;
    }

}
