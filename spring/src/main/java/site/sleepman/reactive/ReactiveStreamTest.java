package site.sleepman.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import site.sleepman.zookeeper.Executor;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReactiveStreamTest {
    public static void main(String[] args) {
        Iterable<Integer> it = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());
        Publisher<Integer> publisher = getIntegerPublisher(it);
        Subscriber<Integer> subscriber = getSubscriber();
        publisher.subscribe(subscriber);
    }

    private static Subscriber<Integer> getSubscriber() {
        return new Subscriber<Integer>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("on subscribe");
                this.subscription = s;
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("on next : " + integer);
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("on error");
            }

            @Override
            public void onComplete() {
                System.out.println("on complete");
            }
        };
    }

    private static Publisher<Integer> getIntegerPublisher(Iterable<Integer> it) {
        return s -> {
            Iterator<Integer> i = it.iterator();
            s.onSubscribe(new Subscription() {
                //backpressure
                @Override
                public void request(long n) {
                    long a = 0;
                    try {
                        while (a++ < n) {
                            if (i.hasNext())
                                s.onNext(i.next());
                            else {
                                s.onComplete();
                                break;
                            }
                        }
                    } catch (Exception e) {
                        s.onError(e);
                    }
                }

                @Override
                public void cancel() {

                }
            });
        };
    }
}
