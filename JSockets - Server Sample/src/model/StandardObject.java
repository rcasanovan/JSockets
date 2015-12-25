/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author ricardocasanova
 */
public class StandardObject implements Serializable
{
    private String protocol;
    private Object object;

    public StandardObject(String protocol, Object object)
    {
        this.protocol = protocol;
        this.object = object;
    }

    public String getProtocol()
    {
        return protocol;
    }

    public void setProtocol(String protocol)
    {
        this.protocol = protocol;
    }

    public Object getObject()
    {
        return object;
    }

    public void setObject(Object object)
    {
        this.object = object;
    }
}
