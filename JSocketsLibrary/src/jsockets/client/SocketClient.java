package jsockets.client;

import java.io.*;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import jsockets.util.UtilFunctions;

/**
 * Class that contains the request made by the client to the server via
 * the protocol specified
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class SocketClient
{
    private Socket              client;
    private ObjectOutputStream  out;
    private ObjectInputStream   in;
    
    /**
     * Class constructor
     * @since JSockets 1.0.0
     */
    public SocketClient()
    {
        client  = null;
        out     = null;
        in      = null;
 
        showInitialMessage();
    }
    
    /**
     * Class constructor
     * @param showMessage boolean to show the initial message
     * @since JSockets 1.0.0
     */
    public SocketClient(boolean showMessage)
    {
        client  = null;
        out     = null;
        in      = null;
        
        if (showMessage)
            showInitialMessage();
    }
    
    /**
     * Method to get the client output stream
     * @return The client output stream
     * @since JSockets 1.0.0
     */
    public OutputStream getOutputStream()
    {
        OutputStream result = null;
        
        try
        {
            result =  this.client.getOutputStream();
        }
        catch (IOException e)
        {
            result = null;
            System.err.println("Error getting the outputstream from client");
        }
        
        return result;
    }
    
    /**
     * Method to get the client input stream
     * @return The client input stream
     * @since JSockets 1.0.0
     */
    public InputStream getInputStream()
    {
        InputStream result;
        
        try
        {
            result = this.client.getInputStream();
        }
        catch (IOException e)
        {
            result = null;
            System.err.println("Error getting the inputstream from client");
        }
        
        return result;
    }
    
    /**
     * Method to show initial welcome message
     * @since JSockets 1.0.0
     */
    private void showInitialMessage()
    {
        UtilFunctions.showInitialMessage(true);
    }
    
    /**
     * Method to execute the request to the server with an answer
     * @param message Object to send to the specified protocol
     * @param host destination host
     * @param port destination port number
     * @return Message returned by the server
     * @since JSockets 1.0.0
     */
    public byte [] executeRequestWithAnswer(Object message, String host, Integer port)
    {
        byte [] result = null;

        try
        {
            connectToTheServer(host, port);

            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());

            sendData(message);

            result = readData();
        }
        catch (EOFException eofE)
        {
           System.err.println("The client terminates the connection");
        }
        // process problems that can happen when communicating with the server
        catch (IOException ioE)
        {
           ioE.printStackTrace();
        }
        finally
        {
            closeConnection();
            return result;
        }
    }
    
    /**
     * Method to execute the request to the server without an answer
     * @param message Object to send to the specified protocol
     * @param host destination host
     * @param port destination port number
     * @since JSockets 1.0.0
     */
    public void executeRequestWithoutAnswer(Object message, String host, Integer port)
    {
        try
        {
            connectToTheServer(host, port);

            out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            in = new ObjectInputStream(client.getInputStream());

            sendData(message);
        }
        catch (EOFException eofE)
        {
           System.err.println("The client terminates the connection");
        }
        // process problems that can happen when communicating with the server
        catch (IOException ioE)
        {
           ioE.printStackTrace();
        }
        finally
        {
            closeConnection();
        }
    }

    /**
     * Method to establish the connection to the server
     * @param host destination host
     * @param port destination port number
     * @throws the method can handle exceptions of IOException type
     * @since JSockets 1.0.0
     */
    private void connectToTheServer(String host, Integer port) throws IOException, ConnectException
    {
        System.out.println("Establishing connection...");
        try
        {
            client = new Socket(InetAddress.getByName(host), port);
            System.out.println("The client is connected to: " + client.getInetAddress().getHostName());
        }
        catch (ConnectException cE)
        {
            System.err.println("Error in the connection with the server");
        }       
    }

    /**
     * Method to close the connection to the server
     */
    private void closeConnection()
    {
        try
        {
            client.close();
        }
        catch(IOException ioE)
        {
            ioE.printStackTrace();
        }
    }

    /**
     * Method to send messages to the server
     * @param message Object to send to the specified protocol
     * @throws the method can handle exceptions of IOException type
     * @since JSockets 1.0.0
     */
    private void sendData(Object message) throws IOException
    {
        byte [] stringMessage = UtilFunctions.objectToByteArray(message);

        out.writeObject(stringMessage);
        out.flush();

        System.out.println("The message has been sent to the server");
    }

    /**
     * Method to read messages from the server
     * @throws the method can handle exceptions of IOException type
     * @return Byte array that the server sends
     * @since JSockets 1.0.0
     */
    private byte [] readData() throws IOException
    {
        byte [] resultMessage;

        resultMessage = null;

        try
        {
            resultMessage = (byte []) in.readObject();
        }
        finally
        {
            return resultMessage;
        }
    }
}