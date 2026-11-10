package org.example.transposition;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Transposition {
    protected Map<Integer, String> mainNotes;
    private void initialize() {
        mainNotes =
                Map.ofEntries(Map.entry(0, "C"), Map.entry(1, "C#"),
                        Map.entry(2, "D"), Map.entry(3, "D#"),
                        Map.entry(4, "E"), Map.entry(5, "F"),
                        Map.entry(6, "F#"), Map.entry(7, "G"),
                        Map.entry(8, "G#"), Map.entry(9, "A"),
                        Map.entry(10, "A#"), Map.entry(11, "B"));
    }

    public Transposition() {
        initialize();
        
    }

    public String transpose(String startKey, String endKey, String progression) {
        if (startKey == null || endKey == null || progression == null || progression.isBlank()) {
            throw new IllegalArgumentException("Tonacijos ir akordu seka turi buti pateiktos.");
        }

        startKey = startKey.trim();
        endKey = endKey.trim();
        int startInd = findNoteIndex(startKey);
        int endInd = findNoteIndex(endKey);

        if (startKey.endsWith("m") != endKey.endsWith("m")) {
            throw new IllegalArgumentException("Mazorines ir minorines tonacijos tarpusavyje netransponuojamos.");
        }

        int transposition = transposeIndex(startKey, endKey);

        return transposeByStep(progression, transposition);

    }

    private int findNoteIndex(String chord) {
        if (chord.length() == 1) chord = chord.toUpperCase(Locale.ROOT);
        if (!chord.matches("[A-G]#?m?")) {
            throw new IllegalArgumentException("Neatpazintas akordas arba tonacija: " + chord);
        }

        String root = chord.endsWith("m") ? chord.substring(0, chord.length() - 1) : chord;
        for (Map.Entry<Integer, String> entry : mainNotes.entrySet()) {
            if (entry.getValue().equals(root)) {
                return entry.getKey();
            }
        }

        throw new IllegalArgumentException("Neatpazinta pagrindine nata: " + root);
    }

    public String transposeByStep(String progression, int transposition) {
        if (progression == null || progression.isBlank()) {
            throw new IllegalArgumentException("Akordu seka turi buti pateikta.");
        }

        List<String> notes = new ArrayList<>();
        for (String note : progression.trim().split("\\s+")) {
            findNoteIndex(note);
            notes.add(note);
        }

        StringBuilder result = new StringBuilder();
        for (String note : notes) {
            int noteInd = findNoteIndex(note);
            int resultInd = Math.floorMod(noteInd + transposition, 12);

            if (!result.isEmpty()) {
                result.append(" ");
            }
            result.append(mainNotes.get(resultInd));
            if (note.endsWith("m")) {
                result.append("m");
            }
        }

        return result.toString();
    }

    public Integer transposeIndex(String startKey, String endKey) {
        int startInd = findNoteIndex(startKey);
        int endInd = findNoteIndex(endKey);

        int transposition = Math.floorMod(endInd - startInd, 12);
        if (transposition > 6) {
            transposition -= 12;
        }
        return transposition;
    }
}
