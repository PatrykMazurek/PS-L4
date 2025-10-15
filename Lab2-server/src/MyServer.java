import java.io.*;
import java.net.Socket;

public class MyServer implements Runnable{

    private PrintWriter writer;
    private BufferedReader buffered;
    private Socket clientSocket;

    public MyServer(Socket s){
        clientSocket = s;
        try {
            writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
            buffered = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("nawiązałem połączenie z " + clientSocket.getInetAddress().getHostAddress());
    }


    @Override
    public void run() {
        String line = "";
        StringBuilder builder = new StringBuilder();
        while(true){
            try {
                line = buffered.readLine();
                if (line.trim().equals("q")){
                    writer.println("pa pa");
                    break;
                }
                builder.append(line);
                writer.println(builder.reverse());
                builder.delete(0, builder.length());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        disconnect();
    }

    public void disconnect(){
        if (!clientSocket.isClosed()){
            try {
                buffered.close();
                writer.close();
                clientSocket.close();
                System.out.println("zakończyłem połaćżenie ");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
