import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Rozpoczynam Pracę ");
                System.out.println(Thread.currentThread().getName());
                for (int i =0; i < 5; i++) {
                    System.out.println("numer: " + i );
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("koniec pracy wątka");
            }
        });
        // uruchopienie pojedyńczego wątku wywołanego z programu
//        th.start();
        // wywołanie wątku typu Runnable
//        RunnableExample runEx = new RunnableExample();
//        Thread th2 = new Thread(runEx);
//        th2.start();

        // wywołanie stałej puli wątków
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futureList = new ArrayList<>();

//        for (int i =0; i< 20; i++){
//            futureList.add(executorService.submit(new CallableExample()));
//        }
        // wywołanie dynamicznej puli wątków
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        int task = 20;
        int number = 0;
        while(threadPoolExecutor.getActiveCount() < 5){
            if (number < task) {
                futureList.add(executorService.submit(new CallableExample()));
                number += 1;
            }
            if (number == task - 1){
                break;
            }
        }
        // odbieranie danych z wątków przyz obiekt Future
        try {
            for (Future f : futureList){
                System.out.println("Wynik loswania: " + f.get(5000, TimeUnit.MILLISECONDS));
            }
        } catch (InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        // zakończenie pracy wątków
        executorService.shutdown();
        System.out.println("Zakończenie głównego programu");
    }
}