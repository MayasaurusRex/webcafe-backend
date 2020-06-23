package com.example.CafeGoogooExample;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FactorialTest {
    @Autowired
    private Factorial factorial;

    @Test
    public void doFactorial() {
        assertThat(factorial.doFactorial(4)).isEqualTo(24);
        assertThat(factorial.doFactorial(3)).isEqualTo(6);
    }
}
