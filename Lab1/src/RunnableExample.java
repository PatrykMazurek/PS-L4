public class RunnableExample implements Runnable{

    public RunnableExample(){
        System.out.println("Uruchomienie klasy z wątkiem Runnable");
    }

    @Override
    public void run() {
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
}
