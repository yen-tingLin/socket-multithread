import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {

        Socket connToServer = new Socket(SERVER_IP, SERVER_PORT);
              
        ServerConnection serverConnection = new ServerConnection(connToServer);                     
        System.out.println("[Client] Ready to receive from server.");                    
              
        
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        // autoflush : true
        PrintWriter outputFromClient = new PrintWriter(connToServer.getOutputStream(), true);   
        System.out.println("[Client] Ready to send to server.");

        new Thread(serverConnection).start();

        while(true) {
            System.out.println("> ");
            // this is blocking code, wait until input from keyboard
            String inputFromKeyboard = keyboard.readLine();

            // stop the communication
            if(inputFromKeyboard.equals("q")) break; 
            outputFromClient.println(inputFromKeyboard);
        }

        connToServer.close();
    }
    
}
