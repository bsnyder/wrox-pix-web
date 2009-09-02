package com.wrox.beginspring.pix.dao;

import org.springframework.transaction.annotation.Transactional;

import com.wrox.beginspring.pix.model.PixUser;

public class UserJpaRepository extends AbstractAlbumDAO implements
        UserRepository {

    @Transactional(readOnly = true)
    public PixUser retreiveUserByUserName(String userName) {
        return getJpaTemplate().find(PixUser.class, userName);

    }

    @Transactional
    public void deleteUser(PixUser user) {
        getJpaTemplate().remove(user);

    }

    @Transactional
    public void persistUser(PixUser user) {
        getJpaTemplate().persist(user);

    }

}
