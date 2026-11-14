package org.example.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DemoDataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public DemoDataInitializer(
            UserRepository userRepository,
            SongRepository songRepository) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        prepareLibrary("Naudotojas A", "demo-a@example.com", "Kūrinys A", "C", "C Am F G");

        prepareLibrary("Naudotojas B", "demo-b@example.com", "Kūrinys B", "G", "G Em C D");
    }

    private void prepareLibrary(String name, String email, String title, String originalKey, String progression) {

        User user = userRepository.findByEmail(email).orElseGet(() -> userRepository.save(
                        new User(name, email, null, IntrumentType.PIANO)));

        if (songRepository.findAllByUserId(user.getId()).isEmpty()) {
            Song song = new Song(user.getId(), title, "Demonstracinis autorius", originalKey, progression, 100);
            songRepository.save(song);
        }
    }
}