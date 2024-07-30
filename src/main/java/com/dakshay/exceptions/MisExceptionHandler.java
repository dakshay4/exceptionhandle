package com.dakshay.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ControllerAdvice
@RequiredArgsConstructor
public class MisExceptionHandler {

  private final Logger logger = LoggerFactory.getLogger(getClass());

  private final MessageSource messageSource;

  @ExceptionHandler(MisCustomException.class)
  public ResponseEntity<MisErrorHttpResponse> handleCustomException(MisCustomException ex, HttpServletRequest request) {
    Locale locale = null;
//            LocaleUtils.toLocale(UserContextResolver.getCurrentContext().getLocale());
    String localizedMessage = messageSource.getMessage(ex.getMisError().getMessageKey(), ex.getArgs(), locale);
    localizedMessage = replaceUnreplacedPlaceholders(localizedMessage);
    HttpStatus statusCode = ex.getMisError().getErrorType().getStatusCode();
    MisErrorHttpResponse errorResponse = new MisErrorHttpResponse(ex.getMisError().getErrorType().name(), localizedMessage, System.currentTimeMillis(),
        request.getRequestURI(), ex.getCause().getMessage());
    logger.error("{}", ex);
    return new ResponseEntity<>(errorResponse, statusCode);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<MisErrorHttpResponse> handleException(Exception ex, HttpServletRequest request) {
    String cause = ex.getCause()!=null ? ex.getCause().getMessage() : null;
    MisErrorHttpResponse errorResponse = new MisErrorHttpResponse("Error", ex.getMessage(), System.currentTimeMillis(),
        request.getRequestURI(), cause);
    logger.error("Exception Handler -- {}", ex);

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private String replaceUnreplacedPlaceholders(String message) {
    // Regular expression to match placeholders like {0}, {1}, etc.
    String regex = "\\{\\d+\\}";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(message);

    // Replace all matches with "_"
    return matcher.replaceAll("_");
  }
}
