package org.cmms.modules.base.dao;

import org.cmms.base.XbootBaseDao;
import org.cmms.modules.base.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户数据处理层
 * @author Exrickx
 */
public interface UserDao extends XbootBaseDao<User,String> {

    /**
     * 通过用户名获取用户
     * @param username
     * @return
     */
    User findByUsername(String username);


    /**
     * 通过用户名模糊搜索
     * @param key
     * @param status
     * @return
     */
    @Query("select u from User u where u.username like %?1% or u.realname like %?1% ")
    List<User> findByUsernameLikeAndStatus(String key, Integer status);


}
