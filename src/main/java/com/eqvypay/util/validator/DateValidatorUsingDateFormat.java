package com.eqvypay.util.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidatorUsingDateFormat implements DateValidator {
    private final String dateFormat;

    public DateValidatorUsingDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isDateValid(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("date format error=="+e);
            return false;
        }
        return true;
    }

    @Override
    public int getMonth(String month) {
        int i = 5;
        if(month.length() >= 3) {
            switch (month.toLowerCase().substring(0,3)) {
                case "jan":
                    return 1;
                case "feb":
                    return 2;
                case "mar":
                    return 3;
                case "apr":
                    return 4;
                case "may":
                    return 5;
                case "jun":
                    return 6;
                case "jul":
                    return 7;
                case "aug":
                    return 8;
                case "sep":
                    return 9;
                case "oct":
                    return 10;
                case "nov":
                    return 11;
                case "dec":
                    return 12;
                default:
                    return 0;
            }
        }
        else {
            return 0;
        }
    }
}
