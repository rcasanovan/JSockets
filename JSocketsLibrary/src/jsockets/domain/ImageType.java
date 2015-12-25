package jsockets.domain;

import jsockets.util.UtilFunctions;

/**
 * Enum Type for file formats suported
 * @author Ricardo Casanova
 * @author Gerardo Barcia
 * @version 1.0.0
 * @since JSockets 1.0.0
 */
public enum ImageType
{
    JPG(UtilFunctions.getImageFormat());
    private String type;

    /**
     * Method to set the image type
     * @param type the image type
     * @since JSockets 1.0.0
     */
    ImageType(String type)
    {
        this.type = type;
    }

    /**
     * Method to get the current image types
     * @return The current version
     * @since JSockets 1.0.0
     */
    public String getType()
    {
        return type;
    }
}