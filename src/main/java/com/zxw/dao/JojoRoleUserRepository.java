package com.zxw.dao;



import com.zxw.entities.JojoRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2019-4-24.
 */
public interface JojoRoleUserRepository extends JpaRepository<JojoRoleUser,Long> {
  List<JojoRoleUser> findBySysUserId(Long sysUserId);
}
