package com.shuai.bootmapstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BootMapStructApplication {

    public static void main(String[] args) {
        // 17.10
        // 12.42
        // 15.30
        // 15.46
        // 16.00
        // 19.22
        // 15.30
        // 19.22
        // 14.49
        // 15.30

//        BigDecimal bigDecimal1 = new BigDecimal("17.10");
//        BigDecimal bigDecimal2 = new BigDecimal("12.42");
//        BigDecimal bigDecimal3 = new BigDecimal("15.30");
//        BigDecimal bigDecimal4 = new BigDecimal("15.46");
//        BigDecimal bigDecimal5 = new BigDecimal("16.00");
//        BigDecimal bigDecimal6 = new BigDecimal("19.22");
//        BigDecimal bigDecimal7 = new BigDecimal("14.49");
//        BigDecimal bigDecimal8 = new BigDecimal("15.30");
//        BigDecimal bigDecimal9 = new BigDecimal("15.30");
//        BigDecimal bigDecimal10 = new BigDecimal("19.22");
//        BigDecimal add = bigDecimal1.add(bigDecimal2).add(bigDecimal3).add(bigDecimal4).add(bigDecimal5).add(bigDecimal6).add(bigDecimal7).add(bigDecimal8).add(bigDecimal9).add(bigDecimal10);
//        System.out.println("金额 ： ----> "+add.toString());

        SpringApplication.run(BootMapStructApplication.class, args);
    }

}
