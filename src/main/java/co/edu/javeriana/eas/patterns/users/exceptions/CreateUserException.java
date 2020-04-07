package co.edu.javeriana.eas.patterns.users.exceptions;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;

public class CreateUserException extends QuotationCoreException {

    public CreateUserException(EExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public CreateUserException(EExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

}