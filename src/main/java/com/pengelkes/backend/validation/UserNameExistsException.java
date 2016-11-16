package com.pengelkes.backend.validation;

/**
 * Created by pengelkes on 16.11.2016.
 */
public class UserNameExistsException extends Throwable
{
    public UserNameExistsException(final String message)
    {
        super(message);
    }
}
