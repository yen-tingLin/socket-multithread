import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


public class ClientHandler implements Runnable {

    // clientSocket : connection to server
    private Socket clientSocket;
    // for input stream
    private BufferedReader bufferedReader;
    // for output stream
    private PrintWriter printWriter;
    private ArrayList<ClientHandler> clientList;


    public ClientHandler(
        Socket clientSocket, ArrayList<ClientHandler> clientList) throws IOException 
    {
        this.clientSocket = clientSocket;
        this.bufferedReader = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        // autoflush : true
        this.printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
        this.clientList = clientList;
    }
        

    @Override
    public void run() {
        try {
            while(true) {
                String clientRequest = bufferedReader.readLine();
                if(clientRequest.contains("fruit")) {
                    printWriter.println(Server.GetRandomPairs());

                } else if(clientRequest.startsWith("hello")) {
                    int firstSpaceIdx = clientRequest.indexOf(" ");
                    if(firstSpaceIdx > 0) {
                        outToAllThreads(clientRequest.substring(firstSpaceIdx+1));
                    }
                } else {
                    printWriter.println("Type 'fruit' to get a random fruit.");
                }            
            } 
        } catch(IOException e) {
            System.err.println("IO exception in client handler. " + e.getStackTrace());
        
        } finally {
            printWriter.close();        

            try {
                bufferedReader.close();
                clientSocket.close();

            } catch(IOException e) {
                e.printStackTrace();
            }
    
        }

    }

    private void outToAllThreads(String message) {
        for(ClientHandler client : clientList) {
            client.printWriter.println(message);
        }
    }


}
