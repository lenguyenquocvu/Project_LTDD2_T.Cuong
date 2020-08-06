package com.example.quanlydiemsinhvien.validators;

import java.util.regex.Pattern;

public class NameValidator {
    private Pattern pattern;
    private static final String HOTEN_PATTERN = "^[a-zA-Z\\p{L}\\s*]{1,50}$";

    public NameValidator() {
        pattern = Pattern.compile(HOTEN_PATTERN);
    }

    public boolean validate(final String hoten) {
        return pattern.matcher(hoten).matches();
    }
}
