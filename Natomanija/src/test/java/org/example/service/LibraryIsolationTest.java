package org.example.service;


import org.example.library.Song;
import org.example.library.User;
import org.example.library.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class LibraryIsolationTest {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void userBCannotReadUserASong() {
        User userA = userRepository.findByEmail("demo-a@example.com").orElseThrow();
        User userB = userRepository.findByEmail("demo-b@example.com").orElseThrow();

        List<Song> songsA = libraryService.getSongs(userA.getId());
        assertFalse(songsA.isEmpty());

        Long songAId  = songsA.getFirst().getId();

        Song ownSong = libraryService.getSong(songAId, userA.getId());
        assertEquals(songAId, ownSong.getId());

        List<Song> songsB = libraryService.getSongs(userB.getId());
        assertTrue(songsB.stream().noneMatch(song -> song.getId().equals(songAId)));
        assertThrows(
                NoSuchElementException.class, () -> libraryService.getSong(songAId, userB.getId())
        );

    }
}
