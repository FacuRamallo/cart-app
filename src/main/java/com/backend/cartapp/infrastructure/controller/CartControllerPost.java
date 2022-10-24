package com.backend.cartapp.infrastructure.controller;

import com.backend.cartapp.application.CreateCart;
import com.backend.cartapp.application.CreateCartCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.CREATED;

@Controller
public class CartControllerPost {
    private final CreateCart createCart;

    public CartControllerPost(CreateCart createCart) {
        this.createCart = createCart;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> create(@RequestBody CreateCartDTO createCartDTO) {
        CreateCartCommand command = commandFromDTO(createCartDTO);

        String cartId = createCart.execute(command);

        return ResponseEntity.ok()
                .header("content-type","application/json")
                .body("{" +
                        "\"result\"= \"Cart created\","+
                        "\"cart-ID\"= "+cartId+","+
                        "}");
    }

    private CreateCartCommand commandFromDTO(CreateCartDTO createCartDTO) {
        return new CreateCartCommand(createCartDTO.productDtoList);
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
