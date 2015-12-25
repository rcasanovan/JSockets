package jsockets.server.logic;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsockets.exceptions.LogicNoCompatibleExcepction;
import jsockets.exceptions.NumberPortExcepcion;
import jsockets.net.JSocket;
import jsockets.net.ServerJSocket;
import jsockets.util.UtilFunctions;

/**
 * Class to start the server thread
 * @author Ricardo Casanova
 * @version 1.1.0
 * @since JSockets 1.0.0
 */
public final class ServerThread extends Thread
{
    private static JSocket       clientSocket = null;
    private static ServerJSocket listeningSocket = null;
    private static Integer       servicePort = null;
    private static String        logicClass = null;
    private static boolean       threadDone = false;

    /**
     * Method to initialize the server thread
     * @param args arguments that should receive the method are as follows:
     * args[0] port number for connection
     * args[1] name of the class of logic to implement on the server which must implement
     * the @ServerLogic interface
     * @since JSockets 1.0.0
     */
    public ServerThread(String args[])
    {
        ServerThread.servicePort = null;

        if (UtilFunctions.isNumeric(args[0]))
            ServerThread.servicePort = Integer.parseInt(args[0]);
        else
            throw new NumberPortExcepcion();
        
        ServerThread.logicClass = args[1];
        
        try
        {
            Class logic = Class.forName(ServerThread.logicClass);
            ServerThread.threadDone = false;
        }
        catch (ClassNotFoundException cnfe)
        {
            System.err.println("Fatal error with the execute request class in server. \nThe class " + ServerThread.logicClass +
                        " is not in the right package... \nPlease verify and try again...");
                        
            ServerThread.threadDone = true;
        }
    }
    
    /**
     * Method to show the server message
     * @param start the parameter indicates whether the message should be displayed 
     * at the beginning or end of the connection:
     * @since JSockets 1.0.0
     */
    private static void showServerMessage(boolean start)
    {
        System.out.println(" ---------------  ");
        System.out.println("|  -----------  |");
        System.out.println("| |           | |");
        if (start)
            System.out.println("| |  ^     ^  | |");
        else
            System.out.println("| |  X     X  | |");
        System.out.println("| |     -     | |");
        if (start)
            System.out.println("| |    ---    | |");
        else
            System.out.println("| |     O     | |");
        System.out.println("|  -----------  |");
        System.out.println("|               |");
        System.out.println("| --      ----- |");
        System.out.println("|               |");
        System.out.println(" --------------- ");
    }
    
    /**
     * Method to show initial welcome message
     * @since JSockets 1.0.0
     */
    private static void showInitialMessage()
    {
        UtilFunctions.showInitialMessage(false);
    }

    /**
     * Method that expects an incoming connection
     * @throws the method can handle exceptions of IOException type
     * @since JSockets 1.0.0
     */
    private static void waitConnection() throws IOException
    {
        System.out.println( "Waiting for connection..." );
        ServerThread.clientSocket = ServerThread.listeningSocket.accept(); //allow the server to accept connection
        System.out.println( "Connection established with: " + clientSocket.getHostName());
    }
    
    /**
     * Method to start the server thread
     * @since JSockets 1.0.0
     */
    @Override
    public void run() 
    {
        Class logic = null;
        
        if (!ServerThread.threadDone)
        {
            showInitialMessage();
        
            try
            {
                ServerThread.listeningSocket = new ServerJSocket(servicePort);
                ServerThread.showServerMessage(true);
                System.out.println("Server started");
                System.out.println("IP address: " + UtilFunctions.getCurrentIPAddress());
                System.out.println("Port: " + servicePort);

                while (!ServerThread.threadDone)
                {
                    waitConnection();

                    logic = Class.forName(ServerThread.logicClass);

                    if (logic.newInstance() instanceof ServerLogic)
                        new Connection(ServerThread.clientSocket, (ServerLogic) logic.newInstance());
                    else
                        throw new LogicNoCompatibleExcepction(logic.getName());
                }
            } 
            catch (Exception e)
            {
                if (logic == null)
                    System.err.println("Error with the execute request class in server. The class " + ServerThread.logicClass +
                            " is not in the right package. Please verify");

                System.err.println("Server shutdown X__X ...");
            }
        }
    }
    
    /**
     * Method to stop the server thread
     * @since JSockets 1.0.0
     */
    public void stopThread()
    {        
        ServerThread.showServerMessage(false);

        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if (ServerThread.listeningSocket != null)
                ServerThread.listeningSocket.closeConnection();
            
            ServerThread.threadDone = true;
        }
    }
}
