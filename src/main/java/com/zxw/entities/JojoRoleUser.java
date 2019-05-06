package com.zxw.entities;

import javax.persistence.*;

/**
 * Created by Administrator on 2019-4-24.
 */
@Entity
@Table(name = "jojo_role_user")
public class JojoRoleUser {
    private long id;
    private long sysUserId;
    private long sysRoleId;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sys_user_id")
    public long getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(long sysUserId) {
        this.sysUserId = sysUserId;
    }

    @Basic
    @Column(name = "sys_role_id")
    public long getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleId(long sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JojoRoleUser that = (JojoRoleUser) o;

        if (id != that.id) return false;
        if (sysUserId != that.sysUserId) return false;
        if (sysRoleId != that.sysRoleId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (sysUserId ^ (sysUserId >>> 32));
        result = 31 * result + (int) (sysRoleId ^ (sysRoleId >>> 32));
        return result;
    }
}
