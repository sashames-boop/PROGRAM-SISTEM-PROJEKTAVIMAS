package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.library.Song;
import org.example.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class LibraryController {
    private final LibraryService libraryService;
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("api/songs")
    public List<Song> getSongs(HttpSession session) {
        return libraryService.getSongs(userId(session));
    }

    @GetMapping("api/songs/{songId}")
    public Song getSong(@PathVariable("songId") Long songId, HttpSession session) {
        Long userId = userId(session);

        try {
            return libraryService.getSong(songId, userId);

        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kūrinys nerastas:(");
        }

    }

    private Long userId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pirmiausia pasirinkite naudotoją.");
        }
        return userId;
    }
}
