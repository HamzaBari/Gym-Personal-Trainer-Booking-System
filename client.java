import java.util.*;
import java.net.Socket;
import java.io.IOException;
import java.io.PrintWriter;

public class client {

    public static void listallCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("LISTALL")) {
            String listallCommand = "LISTALL";
            netOutput.println(listallCommand); //This gets the command LISTALL from the server.
            netOutput.flush();

	    System.out.println("");
	    System.out.println("-------------------All Bookings-----------------------");
	    System.out.println("");
	    System.out.println("BKID CLID PTID FSID Date       Start    End");
	    System.out.println("");
	    
            //This loop prints out all the results from the server, from the beggining to the end.
            while (netInput.hasNextLine()) {

                String serverResponse = netInput.nextLine();
                /* When the loop reaches the empty line then the while loop would break.
                 This is the reason why an empty line is sent after the end of results. */
                if (serverResponse.equals("")) {
		    System.out.println("");
		    System.out.println("------------------------------------------------------");
                    break;
                } else {		    
                    System.out.println(serverResponse); //Prints out all the results from the servers response.
                }

            }

        }
    }

    public static void listptCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("LISTPT")) {
            String personalTrainerId = usrInput.next();
            String listptCommand = "LISTPT " + personalTrainerId;
            netOutput.println(listptCommand); //This gets the command LISTPT with personal trainer id enterted by the user, from the server.
            netOutput.flush();

	    System.out.println("");
	    System.out.println("-------------Personal Trainers Bookings---------------");
	    System.out.println("");
	    System.out.println("BKID CLID PTID FSID Date       Start    End");
	    System.out.println("");

            //This loop prints out all the results from the server, from the beggining to the end.
            while (netInput.hasNextLine()) {

                String serverResponse = netInput.nextLine();

                /* When the loop reaches the empty line then the while loop would break.
                 This is the reason why an empty line is sent after the end of results. */
                if (serverResponse.equals("")) {
		    System.out.println("");
		    System.out.println("------------------------------------------------------");
                    break;
                } else {
                    System.out.println(serverResponse); //Prints out all the results from the servers response.
                }

            }

        }
    }

    public static void listclientCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("LISTCLIENT")) {
            String clientid = usrInput.next();
            String listclientCommand = "LISTCLIENT " + clientid;
            netOutput.println(listclientCommand); //This gets the command LISTCLIENT with client id enterted by the user, from the server.
            netOutput.flush();

	    System.out.println("");
	    System.out.println("-------------------Clients Bookings--------------------");
	    System.out.println("");
	    System.out.println("BKID CLID PTID FSID Date       Start    End");
	    System.out.println("");

            //This loop prints out all the results from the server, from the beggining to the end.
            while (netInput.hasNextLine()) {

                String serverResponse = netInput.nextLine();

                /* When the loop reaches the empty line then the while loop would break.
                 This is the reason why an empty line is sent after the end of results. */
                if (serverResponse.equals("")) {
		    System.out.println("");
		    System.out.println("------------------------------------------------------");
                    break;
                } else {
                    System.out.println(serverResponse); //Prints out all the results from the servers response.
                }

            }

        }
    }

    public static void listdayCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("LISTDAY")) {
            String dayid = usrInput.next();
            String listdayCommand = "LISTDAY " + dayid;
            netOutput.println(listdayCommand); //This gets the command LISTDAY with date enterted by the user, from the server.
            netOutput.flush();

	    System.out.println("");
	    System.out.println("-------------------Date Bookings----------------------");
	    System.out.println("");
	    System.out.println("BKID CLID PTID FSID Date       Start    End");
	    System.out.println("");

            //This loop prints out all the results from the server, from the beggining to the end.
            while (netInput.hasNextLine()) {

                String serverResponse = netInput.nextLine();

                /* When the loop reaches the empty line then the while loop would break.
                 This is the reason why an empty line is sent after the end of results. */
                if (serverResponse.equals("")) {
		    System.out.println("");
		    System.out.println("------------------------------------------------------");
                    break;
                } else {
                    System.out.println(serverResponse); //Prints out all the results from the servers response.
                }

            }

        }
    }

    public static void enterAddBookingsDetails(Scanner usrInput, PrintWriter netOutput) {
        //Details of the add booking details
        String addBookiingID = usrInput.next();
        String addClientId = usrInput.next();
        String addPersonalTrainerId = usrInput.next();
        String addFocusId = usrInput.next();
        String addStartDate = usrInput.next();
        String addStartTime = usrInput.next();
        String addEndTime = usrInput.next();

        //With the ADD all the other bookings details are also added next to each other as one command.
        String addCommand = "ADD "
                + addBookiingID
                + " " + addClientId
                + " " + addPersonalTrainerId
                + " " + addFocusId
                + " " + addStartDate
                + " " + addStartTime
                + " " + addEndTime
                + " " + "\n\n";

        netOutput.print(addCommand); //This sends the command ADD with all the booking details, to the server.
        netOutput.flush();
    }

    public static void addCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("ADD")) { //User enters the ADD command

	    System.out.println("");
	    System.out.println("--------------------Add Booking----------------------");
	    System.out.println("");

            //The command is sent to the server to update the system. 
            enterAddBookingsDetails(usrInput, netOutput);

            //Prints out a confirmation messge from the server. 
            System.out.println(netInput.nextLine());

	    System.out.println("");
	    System.out.println("-----------------------------------------------------");

        }
    }

    public static void enterUpdateBookingsDetails(Scanner usrInput, PrintWriter netOutput) {
        //Details of the update booking details
        String updateBookingId = usrInput.next();
        String updateClientid = usrInput.next();
        String updatePersonalTrainerId = usrInput.next();
        String updateFocusId = usrInput.next();
        String updateSessionDate = usrInput.next();
        String updateSessionStartTime = usrInput.next();
        String updateSessionEndime = usrInput.next();

        //With the UPDATE all the other bookings details are also added next to each other as one command.
        String updateCommand = "UPDATE "
                + updateBookingId + " "
                + updateClientid + " "
                + updatePersonalTrainerId + " "
                + updateFocusId + " "
                + updateSessionDate + " "
                + updateSessionStartTime + " "
                + updateSessionEndime + " " + "\n\n";

        netOutput.print(updateCommand); //This sends the command UPDATE with all the booking details, to the server.
        netOutput.flush();
    }

    public static void updateCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("UPDATE")) { //User enters the UPDATE command

	    System.out.println("");
	    System.out.println("--------------------Update Booking-------------------");
	    System.out.println("");

            //The command is sent to the server to update the system. 
            enterUpdateBookingsDetails(usrInput, netOutput);

            //Prints out a confirmation messge from the server. 
            System.out.println(netInput.nextLine());

	    System.out.println("");
	    System.out.println("-----------------------------------------------------");

        }
    }

    public static void enterDeleteBookingsDetails(Scanner usrInput, PrintWriter netOutput) {
        //Details of the delete booking details
        String deleteId = usrInput.next();

        //The DELETE command is used with the booking id e.g. DELETE BK01
        String deleteCommand = "DELETE "
                + deleteId + ""
                + "\n\n\n";

        netOutput.print(deleteCommand); //This sends the command DELETE with the booking id to the server.
        netOutput.flush();
    }

    public static void deleteCommand(String input, Scanner usrInput, PrintWriter netOutput, Scanner netInput) {
        if (input.equals("DELETE")) { //User enters the DELETE command

	    System.out.println("");
	    System.out.println("--------------------Delete Booking-------------------");
	    System.out.println("");

            //The command is sent to the server to update the system. 
            enterDeleteBookingsDetails(usrInput, netOutput);

            //Prints out a confirmation messge from the server. 
            System.out.println(netInput.nextLine());

	    System.out.println("");
	    System.out.println("-----------------------------------------------------");
        }
    }

    public static void helpGuide() { //This is the help guide for the users to undertand how the system works.
	System.out.println("");
        System.out.println("-----------------------------------------------Welcome to Help Guide--------------------------------------------------");
        System.out.println("");
        System.out.println("* Enter LISTALL command to get all the bookings information e.g. LISTALL");
        System.out.println("");
        System.out.println("* Enter LISTPT command to get the personal trainer booking information e.g. LISTPT PT01");
        System.out.println("");
        System.out.println("* Enter LISTCLIENT command to get the personal trainer booking information e.g. LISTCLIENT CL01");
        System.out.println("");
        System.out.println("* Enter LISTPT command to get the personal trainer booking information e.g. LISTDAY 2020-01-26");
        System.out.println("");
        System.out.println("* Enter ADD command to add a booking e.g. ADD BK01 CL01 PT02 FS03 2020-01-26 10:00:00 12:00:00");
        System.out.println("");
        System.out.println("* Enter UPDATE command to update an existing booking e.g. UPDATE BK01 CL03 PT04 FS03 2020-01-27 10:00:00 12:00:00");
        System.out.println("");
        System.out.println("* Enter DELETE command to delete a booking e.g. DELETE BK01");
        System.out.println("");
        System.out.println("* Enter exit to close the System.");
        System.out.println("");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
    }

    public static void systemInto() { //This has all the information regarding system intoduction.
        System.out.println("");
        System.out.println("\t-----------Gym Management System----------");
        System.out.println("");
        System.out.println("\tPlease use the help guide by typing 'help' \n\tif you are new to this System");
	System.out.println("");
	System.out.println("\t------------------------------------------");
	System.out.println("");
        System.out.println("");
        System.out.println("");
    }

    /*This is the information that is going to be displayed after every serverResponse the user enters. 
     This why the user would contantly know what they need to do.*/
    public static void userInteraction() {
        System.out.println("");
        System.out.print("> Please Enter a command, help, or exit: ");
        System.out.print("");
    }

    public static void help(String input) {
        if (input.equalsIgnoreCase("help")) { //if the user enters help
            helpGuide(); //The help guide manual displays
        }
    }

    public static void exit(String input) {
        if (input.equalsIgnoreCase("exit")) { //if the user enters exit
            System.out.println("Disconnecting from the server...");
            System.exit(0); //The client would dissconnect from the server
        }
    }

    public static void main(String args[]) {

        final String HOST;
        final int PORT;

        /* If the host and port number were given as commandline
         then use that as the port number */
        if (args.length == 2) {
            HOST = args[0];
            PORT = Integer.parseInt(args[1]);
        } else {
            HOST = "localhost";
            PORT = 7000;
        }

        Scanner netInput; //Used for getting thinges from the server.
        Scanner usrInput; //Used for the user input.
        PrintWriter netOutput; //prints the reuslts from the server.

        try (Socket socket = new Socket(HOST, PORT)) { //Creates the socket on the host and portname

            netInput = new Scanner(socket.getInputStream());
            usrInput = new Scanner(System.in);
            netOutput = new PrintWriter(socket.getOutputStream());

            try {

                /*The system introduction will be displayed right at the 
                 begginning of the system */
                systemInto();

                while (true) {

                    //After ever input this will ask the user what they want to do next.
                    userInteraction();

                    //name of the user input
                    String input = usrInput.next();

                    //This method will be called, When the user types LISTALL command
                    listallCommand(input, usrInput, netOutput, netInput);

                    //This method will be called, When the user types LISTPT command
                    listptCommand(input, usrInput, netOutput, netInput);

                    //This method will be called, When the user types LISTCLIENT command
                    listclientCommand(input, usrInput, netOutput, netInput);

                    //This method will be called, When the user types LISTDAY command
                    listdayCommand(input, usrInput, netOutput, netInput);

                    //This method will be called, When the user types ADD command
                    addCommand(input, usrInput, netOutput, netInput);

                    //This method will be called, When the user types UPDATE command
                    updateCommand(input, usrInput, netOutput, netInput);

                    //This method will be called, When the user types DELETE command
                    deleteCommand(input, usrInput, netOutput, netInput);

                    //This method display the help.
                    help(input);

                    //This method disconnects the client from the server and it closes the system.
                    exit(input);

                }

                //Handling exception
            } catch (java.util.NoSuchElementException ex) {
                System.out.println(ex.getMessage());
                System.exit(0);
            }

            //This closes all the connections for the socket, printwriters, and scanners.
            netInput.close();
            netOutput.close();
            usrInput.close();

            //Handling exception
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.exit(0);
        }

    }
}
