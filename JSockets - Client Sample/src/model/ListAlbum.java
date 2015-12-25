/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ricardocasanova
 */
public class ListAlbum implements Serializable
{
    private List <Album> listAlbums;
    
    public ListAlbum()
    {
        this.listAlbums = new ArrayList();
    }
    
    public boolean addAlbum(Album a)
    {
        return this.listAlbums.add(a);
    }
    
    public boolean removeAlbum(Album a)
    {
        return this.listAlbums.remove(a);
    }
    
    public Iterator iterator()
    {
        return this.listAlbums.iterator();
    }
    
    public void printAllAlbums()
    {
        Iterator i = this.listAlbums.iterator();
        Album a;
        
        System.out.println("LIST ALBUMS:");
        System.out.println("--------------------------");        
        
        while (i.hasNext())
        {
            a = (Album) i.next();
            
            System.out.println("Album name: " + a.getName());
        }
        
        System.out.println("--------------------------"); 
    }   
}
