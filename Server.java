import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    private static final int PORT = 9090;

    private static String[] fruits = {"kiwi", "apple", "strawberry", "lemon", "grape"};
    private static String[] colors = {"green", "pink", "red", "white", "yellow"};
    
    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);

        System.out.println("[Server] Waiting for client connection...");
        Socket connToClient = listener.accept();

        System.out.println("[Server] Connected to client.");
        PrintWriter outputFromServer = new PrintWriter(
                            // autoflush : true to send messages as soon as 
                            // the print line statement is run, and don't need 
                            // to wait to send messages until the buffer is full
                            connToClient.getOutputStream(), true);

        outputFromServer.println(GetRandomPairs());

        System.out.println("[Server] Response sent to client. Closing.");
        
        connToClient.close();
        listener.close();
    }

    public static String GetRandomPairs() {
        // Math.random() returns number between 0 and 1
        String fruitRand = fruits[(int) (Math.random() * fruits.length)];
        String colorRand = colors[(int) (Math.random() * colors.length)];
        
        String display = colorRand + " " + fruitRand;
        return display;
    }
}
