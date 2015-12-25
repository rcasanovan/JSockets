package jsockets.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that defines the behavior of an ServerJSocket object type
 * @author Ricardo Casanova
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class ServerJSocket
{
    private ServerSocket listeningSocket = null;
    
    /**
     * Method to initialize a Socket server client
     * @param port the connection port
     * @since JSockets 1.0.0
     */
    public ServerJSocket(int port)
    {
        Integer conectionPort = (Integer) port;
        try
        {
            listeningSocket = new ServerSocket(conectionPort);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * Method to accept a new connection
     * @throws IOException
     * @return JSocket object
     * @since JSockets 1.0.0
     */
    public JSocket accept() throws IOException
    {
        JSocket socket = new JSocket();
        Socket clientSocket;
        
        clientSocket = this.listeningSocket.accept();
        socket.setClientSocket(clientSocket);
        
        return socket;
    }
    
    /**
     * Method to close the actual connection
     * @since JSockets 1.0.0
     */
    public void closeConnection()
    {
        if (this.listeningSocket != null)
        {
            try
            {
                this.listeningSocket.close();
                System.err.println("JSockets connection finished...");
            } 
            catch(IOException ioE)
            {
                ioE.printStackTrace();
            }
        }
    }
}