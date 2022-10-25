package com.backend.cartapp.infrastructure.controller;

import com.backend.cartapp.application.UpdateCart;
import com.backend.cartapp.application.UpdateCartCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartControllerPut {
    private final UpdateCart updateCart;

    public CartControllerPut(UpdateCart updateCart) {
        this.updateCart = updateCart;
    }

    @PutMapping("/cart")
    public ResponseEntity<String> update(@RequestBody UpdateCartDTO updateCartDTO) {
        UpdateCartCommand command = commandFromDTO(updateCartDTO);

         updateCart.execute(command);

        return ResponseEntity.ok()
                .header("content-type","application/json")
                .body("{" +
                        "\"result\": \"cart updated correctly\","+
                        "\"cart-ID\": \""+""+"\","+
                        "}");
    }

    private UpdateCartCommand commandFromDTO(UpdateCartDTO updateCartDTO) {
        return new UpdateCartCommand(updateCartDTO.cartId, updateCartDTO.productDtoList);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleException(RuntimeException exception){
        return ResponseEntity.badRequest()
                .header("content-type","application/json")
                .body("{" +
                        "\"exception\"= "+exception.getCause().getClass().getSimpleName()+","+
                        "\"message\"= "+exception.getMessage()+","+
                        "}");
    }
}
