package com.example.CafeGoogooExample;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
public class AllAboutCollectionsTest {

    public static final Logger LOG = LoggerFactory.getLogger(AllAboutCollectionsTest.class);

    @Test
    public void doStuff() {

        List<String> result = new ArrayList<>();
        result.add("One");
        result.add("Two");
        result.add("Three");
        result.add("One");
        LOG.info("With Duplicates: " + String.valueOf(result));
        Set<String> noDups = new HashSet<>(result);
        LOG.info("Without Duplicates: " + String.valueOf(noDups));

        List<String> result1 = new ArrayList<>();
        result1.add("Two");
        result1.add("Six");
        result1.add("Five");
        result1.add("Four");
        result1.add("One");
        noDups.addAll(result1);
        LOG.info("Ordered list: " + result1);

        LOG.info("More Without Duplicates: " + String.valueOf(noDups));

        Set<String> answer = new HashSet<>();
        for(String s: result) {
            if(result1.contains(s)){
                answer.add(s);
            }
        }
        LOG.info("Answer: " + String.valueOf(answer));
    }

}
