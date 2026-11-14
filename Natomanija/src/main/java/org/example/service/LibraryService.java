package org.example.service;

import org.example.library.Song;
import org.example.library.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LibraryService {
    private final SongRepository songRepository;
    public LibraryService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> getSongs(Long userId) {
        return songRepository.findAllByUserId(userId);
    }

    public Song getSong(Long songId, Long userId) {
        return songRepository.findByIdAndUserId(songId, userId).orElseThrow(() ->
                new NoSuchElementException("Kūrinys nerastas:("));
    }

    @Transactional
    public Song renameSong(Long songId, Long userId, String title) {
        Song song = getSong(songId, userId);
        if (title== null || title.isBlank()) {
            throw new IllegalArgumentException("Kūrinio pavadinimas turi būti pateiktas.");
        }

        String trimmed = title.trim();
        if(trimmed.length() > 255) {
            throw new IllegalArgumentException("Kūrinio pavadinimas negali viršyti 255 simbolių.");
        }
        song.setTitle(trimmed);
        return songRepository.save(song);
    }
}
