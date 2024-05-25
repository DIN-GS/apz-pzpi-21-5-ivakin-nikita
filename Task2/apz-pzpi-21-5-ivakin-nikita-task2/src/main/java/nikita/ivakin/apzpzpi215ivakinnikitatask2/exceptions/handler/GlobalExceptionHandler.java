package nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.handler;

import jakarta.validation.constraints.NotNull;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.*;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.errorResponse.CustomErrorResponse;
import nikita.ivakin.apzpzpi215ivakinnikitatask2.exceptions.errorResponse.ValidationExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CommanderAssigningException.class)
    public ResponseEntity<CustomErrorResponse> handleCommanderAssigningException(CommanderAssigningException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommanderAuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleCommanderAuthenticationException(CommanderAuthenticationException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
    }

    @ExceptionHandler(CommanderDoesNotAssignedException.class)
    public ResponseEntity<CustomErrorResponse> handleCommanderDoesNotAssignedException(CommanderDoesNotAssignedException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CommanderNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleCommanderSendingResourcesException(CommanderNotFoundException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommanderSendingResourcesException.class)
    public ResponseEntity<CustomErrorResponse> handleCommanderSendingResourcesException(CommanderSendingResourcesException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GivenResourcesCreationException.class)
    public ResponseEntity<CustomErrorResponse> handleGivenResourcesCreationException(GivenResourcesCreationException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GivenResourcesNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleGivenResourcesNotFoundException(GivenResourcesNotFoundException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MilitaryGroupCreationException.class)
    public ResponseEntity<CustomErrorResponse> handleMilitaryGroupCreationException(MilitaryGroupCreationException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MilitaryGroupNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleMilitaryGroupNotFoundException(MilitaryGroupNotFoundException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MilitaryGroupUpdateException.class)
    public ResponseEntity<CustomErrorResponse> handleMilitaryGroupUpdateException(MilitaryGroupUpdateException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourcesRequestCreationException.class)
    public ResponseEntity<CustomErrorResponse> handleResourcesRequestCreationException(ResourcesRequestCreationException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourcesRequestNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourcesRequestNotFoundException(ResourcesRequestNotFoundException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SupplyRequestCreationException.class)
    public ResponseEntity<CustomErrorResponse> handleSupplyRequestCreationException(SupplyRequestCreationException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SupplyRequestNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleSupplyRequestNotFoundException(SupplyRequestNotFoundException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SupplyRequestUpdateException.class)
    public ResponseEntity<CustomErrorResponse> handleSupplyRequestUpdateException(SupplyRequestUpdateException ex) {
        CustomErrorResponse error = new CustomErrorResponse();
        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               @NotNull HttpHeaders headers,
                                                               @NotNull HttpStatusCode status,
                                                               @NotNull WebRequest webRequest) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError -> FieldError.getField() + ": " + FieldError.getDefaultMessage())
                .toList();
        var exceptionBody = new ValidationExceptionResponse("validation_exceptions", errors.toString());
        return handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, webRequest);
    }


}
