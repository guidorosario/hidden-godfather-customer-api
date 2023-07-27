package com.hidden.godfather.customer.util;

import com.hidden.godfather.customer.exception.ValidatorException;
import com.hidden.godfather.customer.model.enums.DocumentType;

public class DocumentUtils {


    public static DocumentType validateDocument(String documentNumber) {
        if(new CpfValidator().isValid(documentNumber))
            return DocumentType.CPF;
        else if (new CnpjValidator().isValid(documentNumber))
            return DocumentType.CNPJ;
        else throw  new ValidatorException(400, "Numero de Documento Invalido", "Digite um numero de documento valido");


    }

    public static String validateDocumentNumber(String documentNumber) {
        if(new CpfValidator().isValid(documentNumber))
            return documentNumber;

        else if (new CnpjValidator().isValid(documentNumber))
            return documentNumber;

        else throw  new ValidatorException(400, "Numero de Documento Invalido", "Digite um numero de documento valido");


    }
}
