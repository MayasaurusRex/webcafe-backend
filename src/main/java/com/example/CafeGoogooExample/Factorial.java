package com.example.CafeGoogooExample;

import org.springframework.stereotype.Component;

@Component
public class Factorial {

    public int doFactorial(int num) {
        int result = 1;
        for(int i = 1; i <= num; i++){
            result *= i;
        }
        return result;
    }
}
