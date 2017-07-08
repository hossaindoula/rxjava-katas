package com.senacor.codecamp.reactive.katas.codecamp.rxjava2;

import com.senacor.codecamp.reactive.services.WikiService;
import com.senacor.codecamp.reactive.util.DelayFunction;
import com.senacor.codecamp.reactive.util.FlakinessFunction;
import com.senacor.codecamp.reactive.katas.KataClassification;
import io.reactivex.Observable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.senacor.codecamp.reactive.katas.KataClassification.Classification.*;
import static com.senacor.codecamp.reactive.util.FlakinessFunction.failCountDown;

/**
 * @author Andreas Keefer
 */
public class Kata7aResilience {

    @Test
    @KataClassification(mandatory)
    public void timeout() throws Exception {
        // 1. use fetchArticleObservableWithTimeout and add there a timeout of 500ms.
        // 2. verify this behavior with tests

        WikiService wikiService = WikiService.create(DelayFunction.staticDelay(1000));

        fetchArticleObservableWithTimeout(wikiService, "42")
            .timeout(500, TimeUnit.MILLISECONDS)
            .subscribe(next -> System.out.print(next));
    }

    private Observable<String> fetchArticleObservableWithTimeout(WikiService wikiService, String articleName) {
        return wikiService.fetchArticleObservable(articleName);
    }

    @Test
    @KataClassification(hardcore)
    public void retry() throws Exception {
        // 3. when fetchArticleObservableWithTimeout fails, retry twice with a delay of 1 second
        // 4. verify this behavior with tests

        WikiService wikiService = WikiService.create(DelayFunction.staticDelay(400),
                failCountDown(1));

        fetchArticleObservableWithTimeout(wikiService, "42")
                .retryWhen(attempts -> Observable.timer(1, TimeUnit.SECONDS));
                }


    @Test
    @KataClassification(nightmare)
    public void ambiguous() throws Exception {
        // 5. We can do better! Take a look at the amb() operator to beat the “flakiness” and speed up
        //    fetching articles.

        WikiService wikiService = WikiService.create(DelayFunction.withRandomDelay(200, 1000));

        fetchArticleObservableWithTimeout(wikiService, "42");
    }
}
