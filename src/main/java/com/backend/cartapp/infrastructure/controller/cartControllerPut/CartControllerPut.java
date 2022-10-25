package com.backend.cartapp.infrastructure.controller.cartControllerPut;

import com.backend.cartapp.application.updateCart.UpdateCart;
import com.backend.cartapp.application.updateCart.UpdateCartCommand;
import com.backend.cartapp.domain.exceptions.CartNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartControllerPut {
    private final UpdateCart updateCart;

    public CartControllerPut(UpdateCart updateCart) {
        this.updateCart = updateCart;
    }

    @PutMapping("/cart")
    public ResponseEntity<String> update(@RequestBody UpdateCartDTO updateCartDTO) throws CartNotFoundException {
        UpdateCartCommand command = commandFromDTO(updateCartDTO);

         updateCart.execute(command);

        return ResponseEntity.ok()
                .header("content-type","application/json")
                .body("{" +
                        "\"result\": \"cart updated correctly\""+
                        "}");
    }

    private UpdateCartCommand commandFromDTO(UpdateCartDTO updateCartDTO) {
        return new UpdateCartCommand(updateCartDTO.cartId, updateCartDTO.productDtoList);
    }

    @ExceptionHandler({RuntimeException.class, CartNotFoundException.class})
    public ResponseEntity<String> handleException(RuntimeException exception){
        return ResponseEntity.badRequest()
                .header("content-type","application/json")
                .body("{" +
                        "\"exception\"= "+exception.getCause().getClass().getSimpleName()+","+
                        "\"message\"= "+exception.getMessage()+","+
                        "}");
    }
}
