package com.backend.cartapp.infrastructure.controller.cartControllerDelete;

import com.backend.cartapp.application.deleteCart.DeleteCart;
import com.backend.cartapp.application.getCart.CartResponseDTO;
import com.backend.cartapp.application.getCart.GetCart;
import com.backend.cartapp.application.getCart.GetCartCommand;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartControllerDelete {
    private final DeleteCart getCart;

    private final ObjectMapper objectMapper;

    public CartControllerDelete(DeleteCart deleteCart) {
        this.getCart = deleteCart;
        this.objectMapper = new ObjectMapper();
    }

    @DeleteMapping("/cart/{id}")
    public void get(@PathVariable("id") String id)  {

    }


    @ExceptionHandler({CartNotFoundException.class})
    public ResponseEntity<String> handleException(CartNotFoundException exception){
        return ResponseEntity.badRequest()
                .header("content-type","application/json")
                .body("{" +
                        "\"exception\": "+exception.getClass().getSimpleName()+","+
                        "\"message\": "+exception.getMessage()+","+
                        "}");
    }
}
