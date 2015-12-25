package jsockets.server.logic;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import jsockets.net.JSocket;
import jsockets.util.UtilFunctions;

/**
 * Class through a thread is responsible for managing each request received
 * by the server to be managed and apply the corresponding operation
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class Connection extends Thread
{
    private ObjectInputStream   in = null;
    private ObjectOutputStream  out = null;
    private Socket              clientSocket;
    private ServerLogic         logic;

    /**
     * Method to initialize the connection
     * @param clientSocket the client socket that is connecting
     * @param logicClass class to handle the logical part
     * @throws IOException
     * @since JSockets 1.0.0
     */
    public Connection(JSocket clientSocket, ServerLogic logicClass) throws IOException
    {
        this.clientSocket = clientSocket.getClientSocket();
        this.logic = logicClass;
        in = new ObjectInputStream(this.clientSocket.getInputStream());
        out = new ObjectOutputStream(this.clientSocket.getOutputStream());
        
        this.start();
    }

    /**
     * Method to start the connection
     * @since JSockets 1.0.0
     */
    @Override
    public void run()
    {
        Object data;
        byte [] result = null;
       
        try
        {
            data = UtilFunctions.byteArrayToObject((byte[]) in.readObject());
          
            result = logic.executeOperation(data);

            out.writeObject(result);
            out.flush();
            clientSocket.close();
        }
        catch (ClassNotFoundException cnfE)
        {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, cnfE);
        }
        catch (IOException ioE) {
            ioE.printStackTrace();
        }
    }
}