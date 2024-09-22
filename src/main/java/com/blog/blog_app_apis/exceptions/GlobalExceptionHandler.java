package com.blog.blog_app_apis.exceptions;

import com.blog.blog_app_apis.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


// ye annotation controller me koi bhi exception ko handle karega globally
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ye function agar 'ResourceNotFoundException' aata h kisi bhi controller me tb usko handle karega globally.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFountExceptionHandler(ResourceNotFoundException e)
    {
        String message=e.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message,false), HttpStatus.NOT_FOUND);
    }

    // for handling exception that we get while validating the fields
    // i.e 'MethodArgumentNotValidException'.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex)
    {
        // phle wo sb field nikalna hoga jispe exception aa rha h with messages.
        // niklane ke bad usko map me store karenge

        Map<String, String> res = new HashMap<>();

        // phle erros sb niklange(list dega), then errors se field nikalenge(using lambda fn) isliye hmko phle typecast karna hoga.
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();  // getting filed name
            String message = error.getDefaultMessage();
            res.put(fieldName, message);
        });

        return new ResponseEntity<Map<String,String>>(res , HttpStatus.BAD_REQUEST);
    }
}
