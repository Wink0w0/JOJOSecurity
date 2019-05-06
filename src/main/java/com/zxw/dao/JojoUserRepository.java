package com.zxw.dao;

import com.zxw.entities.JojoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Administrator on 2019-4-12.
 */
public interface JojoUserRepository extends JpaRepository<JojoUser, Long> {

     Optional<JojoUser> findByUsername(String username);


}
