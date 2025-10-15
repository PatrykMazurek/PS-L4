import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {
            ServerSocket server = new ServerSocket(5050);
            while (true){
                System.out.println("Oczekuje na klienta... ");
                Socket client = server.accept();
                new Thread(new MyServer(client)).start();
            }
//            server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}