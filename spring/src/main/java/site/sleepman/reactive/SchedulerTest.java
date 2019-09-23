package site.sleepman.reactive;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class SchedulerTest {
    public static void main(String[] args) {
        Publisher<Integer> publisher = sub -> {
            sub.onSubscribe(new Subscription() {
                @Override
                public void request(long n) {
                    Stream.iterate(1, i -> i + 1).limit(5).forEach(value -> sub.onNext(value));
                }

                @Override
                public void cancel() {

                }
            });
        };

        publisher.subscribe(new Subscriber<Integer>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.info("onNext : {}", integer);
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
