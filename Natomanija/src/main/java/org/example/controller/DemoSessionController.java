package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.library.User;
import org.example.library.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DemoSessionController {
    private final UserRepository userRepository;

    public DemoSessionController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("api/demo/select-user")
    public String selectUser(@RequestParam("profile") String profile, HttpSession session) {
        String email = switch (profile) {
            case "A" -> "demo-a@example.com";
            case "B" -> "demo-b@example.com";
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pasirinkite egzistuojantį vartotoją.");
        };

        User user = userRepository.findByEmail(email).orElseThrow(() ->
            new ResponseStatusException(HttpStatus.NOT_FOUND, "Demonstracinis naudotojas nerastas."));
        session.setAttribute("userId", user.getId());
        return "Pasirinktas naudotojas: " + user.getName();
    }

}
