package com.trainerapp.calorie_calculator.exception.handler;

import com.trainerapp.calorie_calculator.exception.*;
import com.trainerapp.calorie_calculator.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Excepciones de tipo "NO ENCONTRADO"
    @ExceptionHandler({
            CustomIngredientNotFoundException.class,
            FoodNotFoundException.class,
            IngredientNotFoundException.class,
            MealNotFoundException.class,
            MeasurementUnitNotFoundException.class,
            MicronutrientContentNotFoundException.class,
            MicronutrientNotFoundException.class,
            RecipeNotFoundException.class,
            StepNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(RuntimeException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    // Excepciones de negocio: datos duplicados o reglas incumplidas
    @ExceptionHandler(DuplicatedMicronutrientContentException.class)
    public ResponseEntity<ErrorResponse> handleDuplicatedData(RuntimeException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    // Errores de validación de DTO (@Valid fallidos)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    // JSON mal formado o tipo de datos incompatible en el request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidJson(HttpMessageNotReadableException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    // IllegalArgument o IllegalState: cuando el flujo de la lógica falla
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgument(RuntimeException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    // NullPointer: bug interno
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> handleNullPointer(NullPointerException e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // Catch-All: para cualquier otra excepción no controlada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllOtherExceptions(Exception e, HttpServletRequest request) {
        return buildErrorResponse(e, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(Exception e, HttpStatus status, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                e.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, status);
    }
}