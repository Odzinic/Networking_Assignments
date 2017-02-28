
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
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

        // Integer that will contain the desired port number to be used for the connection socket
        // retrieved from the first argument in the application call
        int nPort = Integer.valueOf(argv[0]);

        // BufferedReader object that will be used for retrieving user
        // inputs from a keyboard
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            // Stores a response from the user's keyboard which represents the command
            // that will be sent to a server
            command = inFromUser.readLine();

            if (command.equals("EXIT")) {
                break;
            }

            Socket clientSocket = new Socket("localhost", nPort);

            // A DataOutputStream object that creates an output stream to a server using the
            // connectionSocket Socket object
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            // A BufferedReader object that creates an input stream from a server using the
            // connectionSocket Socket object
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // String sent to a server that will contain which command to apply to the sent
            // message
            outToServer.writeBytes(command + '\n');


            // The client receives a string containing "OK" from the server to confirm the command
            // was received and is accepted
            okResponse = inFromServer.readLine();

            // The string okResponse is printed out and states "OK"
            System.out.println(okResponse);

            // Stores a response from the user's keyboard which represents the sentence
            // that will be sent to a server for modification
            sentence = inFromUser.readLine();

            // The client sends a string to the server containing the string that
            // will be modified
            outToServer.writeBytes(sentence + '\n');

            // The client receives a string containing the modified capitalized message
            // from the server
            modifiedSentence = inFromServer.readLine();

            // The modifiedSentence string is printed along with "FROM SERVER:"
            System.out.println("FROM SERVER: " + modifiedSentence);


            // The client socket is closed using the close() method
            clientSocket.close();

        }

    }

}
