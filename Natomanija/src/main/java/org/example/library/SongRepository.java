package org.example.library;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByUserId(Long userId);
    Optional<Song> findByIdAndUserId(Long id, Long userId);
}
