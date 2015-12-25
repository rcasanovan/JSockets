# JSockets

JSockets is a library developed in Java (and NetBeans) that allows management to encapsulate Sockets for network communication. With JSockets it is incredibly easy to develop projects under the client-server model.

The library was originally developed by the Engineer Gerardo Barcia to support development projects in the area of Algorithms and Programming III School of Engineering at the Andrés Bello Catholic University (UCAB) in Caracas, Venezuela. From version 2.0 I joined the project and since then I've been keeping and developing new features.

After years working with the library, I decided to release it as an open source project.

The goals of this code are:

* Abstracting Sockets implementation in Java
* Allow people who don't know the implementation of Sockets deploy applications on the client-server model very easily
* Allow programming teachers create more interesting and complex projects without the need for implementing Sockets

## Adding JSockets to your project

### Server

In order to create your JSockets server, you need to follow these steps:

* Add the JSockets library JAR file to you project
* Create a class that implements ServerLogic protocol
* This class must implement the 'executeOperation' method
* In this method, process the Object arg parameter (it depends from the object that you're using to exchange information between client and server)
* Use the byte[] return value from 'executeOperation' method to return anything you want to the client. You can use the UtilFunctions class in JSockets to transform objects if you need it
* On main class, configure a String array with two elements: server listening port and the class that implements the ServerLogic protocol (String[] parameters = {"7687", "controller.ManagementRequest"};)
* Execute 'main' method from JSockets 'Server' class with the String array parameter (Server.main(parameters);)

### Client

In order to create your JSockets client, you need to follow these steps:

* Add the JSockets library JAR file to you project
* Create a SocketClient object. If you want you can use a singleton object
* Configure the object tht you'll transfer to the server (a String, an array, a complex object)
* If you need an answer from the server, execute the method 'executeRequestWithAnswer' sending the object, the server IP and server port
* Process the server answer. You can use the UtilFunctions class in JSockets to transform objects if you need it

## Javadoc

## Support && contact

### Twitter

Follow me [@rcasanovan](http://twitter.com/rcasanovan) on twitter.
