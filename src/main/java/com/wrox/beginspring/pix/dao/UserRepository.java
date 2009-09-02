package com.wrox.beginspring.pix.dao;

import com.wrox.beginspring.pix.model.PixUser;

public interface UserRepository {

    public void persistUser(PixUser user);

    public PixUser retreiveUserByUserName(String userName);

    public void deleteUser(PixUser user);

}
