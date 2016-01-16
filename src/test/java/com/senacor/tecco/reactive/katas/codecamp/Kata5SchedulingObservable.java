package com.senacor.tecco.reactive.katas.codecamp;

import com.senacor.tecco.reactive.services.WikiService;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author Andreas Keefer
 */
public class Kata5SchedulingObservable {

    private final WikiService wikiService = new WikiService();

    @Test
    public void schedulingObservable() throws Exception {
        // 1. Benutze den WikiService#wikiArticleBeingReadObservable, der einen Stream von WikiArtikel Namen liefert,
        //    die gerade gelesen werden
        // 2. nim nur die ersten 20 Artikel
        // 3. lade und parse die Artikel
        // 4. Benutze jetzt den ratingService.rateObservable() und #countWordsObervable() und kombiniere beides im JSON-Format
        //    und gib das JSON auf der Console aus. Beispiel {"rating": 3, "wordCount": 452}
        // 5. messe die Laufzeit
        // 6. Fuege jetzt an passender Stelle in der Observable-Chain einen Schduler ein um die Ausführungszeit zu
        // verkürzen

        wikiService.wikiArticleBeingReadObservable(50, TimeUnit.MILLISECONDS);
    }

}