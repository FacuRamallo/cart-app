package com.backend.cartapp.domain;

import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DescriptionTest {

    @Test
    public void should_create_new_description() {
        String validText = "ABC 123";

        Description description = assertDoesNotThrow(() -> new Description(validText));

        assertEquals(validText, description.getText());
    }

    @Test
    public void should_fail_for_invalid_characters() {
        String invalidText = "asdasd234234`´@<>-.,¿?/=+*";

        Throwable exception = assertThrows(InvalidDescriptionException.class ,() -> new Description(invalidText));

        String expectedMessage = "Product description must contain only alphanumerical characters";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}
