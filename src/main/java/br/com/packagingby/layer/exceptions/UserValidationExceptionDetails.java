package br.com.packagingby.layer.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UserValidationExceptionDetails extends ExceptionDetails{
    private final String fieldWithError;
    private final String fieldErrorDetails;
}
