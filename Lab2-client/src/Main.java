import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 5050);

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner scanner = new Scanner(System.in);
            String line = "";
            System.out.println("Nawiązano połączenie z serwerem, możesz przesłać teskt");
            while(true){
                line = scanner.nextLine();
                writer.println(line);
                if (line.trim().equals("q")){
                    System.out.println(reader.readLine());
                    break;
                }
                System.out.println(reader.readLine());
            }

            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}