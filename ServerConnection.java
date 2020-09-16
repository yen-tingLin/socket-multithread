import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


// To handle input from the server for all threads
public class ServerConnection implements Runnable {

    private Socket server;    
    // for input stream
    private BufferedReader bufferedReader;


    public ServerConnection(Socket server) throws IOException {
        this.server = server;
        this.bufferedReader = new BufferedReader(
            new InputStreamReader(server.getInputStream()));
    }    



    @Override
    public void run() {
        try {
            while(true) {
                String responseFromServer = bufferedReader.readLine();
                
                if(responseFromServer == null) break;
                System.out.println("[Client] Response from server : " + responseFromServer);                
            }
        } catch(IOException exception) {
            exception.printStackTrace();

        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
