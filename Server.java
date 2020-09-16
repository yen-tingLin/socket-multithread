
import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 9090;

    private static String[] fruits = { "kiwi", "apple", "strawberry", "lemon", "grape" };
    private static String[] colors = { "green", "pink", "red", "white", "yellow" };

    private static ArrayList<ClientHandler> clientList = new ArrayList<>();
    private static ExecutorService pool = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws IOException {

        ServerSocket listener = new ServerSocket(PORT);
        
        try {
            while(true) {
                System.out.println("[Server] Waiting for client connection...");
                Socket connToClient = listener.accept();       
                System.out.println("[Server] Connected to client."); 
                
                ClientHandler clientThead = new ClientHandler(connToClient, clientList);
                clientList.add(clientThead);

                pool.execute(clientThead);
            }            
        } finally {
            listener.close();
        }


    }

    public static String GetRandomPairs() {
        // Math.random() returns number between 0 and 1
        String fruitRand = fruits[(int) (Math.random() * fruits.length)];
        String colorRand = colors[(int) (Math.random() * colors.length)];
        
        String display = colorRand + " " + fruitRand;
        return display;
    }
}
