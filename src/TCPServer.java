import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String argv[]) throws Exception {

        // String that will contain the command received from the client
        String clientCommand;

        // String that will contain the sentence received from the client that will be modified
        String clientSentence;

        // String that will contain the modified sentence
        //Example: Sentence will be in all uppercase or all lowercase depending on chosen command
        String modifiedSentence;

        // Integer that will contain the desired port number to be used for the connection socket
        // retrieved from the first argument in the application call
        int nPort = Integer.valueOf(argv[0]);

        int rPort;

        // A ServerSocket object that is used to establish a connection with the client using
        // the port number nPort
        ServerSocket welcomeSocket = new ServerSocket(nPort);


        while (true) {

            // A Socket object that is created once the accept() function is called on the
            // welcomeSocket ServerSocket object
            Socket handshakeSocket = welcomeSocket.accept();

            // A BufferedReader object that creates an input stream from a client using the
            // connectionSocket Socket object
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(handshakeSocket.getInputStream()));

            // A DataOutputStream object that creates an output stream to a client using the
            // connectionSocket Socket object
            DataOutputStream outToClient = new DataOutputStream(handshakeSocket.getOutputStream());


            while (true) {

                // String received from the client that will contain which command to apply to the received
                // message

                clientCommand = inFromClient.readLine();

                if (clientCommand == null){
                    break;
                }



                // Conditional that checks if the client command string equals the string "TOUPPERCASE"
                // and if true, enters the condition nest
                if (clientCommand.equals("TOUPPERCASE")) {

                    // The server send a string containing "OK" to the client to confirm the command
                    // was received and is accepted
                    outToClient.writeBytes("OK\n");

                    String clientAddress = inFromClient.readLine();
                    rPort = Integer.valueOf(inFromClient.readLine());

                    Socket serverSocket = new Socket(clientAddress, rPort);

                    DataOutputStream transferToClient = new DataOutputStream(serverSocket.getOutputStream());
                    BufferedReader transferFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

                    // The server received a string from the client containing the string that
                    // will be modified
                    clientSentence = transferFromClient.readLine();

                    // The clientSentence string is modified to contain all uppercase values
                    // using the toUpperCase() method and stored in the modifiedSentence string
                    modifiedSentence = clientSentence.toUpperCase();

                    // The server send a string containing the modified capitalized message
                    // to the client
                    transferToClient.writeBytes(modifiedSentence + '\n');

                    // Conditional that checks if the client command string equals the string "TOLOWERCASE"
                    // and if true, enters the condition nest

                } else if (clientCommand.equals("TOLOWERCASE")) {

                    // The server send a string containing "OK" to the client to confirm the command
                    // was received and is accepted
                    outToClient.writeBytes("OK\n");

                    String clientAddress = inFromClient.readLine();
                    rPort = Integer.valueOf(inFromClient.readLine());

                    Socket serverSocket = new Socket(clientAddress, rPort);

                    DataOutputStream transferToClient = new DataOutputStream(serverSocket.getOutputStream());
                    BufferedReader transferFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

                    // The server received a string from the client containing the string that
                    // will be modified
                    clientSentence = transferFromClient.readLine();

                    // The clientSentence string is modified to contain all lowercase values
                    // using the toUpperCase() method and stored in the modifiedSentence string
                    modifiedSentence = clientSentence.toLowerCase();

                    // The server send a string containing the modified capitalized message
                    // to the client
                    transferToClient.writeBytes(modifiedSentence + '\n');


                }

            }


        }

    }
}
