package org.example.service;

import org.example.library.Song;
import org.example.library.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
                new IllegalArgumentException("Kūrinys nerastas:("));
    }
}
