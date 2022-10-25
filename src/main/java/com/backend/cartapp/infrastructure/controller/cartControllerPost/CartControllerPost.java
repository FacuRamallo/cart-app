package com.backend.cartapp.infrastructure.controller.cartControllerPost;

import com.backend.cartapp.application.createCart.CreateCart;
import com.backend.cartapp.application.createCart.CreateCartCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class CartControllerPost {
    private final CreateCart createCart;

    public CartControllerPost(CreateCart createCart) {
        this.createCart = createCart;
    }

    @PostMapping("/cart")
    public ResponseEntity<String> create(@RequestBody CartDTO cartDTO) {
        CreateCartCommand command = commandFromDTO(cartDTO);

        String cartId = createCart.execute(command);

        return ResponseEntity.ok()
                .header("content-type","application/json")
                .body("{" +
                        "\"result\"= \"Cart created\","+
                        "\"cart-ID\"= \""+cartId+"\","+
                        "}");
    }

    private CreateCartCommand commandFromDTO(CartDTO cartDTO) {
        return new CreateCartCommand(cartDTO.productDtoList);
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
