import java.io.*;
import java.net.*;

public class multiClient {

    public static void main(String args[]) {

        final int PORT;

        ServerSocket serverSocket = null;

        //If the client does not enter the port number.
        if (args.length != 1) {
            System.out.println("PORT not identified");
            System.exit(1);
        }

        //Converts port number from String to an Integer(int).
        PORT = Integer.parseInt(args[0]);

        try {

            //Creating a server socket on the port number that has been entered by the user.
            //Using the port number entered will be used for accepting the connections requests from the clients.
            serverSocket = new ServerSocket(PORT);

            while (true) {

                //Waiting for the clients to connect
                Socket socket = serverSocket.accept();

                //Starting a new thread for serving a client. 
                new Thread(new multiServer(socket)).start();

            }

            //Handling execption
        } catch (IOException ex) {
            System.out.println("Error creating server socket: " + ex.getMessage());
            System.exit(2);
        }
    }
}
