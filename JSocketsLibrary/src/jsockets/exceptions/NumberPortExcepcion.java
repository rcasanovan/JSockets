package jsockets.exceptions;

/**
 * Class to run the exception of non-numeric port number
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class NumberPortExcepcion extends RuntimeException
{
    private final String EXCEPTION_MESSAGE = "Failed to execute application, " +
            "the port must be numeric";

    @Override
    public String getMessage()
    {
        return EXCEPTION_MESSAGE;
    }
}