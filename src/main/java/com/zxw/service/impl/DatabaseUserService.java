package com.zxw.service.impl;


import com.zxw.dao.*;
import com.zxw.entities.JojoPermission;
import com.zxw.entities.JojoPermissionRole;
import com.zxw.entities.JojoRoleUser;
import com.zxw.entities.JojoUser;
import com.zxw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Administrator on 2019-4-21.
 */
@Service

public class DatabaseUserService implements UserService {
    private final JojoUserRepository jojoUserRepository;
    private final JojoRoleUserRepository jojoRoleUserRepository;
    private final JojoRoleRepository jojoRoleRepository;
    private final JojoPermissionRepository jojoPermissionRepository;
    private final JojoPermissionRoleRepository jojoPermissionRoleRepository;

    @Autowired
    public DatabaseUserService(JojoUserRepository jojoUserRepository, JojoRoleUserRepository jojoRoleUserRepository, JojoRoleRepository jojoRoleRepository,
                               JojoPermissionRepository jojoPermissionRepository, JojoPermissionRoleRepository jojoPermissionRoleRepository) {
        this.jojoUserRepository = jojoUserRepository;
        this.jojoRoleUserRepository = jojoRoleUserRepository;
        this.jojoRoleRepository = jojoRoleRepository;
        this.jojoPermissionRepository = jojoPermissionRepository;
        this.jojoPermissionRoleRepository = jojoPermissionRoleRepository;
    }

    @Override
    @Transactional
    public Optional<JojoUser> findByUsername(String username) {
        return this.jojoUserRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public List<JojoPermission> findPermissionsByRoleId( List<JojoRoleUser> jojoRoleUsers) {
        List<JojoPermission> jojoPermissionsList=new ArrayList<>();
        for (JojoRoleUser jojoRoleUserItem:jojoRoleUsers) {
            List<JojoPermissionRole> byRoleId = jojoPermissionRoleRepository.findByRoleId(jojoRoleUserItem.getSysRoleId());
            for (JojoPermissionRole item:byRoleId ) {
                JojoPermission one = jojoPermissionRepository.getOne(item.getRoleId());
                jojoPermissionsList.add(one);
            }
        }

        return jojoPermissionsList;
    }

    @Override
    @Transactional
    public List<JojoRoleUser> findBySysUserId(Long sysUserId) {
        return jojoRoleUserRepository.findBySysUserId(sysUserId);
    }






 /*   @Override
    public List<JojoRoleUser> findBySysUserId(Long sysUserId) {
        return this.jojoRoleUserRepository.findBySysUserId(sysUserId);
    }

    @Override
    public JojoRole findByName(Long id) {
        return null;
    }*/


}
