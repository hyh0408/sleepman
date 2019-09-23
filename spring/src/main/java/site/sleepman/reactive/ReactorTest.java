package site.sleepman.reactive;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class ReactorTest {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .log()
                .publishOn(Schedulers.newSingle("pub"))
                .subscribeOn(Schedulers.newSingle("sub"))
                .subscribe(System.out::println);
    }
}
