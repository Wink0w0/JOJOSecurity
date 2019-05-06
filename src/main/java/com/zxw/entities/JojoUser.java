package com.zxw.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Administrator on 2019-4-24.
 */
@Entity
@Table(name = "jojo_user")
public class JojoUser {
    private long id;
    private String username;
    private String password;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator="assigned")
    @GenericGenerator(name="assigned", strategy="assigned")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

   
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JojoUser jojoUser = (JojoUser) o;

        if (id != jojoUser.id) return false;
        if (username != null ? !username.equals(jojoUser.username) : jojoUser.username != null) return false;
        if (password != null ? !password.equals(jojoUser.password) : jojoUser.password != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "JojoUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
