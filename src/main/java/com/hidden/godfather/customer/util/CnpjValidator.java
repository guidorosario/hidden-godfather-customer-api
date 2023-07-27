package com.hidden.godfather.customer.util;

public class CnpjValidator {

    public boolean isValid(final String cnpj) {
        if (cnpj == null || cnpj.trim().length() == 0)
            return false;

        String cnpjNumber = extractNumbers(cnpj);

        if (!sizeIsValid(cnpjNumber))
            return false;

        if (hasSameDigits(cnpjNumber))
            return false;

        String cpfGerado = cnpjNumber.substring(0, 12);
        cpfGerado = cpfGerado.concat(generateDigit(cpfGerado));
        cpfGerado = cpfGerado.concat(generateDigit(cpfGerado));

        return cpfGerado.equals(cnpjNumber);
    }

    private String extractNumbers(String value) {
        return value.replace("-", "")
                .replace(".", "")
                .replace("/", "");
    }

    private boolean sizeIsValid(String cnpj) {
        return cnpj.length() == 14;
    }

    private boolean hasSameDigits(String cnpj) {
        char primDig = '0';
        char[] charcnpj = cnpj.toCharArray();

        for (char c : charcnpj) {
            if (c != primDig) return false;
        }

        return true;
    }

    private String generateDigit(String cnpj) {
        int digit = 0;
        int mult = 2;
        char[] charcnpj = cnpj.toCharArray();

        for (int i = cnpj.length()-1; i >= 0; i--) {
            int number = charcnpj[i] - 48;
            digit += number * mult++;
            if(mult > 9) mult = 2;
        }

        if (digit % 11 < 2) {
            digit = 0;
        } else {
            digit = 11 - digit % 11;
        }

        return String.valueOf(digit);
    }
}
