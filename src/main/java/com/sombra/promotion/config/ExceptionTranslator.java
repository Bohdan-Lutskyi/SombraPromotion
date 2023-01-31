package com.sombra.promotion.config;

import com.sombra.promotion.config.error.ApiExceptionDTO;
import com.sombra.promotion.config.error.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionTranslator {

    private final static Logger log = LoggerFactory.getLogger(ExceptionTranslator.class);

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ApiExceptionDTO> handleCustomException(final SystemException ex) {
        log.error(ex.getMessage(), ex);
        final ResponseEntity.BodyBuilder builder = ResponseEntity.status(ex.getErrorCode().getHttpStatus());
        final ApiExceptionDTO apiExceptionDTO = new ApiExceptionDTO();
        apiExceptionDTO.setMessage(ex.getMessage());
        apiExceptionDTO.setCode(ex.getErrorCode().getHttpStatus().name());
        return builder.body(apiExceptionDTO);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiExceptionDTO> handleCustomException(final MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);
        final ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.BAD_REQUEST);
        final ApiExceptionDTO apiExceptionDTO = new ApiExceptionDTO();
        apiExceptionDTO.setMessage(ex.getMessage());
        apiExceptionDTO.setCode(HttpStatus.BAD_REQUEST.name());
        return builder.body(apiExceptionDTO);
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleCustomException(final SQLException ex) {
        log.error(ex.getMessage(), ex);
        final ApiExceptionDTO apiExceptionDTO = new ApiExceptionDTO();
        apiExceptionDTO.setMessage(ex.getMessage());
        apiExceptionDTO.setCode(String.valueOf(ex.getErrorCode()));
        return apiExceptionDTO;
    }

}
