package hk.tomihi.diningapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.security.NoSuchAlgorithmException;


@Getter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Setter
    @Column(name = "USERNAME", nullable = false, unique = true, updatable = false)
    private String username;

    @Setter
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Setter
    @Column(name = "BIO")
    private String bio;

    public User() {

    }

    public User(String username, String password) throws NoSuchAlgorithmException {
        this.username = username;
        this.password = PasswordHashed.hashPassword(password);
    }

}
