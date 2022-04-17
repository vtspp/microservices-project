package br.com.hroauth.handler;

import br.com.hroauth.exception.FindUserByEmailException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler (value = FindUserByEmailException.class)
    public ResponseEntity<ResponseHandler> responseHandler (FindUserByEmailException e, HttpServletRequest request) {
        ResponseHandler response = ResponseHandler.builder()
                .path(request.getRequestURI())
                .instant(LocalDateTime.now())
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Data
    @Builder
    private static class ResponseHandler {

        private String path;

        @JsonFormat (pattern = "dd-MM-yyyy hh:mm:ss")
        private LocalDateTime instant;

        private int code;

        private String message;
    }
}