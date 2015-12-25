package jsockets.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jsockets.domain.ImageType;
import jsockets.exceptions.ImageTypeException;

/**
 * Class to store useful and common functions
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public final class UtilFunctions
{    
    private static final String VERSION = "1.0.0";
    private static final String DATE_RELEASE = "dec 2015";
    private static final String IMAGE_FORMAT  = "jpg";
    private static final String ERROR_MESSAGE = "Error - this function is not supported in .Net";
    
    private static final boolean COMPILE_FOR_JAVA = true;
    
    /**
     * Method to get the current version of JSockets
     * @return The current version
     * @since JSockets 1.0.0
     */
    public static String getCurrentVersion()
    {
        return VERSION;
    }
    
    /**
     * Method to get the date release of the actual version of JSockets
     * @return The date release of the actual version
     * @since JSockets 1.0.0
     */
    public static String getDateRelease()
    {
        return DATE_RELEASE;
    }
    
    /**
     * Method to get the image format (only jpg format is supported for now)
     * @return The image format
     * @since JSockets 1.0.0
     */
    public static String getImageFormat()
    {
        return IMAGE_FORMAT;
    }
    
    /**
     * Method to show initial welcome message
     * @param isClientApplication Boolean to determinate if it's running a JSockets Client or a JSockets Server
     * @since JSockets 1.0.0
     */
    public static void showInitialMessage(boolean isClientApplication)
    {
        System.out.println("|------------------------------------------------------------------------------|");
        if (isClientApplication)
            System.out.println("| JSockets© Library " + UtilFunctions.getCurrentVersion() + " (" + UtilFunctions.getDateRelease() +") - Client aplication                       |");
        else
            System.out.println("| JSockets© Library " + UtilFunctions.getCurrentVersion() + " (" + UtilFunctions.getDateRelease() +") - Server aplication                       |");
        System.out.println("| JSockets© is based on software with Sockets for .Net && Java technology      |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("| TM and © 2009-2015 Gerardo Barcia P. and Ricardo Casanova N.                 |");
        System.out.println("| All rights reserved                                                          |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println("| Developers:                                                                  |");
        System.out.println("| Engineer Gerardo Barcia P.         | Engineer Ricardo Casanova N.            |");
        System.out.println("| Web page: www.gerardobarcia.com    | Web page: www.ricardocasanova.me        |");
        System.out.println("| Email: gerardobarciap@gmail.com    | Email: ricardo.casanova@outlook.com     |");
        System.out.println("| Twitter: @gerardobarciap           | Twitter: @rcasanovan                    |");
        System.out.println("|                                    | http://www.youtube.com/user/liamkc      |");
        System.out.println("|------------------------------------------------------------------------------|");
        System.out.println();
    }
    
    /**
     * Method to get the initial welcome message
     * @param isClientApplication Boolean to determinate if it's running a JSockets Client or a JSockets Server
     * @return String String with the initial welcome message
     * @since JSockets 1.0.0
     */
    public static String getInitialMessage(boolean isClientApplication)
    {
        String message = "";
        message += "|------------------------------------------------------------------------------|\n";
        if (isClientApplication)
            message += "| JSockets© Library " + UtilFunctions.getCurrentVersion() + " (" + UtilFunctions.getDateRelease() +") - Client aplication                         |\n";
        else
            message += "| JSockets© Library " + UtilFunctions.getCurrentVersion() + " (" + UtilFunctions.getDateRelease() +") - Server aplication                         |\n";
        message += "| JSockets© is based on software with Sockets for .Net && Java technology      |\n";
        message += "|------------------------------------------------------------------------------|\n";
        message += "| TM and © 2009-2012 Gerardo Barcia and Ricardo Casanova                       |\n";
        message += "| All rights reserved                                                          |\n";
        message += "|------------------------------------------------------------------------------|\n";
        message += "| Developers:                                                                  |\n";
        message += "| Engineer Gerardo Barcia P.         | Specialist Engineer Ricardo Casanova N. |\n";
        message += "| Web page: www.gerardobarcia.com    | Web page: www.ricardocasanova.net       |\n";
        message += "| Email: gerardobarciap@gmail.com    | Email: rcasanov@ucab.edu.ve             |\n";
        message += "| Twitter: @gerardobarciap           | Twitter: @rcasanovan                    |\n";
        message += "|                                    | http://www.youtube.com/user/liamkc      |\n";
        message += "|------------------------------------------------------------------------------|\n";
        message += "\n";
        
        return message;
    }
    
    /**
     * Method to check if a string is number
     * @param string String to check
     * @return Boolean condition as the case
     * @since JSockets 1.0.0
     */
    public static boolean isNumeric(String string)
    {
        try 
        {
            Integer.parseInt(string);
            return true;
        } 
        catch (NumberFormatException nfe)
        {
            return false;
        }
    }

    /**
     * Method to convert a byte array to string
     * @param byteArray byte array to convert
     * @return The result of the conversion
     * @since JSockets 1.0.0
     */
    public static String byteArrayToString(byte[] byteArray)
    {
        String result = null;

        try 
        {
            result = new String(byteArray, "UTF8");
        }
        catch (UnsupportedEncodingException exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    /**
     * Method to convert a string to an array of bytes
     * @param string String to convert
     * @return Result of the conversion
     * @since JSockets 1.0.0
     */
    public static byte[] stringToByteArray(String string) 
    {
        return string.getBytes();
    }

    /**
     * method for get image type from image path
     * @param imagePath path of image
     * @return Type of image
     * @since JSockets 1.0.0
     */
    public static String getImageType(String imagePath) 
    {
        String result = null;
        if (imagePath.endsWith('.' + ImageType.JPG.getType())) 
            result = ImageType.JPG.getType();
        return result;
    }

    /**
     * method for validate de format suported in app
     * @param imagePath path of image
     * @throws ImageTypeException
     * @since JSockets 1.0.0
     */
    public static void validateImageType(String imagePath) 
    {
        String type = UtilFunctions.getImageType(imagePath);
        if (type == null)
            throw new ImageTypeException();
    }
    
    /**
     * Method for converting a file (represented by its path) in
     * an array of bytes
     * @param filePath path of the file
     * @return Result of the conversion
     * @since JSockets 1.0.0
     */
    public static byte[] fileToByteArray(String filePath)
    {
        if (COMPILE_FOR_JAVA)
        {
            File file = new File(filePath);
            byte[] bytes = null;

            try
            {
                FileInputStream fis = new FileInputStream(file);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];

                try 
                {
                    for (int readNum; (readNum = fis.read(buf)) != -1;)
                        bos.write(buf, 0, readNum);
                } 
                catch (IOException ioE) 
                {
                    System.err.println("\nFailed to transform the object");
                }
                bytes = bos.toByteArray();
            }
            catch (FileNotFoundException fnfE)
            {
                System.err.println("\nFailed to open the file " + filePath);
            }
            finally
            {
                return bytes;
            }
        }
        else
            System.err.println(ERROR_MESSAGE);
        
        return null;
    }
    
    /**
     * Method to construct a file from an array of bytes. If file is an image, 
     * the file's extension parameter is ignored and the file is generated in 
     * jpg format.
     * @param fileBytes Byte array that represents the file
     * @param path Path where the file will be saved
     * @param name Name will be given to the file (not including the format).
     * If you want to create an image called "test.jpg", this parameter should
     * only contain the string "test"
     * @param extension The file's extension (not for image files) without a dot (.)
     * @param isImageFile A boolean to indicate if you're creating an image or not 
     * @throws Exception
     * @since JSockets 1.0.0
     */
    public static void createFileFromByteArray(byte[] fileBytes, String path, String name, String extension, boolean isImageFile) throws Exception
    {
        if (COMPILE_FOR_JAVA)
        {
            String          filename;
            BufferedImage   bufferedImage = null;

            if (fileBytes != null)
            {
                try 
                {
                    if (isImageFile)
                    {
                        ByteArrayInputStream inputImage = new ByteArrayInputStream(fileBytes);
                        bufferedImage = ImageIO.read(inputImage);

                        filename = path + File.separatorChar + name + "." + ImageType.JPG.getType();
                    }
                    else  
                        filename = path + File.separatorChar + name + "." + extension;

                    
                    System.out.println("Creating the file: " + filename);
                    
                    FileOutputStream out = new FileOutputStream(filename);
     
                    if (isImageFile)
                        ImageIO.write(bufferedImage, ImageType.JPG.getType(), out);
                    else
                        out.write(fileBytes);
                    
                    out.close();
                }
                catch (IOException ioE) 
                {
                    System.err.println("\nReceived an unknown object type");
                }
            }
            else
                System.err.println("\nReceived a null object type for image");
        }
        else
            System.err.println(ERROR_MESSAGE);
    }

    /**
     * Method to convert an object of type Object in an array of bytes
     * @param object The object to be converted
     * @return Result of the conversion
     * @since JSockets 1.0.0
     */
    public static byte[] objectToByteArray(Object object)
    {
        if (COMPILE_FOR_JAVA)
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try 
            {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
            } catch (IOException ex) 
            {
                Logger.getLogger(UtilFunctions.class.getName()).log(Level.SEVERE, null, ex);
            } finally 
            {
                return baos.toByteArray();
            }
        }
        else
            System.err.println(ERROR_MESSAGE);
        
        return null;
    }

    /**
     * Method to convert a byte array to an object. It is important that the
     * object is serializable (that implements the Serializable interface)
     * @param bytes The array of bytes representing the object to convert
     * @return Object result of the conversion
     * @since JSockets 1.0.0
     */
    public static Object byteArrayToObject(byte[] bytes) 
    {
        Object object = null;

        if (COMPILE_FOR_JAVA)
        {
            try
            {
                object = new java.io.ObjectInputStream(new ByteArrayInputStream(bytes)).readObject();
            }
            catch (IOException ex) 
            {
                Logger.getLogger(UtilFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (ClassNotFoundException ex) 
            {
                Logger.getLogger(UtilFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
            System.err.println(ERROR_MESSAGE);

        return object;
    }
    
    /**
     * Method to get the current IP address
     * @return The current IP address
     * @since JSockets 1.0.0
     */
    public static String getCurrentIPAddress()
    {
        String ipAddress;
        
        ipAddress = "IP address not available";
        
        try
        {
            InetAddress IP = InetAddress.getLocalHost();
            ipAddress = IP.getHostAddress();
        }
        catch (UnknownHostException e)
        {
            System.err.println(e.getMessage());
        }
        
        return ipAddress;
    }
}