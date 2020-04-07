package co.edu.javeriana.eas.patterns.users.exceptions;

import co.edu.javeriana.eas.patterns.common.enums.EExceptionCode;
import co.edu.javeriana.eas.patterns.common.exceptions.QuotationCoreException;

public class AuthenticationException extends QuotationCoreException {

    public AuthenticationException(EExceptionCode exceptionCode) {
        super(exceptionCode);
    }

    public AuthenticationException(EExceptionCode exceptionCode, String causeMessage) {
        super(exceptionCode, causeMessage);
    }

}