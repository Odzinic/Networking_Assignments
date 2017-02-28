
import sun.awt.image.BufferedImageDevice;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPClient {

    public static void main(String argv[]) throws Exception {

        // String that will contain the "OK" response received from a server
        String okResponse;

        // String that will contain the command being sent to a server
        String command;

        // String that will contain a message that will be sent to and
        // modified by a server
        String sentence;

        // String that will contain a modified version of sentence that
        // will be received from a server
        String modifiedSentence;

        // A string containing the address of the server
        String address = argv[0];

        // Integer that will contain the desired port number to be used for the connection socket
        // retrieved from the first argument in the application call
        int nPort = Integer.valueOf(argv[1]);

        // Integer containing an integer value for the port for the non-persistent connection
        int rPort = 5768;

        // BufferedReader object that will be used for retrieving user
        // inputs from a keyboard
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = new Socket(address, nPort);

        // A DataOutputStream object that creates an output stream to a server using the
        // connectionSocket Socket object
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        // A BufferedReader object that creates an input stream from a server using the
        // connectionSocket Socket object
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));



        while (true) {

            // Stores a response from the user's keyboard which represents the command
            // that will be sent to a server
            command = inFromUser.readLine();

            if (command.equals("EXIT")) {
                break;
            }

            // String sent to a server that will contain which command to apply to the sent
            // message
            outToServer.writeBytes(command + '\n');


            // The client receives a string containing "OK" from the server to confirm the command
            // was received and is accepted
            okResponse = inFromServer.readLine();

            // The string okResponse is printed out and states "OK"
            System.out.println(okResponse);

            // A ServerSocket object that listens for incoming connections on the port rPort
            ServerSocket clientwelcomeSocket = new ServerSocket(rPort);

            // A string containing the ip address of the client
            String clientAddress = clientSocket.getLocalAddress().getHostAddress();

            // Writes a string containing the client address to the server
            outToServer.writeBytes(clientAddress + '\n');

            // Writes a string containing a port rPort
            outToServer.writeBytes(rPort + "\n");

            // A Socket object that contains a connection socket from the server
            Socket connectionSocket =  clientwelcomeSocket.accept();

            // A DataOutputStream object that creates an output stream to a server using the
            // connectionSocket Socket object
            DataOutputStream transferToServer = new DataOutputStream(connectionSocket.getOutputStream());

            // A BufferedReader object that creates an input stream from a server using the
            // connectionSocket Socket object
            BufferedReader transferFromServer = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            // Stores a response from the user's keyboard which represents the sentence
            // that will be sent to a server for modification
            sentence = inFromUser.readLine();

            // The client sends a string to the server containing the string that
            // will be modified
            transferToServer.writeBytes(sentence + '\n');

            // The client receives a string containing the modified capitalized message
            // from the server
            modifiedSentence = transferFromServer.readLine();

            // The modifiedSentence string is printed along with "FROM SERVER:"
            System.out.println("FROM SERVER: " + modifiedSentence);


            // The client socket is closed using the close() method
            connectionSocket.close();
            clientwelcomeSocket.close();

        }

        clientSocket.close();

    }

}
