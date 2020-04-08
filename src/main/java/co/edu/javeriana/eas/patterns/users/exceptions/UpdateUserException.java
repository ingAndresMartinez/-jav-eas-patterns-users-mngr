package co.edu.javeriana.eas.patterns.users.exceptions;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;

public class UpdateUserException extends QuotationCoreException {

    public UpdateUserException(EExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public UpdateUserException(EExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

}