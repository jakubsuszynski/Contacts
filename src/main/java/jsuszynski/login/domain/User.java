package jsuszynski.login.domain;

import javax.persistence.*;


@Entity
@Access(AccessType.FIELD)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;
    private String login;
    private String password;


    public User() {

    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {

        return login;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
