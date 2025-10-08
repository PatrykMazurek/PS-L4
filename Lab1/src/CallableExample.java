import java.util.Random;
import java.util.concurrent.Callable;

public class CallableExample implements Callable<Integer> {

    public CallableExample(){
        System.out.println("Uruchamiami klasę z Callable");
    }

    @Override
    public Integer call() throws Exception {
        int number = 0;
        Random rand = new Random();
        for (int i =0; i<5; i++){
            number += rand.nextInt(450);
            System.out.println("Kolejny krok losowania " + Thread.currentThread().getName());
            Thread.sleep(rand.nextInt(500,1000));
        }
        return number;
    }
}
