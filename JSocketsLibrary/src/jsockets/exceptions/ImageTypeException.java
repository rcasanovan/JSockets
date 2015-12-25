package jsockets.exceptions;

/**
 * ImageTypeException for unsoported type
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class ImageTypeException extends RuntimeException
{
    private final String EXCEPTION_MESSAGE = "Failed to execute application, "
            + "the image type is not soported for now";

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}