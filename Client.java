import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import java.io.InputStreamReader;

public class Client {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    public static void main(String[] args) throws IOException {

        Socket connToServer = new Socket(SERVER_IP, SERVER_PORT);
        
        System.out.println("[Client] ready to receive from server.");
        BufferedReader inputFromServer = new BufferedReader(
                            new InputStreamReader(connToServer.getInputStream()));
        
        // this is blocking code, wait until receive response from server
        String responseFromServer = inputFromServer.readLine();
        // show pop-up dialog
        JOptionPane.showMessageDialog(null, responseFromServer);

        connToServer.close();
    }
    
}
