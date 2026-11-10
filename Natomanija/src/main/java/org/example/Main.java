package org.example;

import org.example.transposition.Transposition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        Transposition transposition = new Transposition();
        System.out.println(transposition.transpose("C ", "g", "C Am F G"));

    }
}
