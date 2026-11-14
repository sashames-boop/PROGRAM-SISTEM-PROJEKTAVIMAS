package org.example.library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByUserId(Long userId);
    Optional<Song> findByIdAndUserId(Long id, Long userId);
}
