
import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class multiServer implements Runnable {

    private final Socket socket;

    public multiServer(Socket socket) {
        this.socket = socket;
    }

    public static void listallCommand(ResultSet allBookingsResults, PrintWriter netOutput) {
        try {
            //Sending all bookings result to the client.
            while (allBookingsResults.next()) {
                netOutput.println(allBookingsResults.getString(1) + " " + allBookingsResults.getString(2) + " " + allBookingsResults.getString(3)
                        + " " + allBookingsResults.getString(4) + " " + allBookingsResults.getString(5)
                        + " " + allBookingsResults.getString(6) + " " + allBookingsResults.getString(7));

                netOutput.flush();
            }

            //Handling execption
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void listptCommand(ResultSet listptResults, PrintWriter netOutput) {
        try {
            //Sending personal trainer bookings results to the client.
            while (listptResults.next()) {
                netOutput.println(listptResults.getString(1) + " " + listptResults.getString(2) + " " + listptResults.getString(3)
                        + " " + listptResults.getString(4) + " " + listptResults.getString(5)
                        + " " + listptResults.getString(6) + " " + listptResults.getString(7));

                netOutput.flush();
            }

            //Handling execption
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void listclientCommand(ResultSet listclientResults, PrintWriter netOutput) {
        try {
            //Sending clients bookings results to the client.
            while (listclientResults.next()) {
                netOutput.println(listclientResults.getString(1) + " " + listclientResults.getString(2) + " " + listclientResults.getString(3)
                        + " " + listclientResults.getString(4) + " " + listclientResults.getString(5)
                        + " " + listclientResults.getString(6) + " " + listclientResults.getString(7));

                netOutput.flush();
            }

            //Handling execption
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void listdayCommand(ResultSet listdayResults, PrintWriter netOutput) {
        try {
            //Sending bookings by day results to the client.
            while (listdayResults.next()) {
                netOutput.println(listdayResults.getString(1) + " " + listdayResults.getString(2) + " " + listdayResults.getString(3)
                        + " " + listdayResults.getString(4) + " " + listdayResults.getString(5)
                        + " " + listdayResults.getString(6) + " " + listdayResults.getString(7));

                netOutput.flush();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addCommand(Scanner netInput, Statement addStatement, PrintWriter netOutput) {
        try {
            //Details required for add bookings.
            String addBookingID = netInput.next();
            String addClientID = netInput.next();
            String addPersonalTrainerID = netInput.next();
            String addFocusID = netInput.next();
            String addStartDate = netInput.next();
            String addStartTime = netInput.next();
            String addEndTime = netInput.next();

            //Add Query - enter add with all the required add details as one command.
            addStatement.executeUpdate("INSERT INTO Bookings VALUES('" + addBookingID
                    + "', '" + addClientID
                    + "', '" + addPersonalTrainerID
                    + "', '" + addFocusID + "' , '" + addStartDate
                    + "', '" + addStartTime
                    + "', '" + addEndTime + "') ");

            //Conformation of success.
            netOutput.println("Booking Added successfully");
            netOutput.flush();

            //Handling execption
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void updateCommand(Scanner netInput, Statement updateStatement, PrintWriter netOutput) {
        try {
            //Details required for update bookings.
            String updateBookingID = netInput.next();
            String updateClientBookingID = netInput.next();
            String updatePersonalTrainerID = netInput.next();
            String updateFocusID = netInput.next();
            String updateSessionDate = netInput.next();
            String updateSessionStartTime = netInput.next();
            String updateSessionEndTime = netInput.next();

            //Update Query - enter update with all the required update details as one command.
            String UpdateBookingDetailsQuery = "UPDATE Bookings SET clientNo = '"
                    + updateClientBookingID + "', personalTrainerNo = '"
                    + updatePersonalTrainerID + "', focusNo = '"
                    + updateFocusID + "', sessionDate = '"
                    + updateSessionDate + "', sessionStart = '"
                    + updateSessionStartTime + "', sessionEnd = '"
                    + updateSessionEndTime + "' WHERE bookingID = '"
                    + updateBookingID + "'";

            //Updates the booking details.
            updateStatement.executeUpdate(UpdateBookingDetailsQuery);

            //Conformation of success.
            netOutput.println("Booking details updated successfully");
            netOutput.flush();

            //Handling execption
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteCommand(Scanner netInput, Statement deleteStatement, PrintWriter netOutput) {
        try {
            //Details required to delete bookings.
            String enterBookingID = netInput.next();

            //Delete Query - enter delete with the booking id as one command.
            String deleteBookingQuery = "DELETE FROM Bookings WHERE bookingId = '" + enterBookingID + "'";

            //Delates the booking by the given booking id.
            deleteStatement.executeUpdate(deleteBookingQuery);

            //Confirmation of success
            netOutput.println("Booking deleted successfully");
            netOutput.flush();

            //Handle exeception
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void run() {
        try {

            //Sends the results to the cleint.
            PrintWriter netOutput
                    = new PrintWriter(socket.getOutputStream());

            //Recives the input from the client.
            Scanner netInput
                    = new Scanner(socket.getInputStream());

            try {

                try {
                    //Connection for the SQL Database.
                    Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/gymManagement", "root", "");

                    //Starts using the databse here.
                    while (netInput.hasNextLine()) {

                        //Command that is entered by the user. 
                        String userInput = netInput.next();

                        //Lists of all the commands.
                        String listallCommand = "LISTALL";
                        String listptCommand = "LISTPT";
                        String listclientCommand = "LISTCLIENT";
                        String listdayCommand = "LISTDAY";
                        String deleteCommand = "DELETE";
                        String addCommand = "ADD";
                        String updateCommand = "UPDATE";

                        //If the user enters the command LISTALL.
                        if (listallCommand.equals(userInput)) {

                            //Query for listing all the bookings in the bookings table.
                            String listAllBookingsQuery = "SELECT * FROM Bookings";

                            //Creating a Statement.
                            Statement statementForListallCommand = connect.createStatement();

                            //Creating a ResultsSet to get all the results.
                            ResultSet allBookingsResults = statementForListallCommand.executeQuery(listAllBookingsQuery);

                            //This method is used for getting all the bookings results from the database.
                            //All the bookings reuslts are sent to the client.
                            listallCommand(allBookingsResults, netOutput);

                            //Sends a blank line to the client to break the loop running.
                            netOutput.println("");
                            netOutput.flush();

                            //If the user enters the command LISTPT.
                        } else if (listptCommand.equals(userInput)) {

                            //Input a personal Trainer Id.
                            String personalTrainerID = netInput.next();

                            //Query for all the personal trainers bookings accroding to the given personal trainer id.
                            String listptQuery = "Select * From Bookings Where personalTrainerNo = '" + personalTrainerID + "'";

                            //Creating a Statement.
                            Statement listptQueryStatement = connect.createStatement();

                            //Creating a ResultsSet to get all the results.
                            ResultSet listptResults = listptQueryStatement.executeQuery(listptQuery);

                            //This method is used for getting all the personal trainers bookings accroding to the given personal trainer id.
                            //Results are sent to the client.
                            listptCommand(listptResults, netOutput);

                            //Sends a blank line to the client to break the loop running.
                            netOutput.println("");
                            netOutput.flush();

                            //If the user enters the LISTCLIENT command.
                        } else if (listclientCommand.equals(userInput)) {

                            //Input a client Id.
                            String clientId = netInput.next();

                            //Query for all the clients bookings accroding to the given client id.
                            String listClientQuery = "Select * From Bookings Where clientNo = '" + clientId + "'";

                            //Creating a Statement.
                            Statement listclientStatement = connect.createStatement();

                            //Creating a ResultsSet to get all the results.
                            ResultSet listclientResults = listclientStatement.executeQuery(listClientQuery);

                            //This method is used for getting all the clients bookings accroding to the given client id.
                            //Results are sent to the client.
                            listclientCommand(listclientResults, netOutput);

                            //Sends a blank line to the client to break the loop running.
                            netOutput.println("");
                            netOutput.flush();

                            //If the user enters the LISTDAY command.
                        } else if (listdayCommand.equals(userInput)) {

                            //Input a date.
                            String listDayId = netInput.next();

                            //Query for all the session date bookings accroding to the given session date.
                            String listDayQuery = "Select * From Bookings Where sessionDate = '" + listDayId + "'";

                            //Creating a Statement.
                            Statement listdayStatement = connect.createStatement();

                            //Creating a ResultsSet to get all the results.
                            ResultSet listdayResults = listdayStatement.executeQuery(listDayQuery);

                            //This method is used for getting all the clients bookings accroding to the given session date.
                            //Results are sent to the client.
                            listdayCommand(listdayResults, netOutput);

                            //Sends a blank line to the client to break the loop running.
                            netOutput.println("");
                            netOutput.flush();

                            //If the user enters the DELETE command.
                        } else if (deleteCommand.equals(userInput)) {

                            //Creates a statement.
                            Statement deleteStatement = connect.createStatement();

                            //Deletes the booking.
                            deleteCommand(netInput, deleteStatement, netOutput);

                            //If the user enters the ADD command.
                        } else if (addCommand.equals(userInput)) {

                            //Creates a statement.
                            Statement addStatement = connect.createStatement();

                            //Adds a booking.
                            addCommand(netInput, addStatement, netOutput);

                            //If the user enters the UPDATE command.
                        } else if (updateCommand.equals(userInput)) {

                            //Creates a statement.
                            Statement updateStatement = connect.createStatement();

                            //Updates a booking.
                            updateCommand(netInput, updateStatement, netOutput);

                        }

                    }

                    //Handling exception
                    //When the server is disconnected then it stops the client from crashing.
                } catch (java.util.NoSuchElementException ex) {
                    System.out.println("A Client Disconnects");
                    socket.close();
                }

                //Handling exeception
            } catch (SQLException exp) {
                System.out.println("SQL Error: " + exp.getMessage());
            } //End of using database here.

            //Handling exeception
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
