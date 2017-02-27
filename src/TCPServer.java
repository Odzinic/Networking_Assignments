import java.io.*;
import java.net.*;

public class TCPServer {

    public static void main(String argv[]) throws Exception {
        String clientSentence;

        int nPort = Integer.valueOf(argv[0]);

        ServerSocket welcomeSocket = new ServerSocket(nPort);

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());

            clientSentence = inFromClient.readLine();


            if (clientSentence.equals("TOUPPERCASE")) {

                outToClient.writeBytes("OK\n");

                String message = inFromClient.readLine();
                message = message.toUpperCase();
                outToClient.writeBytes(message + '\n');


            } else if (clientSentence.equals("TOLOWERCASE")) {

                outToClient.writeBytes("OK\n");

                String message = inFromClient.readLine();
                message = message.toUpperCase();
                outToClient.writeBytes(message + '\n');


            }

        }

    }

}
