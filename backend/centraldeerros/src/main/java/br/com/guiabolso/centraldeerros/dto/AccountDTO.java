package br.com.guiabolso.centraldeerros.dto;


import br.com.guiabolso.centraldeerros.entity.Account;
import java.io.Serializable;

public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 497201589703335960L;

    private Long id;
    private String username;
    private String password;
    private String email;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.email = account.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
