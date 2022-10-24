package com.backend.cartapp.infrastructure.controller;

import com.backend.cartapp.application.CartResponseDTO;
import com.backend.cartapp.application.GetCart;
import com.backend.cartapp.application.GetCartCommand;
import com.backend.cartapp.domain.Cart;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartControllerGet {
    private final GetCart getCart;

    private final ObjectMapper objectMapper;

    public CartControllerGet(GetCart getCart) {
        this.getCart = getCart;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/cart/{id}")
    public ResponseEntity<String> get(@PathVariable("id") String id) throws CartNotFoundException {
        GetCartCommand command = new GetCartCommand(id);

        CartResponseDTO cart = getCart.execute(command);

        return ResponseEntity.ok()
                .header("content-type","application/json")
                .body("{" +
                        "\"cart-ID\": \""+cart.cartId+"\","+
                        "\"products\": "+mapToString(cart)+
                        "}");
    }

    private String mapToString(CartResponseDTO cart) {
        try {
            return objectMapper.writeValueAsString(cart.productDtoList);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
         return null;
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