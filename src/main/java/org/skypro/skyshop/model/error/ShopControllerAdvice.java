package org.skypro.skyshop.model.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException
            (NoSuchProductException e) {
        return new ResponseEntity<>(new ShopError("404", "No Such Product Found"), HttpStatusCode.valueOf(404));
    }
}
