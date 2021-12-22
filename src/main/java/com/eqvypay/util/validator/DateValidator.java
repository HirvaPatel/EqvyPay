package com.eqvypay.util.validator;

public interface DateValidator {
    boolean isDateValid(String dateStr);
    int getMonth(String month);
}
