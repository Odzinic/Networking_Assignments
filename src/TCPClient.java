
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClient {

    public static void main(String argv[]) throws Exception {
        String okResponse;
        String command;
        String sentence;
        String modifiedSentence;

        int nPort = Integer.valueOf(argv[0]);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

        while (true) {

            command = inFromUser.readLine();
            if (command.equals("EXIT")) {
                break;
            }

            Socket clientSocket = new Socket("localhost", nPort);

            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            outToServer.writeBytes(command + '\n');


            okResponse = inFromServer.readLine();

            System.out.println(okResponse);

            sentence = inFromUser.readLine();

            outToServer.writeBytes(sentence + '\n');

            modifiedSentence = inFromServer.readLine();

            System.out.println("FROM SERVER: " + modifiedSentence);


            clientSocket.close();

        }

    }

}
