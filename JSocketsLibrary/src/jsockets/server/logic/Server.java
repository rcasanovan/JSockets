package jsockets.server.logic;

/**
 * Class to start the server. You passed the class that will contain the
 * logic of the server which must implement the interface @ServerLogic
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class Server
{
    private static ServerThread serverThread = null;

    /**
     * Method to start the server
     * @param args arguments that should receive the method are as follows:
     * args[0] port number for connection
     * args[1] name of the class of logic to implement on the server which must implement
     * the @ServerLogic interface
     * @since JSockets 1.0.0
     */
    public static void main(String args[]) 
    {
        Server.serverThread = new ServerThread(args);
        Server.serverThread.start();
    }
    
    /**
     * Method to shutdown the server
     * @since JSockets 1.0.0
     */
    public static void shutdown()
    {
        Server.serverThread.stopThread();
    }
}