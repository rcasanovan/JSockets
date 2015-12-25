package jsockets.exceptions;

/**
 * Not checked exception logic to specify when sent to the server does not
 * implement the required interface @ServerLogic
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class LogicNoCompatibleExcepction extends RuntimeException
{
    private String message;

    public LogicNoCompatibleExcepction()
    {
        message = "Compatibility error with the server logic";
    }

    public LogicNoCompatibleExcepction(String className)
    {
        message = "Compatibility error with the server logic. The class "
                + className + " must implement the ServerLogic interface";
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}