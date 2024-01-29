package hk.tomihi.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Getter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(name = "USERNAME", nullable = false, unique = true, updatable = false)
    private String username;

    @Setter
    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Setter
    @Column(name = "BIO", nullable = true)
    private String bio;

    public User() {

    }

    public User(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.passwordHash = PasswordHashed.hashPassword(password);
    }

    public User(Long id, String username, String passwordHash, String bio) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.bio = bio;
    }
}
