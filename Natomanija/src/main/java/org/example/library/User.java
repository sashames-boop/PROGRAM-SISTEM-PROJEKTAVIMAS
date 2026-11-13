package org.example.library;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private IntrumentType instrument;

    public User() {};
    public User(String name, String email, String password, IntrumentType instrument) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.instrument = instrument;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public IntrumentType getInstrument() { return instrument; }
    public void setInstrument(IntrumentType instrument) { this.instrument = instrument; }


}
