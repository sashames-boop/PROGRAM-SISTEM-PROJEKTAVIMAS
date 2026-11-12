package org.example.transposition;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranspositionController {
    private final Transposition transposition = new Transposition();

    @PostMapping("/api/transpose")
    public ResponseEntity<String> transpose(@RequestParam("progression") String progression, @RequestParam("steps") int steps){
        try{
            String result = transposition.transposeByStep(progression, steps);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
        }

    }


    @PostMapping("/api/transpose-by-key")
    public ResponseEntity<String> transposeByKey(@RequestParam("startKey") String startKey, @RequestParam("endKey") String endKey,
                                                 @RequestParam("progression") String progression) {
        try {
            String result = transposition.transpose(startKey, endKey, progression);
            int steps = transposition.transpositionIndex(startKey.trim(), endKey.trim());
            String interval = steps > 0 ? "+" + steps : String.valueOf(steps);
            return ResponseEntity.ok(interval + " pustoniai | " + result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Klaida: " + e.getMessage());
        }
    }
}
