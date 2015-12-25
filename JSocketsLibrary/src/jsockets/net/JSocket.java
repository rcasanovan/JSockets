package jsockets.net;

import java.net.Socket;

/**
 * Class that defines the behavior of an JSocket object type
 * @author Ricardo Casanova
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class JSocket
{
    private Socket clientSocket = null;
    
    /**
     * Method to initialize a JSocket object
     * @since JSockets 1.0.0
     */
    protected JSocket()
    {
        this.clientSocket = new Socket();
    }

    /**
     * Method to get the socket client
     * @return Socket object
     * @since JSockets 1.0.0
     */
    public Socket getClientSocket()
    {
        return clientSocket;
    }

    /**
     * Method to set the socket client
     * @since JSockets 1.0.0
     */
    protected void setClientSocket(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
    }
    
    /**
     * Method to get socket client host name
     * @since JSockets 1.0.0
     */
    public String getHostName()
    {
        return this.clientSocket.getInetAddress().getHostName();
    }
}