package ru.ave.tmp;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "last_seen")
    private LocalDateTime lastSeen;

    @Column(name = "about_user")
    private String aboutUser;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Profile(LocalDateTime lastSeen, String aboutUser) {
        this.lastSeen = lastSeen;
        this.aboutUser = aboutUser;
    }

    public Profile(LocalDateTime lastSeen, String aboutUser, User user) {
        this.lastSeen = lastSeen;
        this.aboutUser = aboutUser;
        this.user = user;
    }

    public Profile() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LocalDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getAboutUser() {
        return aboutUser;
    }

    public void setAboutUser(String aboutUser) {
        this.aboutUser = aboutUser;
    }
}
