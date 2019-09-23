package site.sleepman.reactive;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ObservableTest {
    static class IntObservable extends Observable implements Runnable {

        @Override
        public void run() {
            for(int i=1; i<=10; i++) {
                setChanged();
                notifyObservers(i);
            }
        }
    }
    public static void main(String[] args) {
        Observer observer = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        };


        IntObservable intObservable = new IntObservable();
        intObservable.addObserver(observer);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(intObservable);
        executorService.shutdown();
    }

}
