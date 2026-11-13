package org.example.library;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String title;
    private String artist;
    private String originalKey;
    private String progression;
    private Integer bpm;

    public Song() {}
    public Song(Long userId, String title, String artist, String originalKey, String progression, Integer bpm) {
        this.userId = userId;
        this.title = title;
        this.artist = artist;
        this.originalKey = originalKey;
        this.progression = progression;
        this.bpm = bpm;
    }

    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }
    public String getOriginalKey() { return originalKey; }
    public void setOriginalKey(String originalKey) { this.originalKey = originalKey; }
    public String getProgression() { return progression; }
    public void setProgression(String progression) { this.progression = progression; }
    public Integer getBpm() { return bpm; }
    public void setBpm(Integer bpm) { this.bpm = bpm; }

}
