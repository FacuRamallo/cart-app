package com.backend.cartapp.domain;

import com.backend.cartapp.domain.exceptions.InvalidDescriptionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Description {
    private String text;

    public Description(String text) throws InvalidDescriptionException {
        //String alphaNumRegexPattern = "^[a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9-( )]+$");
        Matcher matcher = pattern.matcher(text);
        if(!matcher.matches()) {
            throw new InvalidDescriptionException();
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "\""+this.text+"\"";
    }
}
