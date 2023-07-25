package com.hidden.godfather.customer.util;

public class CpfValidator {

    public boolean isValid(final String cpf) {
        if (cpf == null || cpf.trim().length() == 0)
            return false;

        String cpfNumber = extractNumbers(cpf);

        if (!sizeIsValid(cpfNumber))
            return false;

        if (hasSameDigits(cpfNumber))
            return false;

        String cpfGerado = cpfNumber.substring(0, 9);
        cpfGerado = cpfGerado.concat(generateDigit(cpfGerado));
        cpfGerado = cpfGerado.concat(generateDigit(cpfGerado));

        return cpfGerado.equals(cpfNumber);
    }

    private String extractNumbers(String value) {
        return value.replace("-", "").replace(".", "");
    }

    private boolean sizeIsValid(String cpf) {
        return cpf.length() == 11;
    }

    private boolean hasSameDigits(String cpf) {
        char primDig = '0';
        char[] charCpf = cpf.toCharArray();

        for (char c : charCpf) {
            if (c != primDig) return false;
        }

        return true;
    }

    private String generateDigit(String cpf) {
        int digit = 0;
        int mult = cpf.length() + 1;
        char[] charCpf = cpf.toCharArray();

        for (int i = 0; i < cpf.length(); i++) {
            digit += (charCpf[i] - 48) * mult--;
        }

        if (digit % 11 < 2) {
            digit = 0;
        } else {
            digit = 11 - digit % 11;
        }

        return String.valueOf(digit);
    }
}
