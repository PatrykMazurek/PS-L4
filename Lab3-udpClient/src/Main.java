import java.io.IOException;
import java.net.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String host = "localhost";
        int port = 5504;

        try {
            DatagramSocket clientSocket = new DatagramSocket();

            byte[] sendData = "hej".getBytes();
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
            clientSocket.send(sendPacket);

            byte[] reciveData = new byte[1024];
            DatagramPacket recivePacket = new DatagramPacket(reciveData, reciveData.length);
            clientSocket.receive(recivePacket);

            String message = new String(recivePacket.getData());
            System.out.println(message);

            clientSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}