package jsockets.server.logic;

/**
 * Contract required to implement the logic on the server
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public interface ServerLogic
{
    /**
     * Signed with the operation to make the call to the server
     * @param arg String format argument according to protocol message
     * @return Byte array with the response operation
     */
    public byte [] executeOperation(Object arg);
}