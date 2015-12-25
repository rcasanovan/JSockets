/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import jsockets.server.logic.Server;

/**
 *
 * @author ricardocasanova
 */
public class Main
{
    public static void main(String [] args)
    {
        /* We define a String array in order to configure the server    */
        /* 1) Listening server port (String)                            */
        /* 2) Java Class to managment the request                       */
        String[] parameters = {"7687", "controller.ManagementRequest"};
        Server.main(parameters);
    }
}
