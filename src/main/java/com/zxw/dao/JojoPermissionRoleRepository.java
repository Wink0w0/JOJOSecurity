package com.zxw.dao;


import com.zxw.entities.JojoPermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/4/28  20:32.
 * @Modified by:
 */
public interface JojoPermissionRoleRepository extends JpaRepository<JojoPermissionRole,Long> {
   List<JojoPermissionRole> findByRoleId(Long roleId);
}
