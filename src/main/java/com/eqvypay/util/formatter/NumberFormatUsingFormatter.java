package com.eqvypay.util.formatter;

import java.text.DecimalFormat;

public class NumberFormatUsingFormatter implements NumberFormatter {

    @Override
    public String formatNumber(Float number) {
        return new DecimalFormat("#.##").format(number);
    }
}
