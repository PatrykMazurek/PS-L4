import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket = new DatagramSocket(5504);
            System.out.println("nasłuchuje na wiadomosci...");
            byte[] reciveData = new byte[1024];
            DatagramPacket recivePacket = new DatagramPacket(reciveData, reciveData.length);
            while (true){
                serverSocket.receive(recivePacket);

                SocketAddress address = recivePacket.getSocketAddress();
                String message = new String(recivePacket.getData());

                System.out.println("wiadomość od klienta " + message);

                byte[] sendData = message.toUpperCase().getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address);
                serverSocket.send(sendPacket);
                if (message.trim().equals("q")){
                    break;
                }
            }


            serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}