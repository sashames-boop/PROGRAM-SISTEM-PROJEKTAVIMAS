package org.example.transpose;

import org.example.transposition.Transposition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TranspositionTest {
    protected int transposition;
    protected String startKey, endKey, progression;
    Transposition t = new Transposition();

    //test 1 - good
    @Test
    protected void transposeFromCtoE() {
        String actual = t.transpose("C", "E", "C Am F G");
        assertEquals("E C#m A B", actual);


//        startKey = "C"; endKey = "E"; progression = "C Am F G";
//        transposition = t.transposeIndex(startKey, endKey);
//
//        System.out.println("Starting key = " +  startKey + " | Ending key = " + endKey + " | Progression = " + progression);
//        String result = t.transpose(startKey, endKey, progression);
//        Character symbol = ' ';
//        if(transposition > 0) {
//            symbol = '+';
//        } else if(transposition < 0) {
//            symbol = '-';
//        }
//        System.out.println("Transposition result = " + result + " |" + symbol + transposition);
    }

    @Test
    //test 2 - on edge
    protected void transposeFromFtoG() {
        String actual = t.transpose("F#", "G", "F# D#m B C#");
        assertEquals("G Em C D", actual);

//        startKey = "F#"; endKey = "G"; progression = "F# D#m B C#";
//        transposition = t.transposeIndex(startKey, endKey);
//
//        System.out.println("Starting key = " +  startKey + " | Ending key = " + endKey + " | Progression = " + progression);
//        String result = t.transpose(startKey, endKey, progression);
//        Character symbol = ' ';
//        if(transposition > 0) {
//            symbol = '+';
//        } else if(transposition < 0) {
//            symbol = '-';
//        }
//        System.out.println("Transposition result = " + result + " |" + symbol + transposition);
    }

    @Test
    //test 3 - with an error
    protected void transposeWithError() {
        IllegalArgumentException error = assertThrows(
                IllegalArgumentException.class,
                () -> t.transpose("F#", "G", "F# BS B C#")
        );

        assertTrue(error.getMessage().contains("BS"));

//        startKey = "F#"; endKey = "G"; progression = "F# D#m BC C#";
//        transposition = t.transposeIndex(startKey, endKey);
//
//        System.out.println("Starting key = " +  startKey + " | Ending key = " + endKey + " | Progression = " + progression);
//        String result = t.transpose(startKey, endKey, progression);
//        Character symbol = ' ';
//        if(transposition > 0) {
//            symbol = '+';
//        } else if(transposition < 0) {
//            symbol = '-';
//        }
//        System.out.println("Transposition result = " + result + " |" + symbol + transposition);
    }

    @Test
    protected void transposeWith() {}
}
