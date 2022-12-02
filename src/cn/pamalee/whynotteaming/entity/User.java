package cn.pamalee.whynotteaming.entity;

import cn.devspace.nucleus.Plugin.DataEntity;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Hibernate数据库
 */
@Entity
@Table(name = "wnt_user")
public class User extends DataEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false)
    private Long id;

    @Column(nullable = false,name = "username")
    private String username;

    @Column(name = "phone",nullable = false)
    private String phone;

    @Column(name = "email",nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "lastLogin")
    private Timestamp last_login;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getLast_login() {
        return last_login;
    }

    public void setLast_login(Timestamp last_login) {
        this.last_login = last_login;
    }
}
