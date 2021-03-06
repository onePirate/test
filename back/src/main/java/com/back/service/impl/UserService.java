package com.back.service.impl;

import com.back.entity.UserBo;
import com.back.mybatis.UserDao;
import com.back.service.IUserService;
import com.common.enums.StateEnum;
import com.common.ex.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: gaodw
 * @Date: 14:34 2018/8/9
 * @Desc:
 */
@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<UserBo> getAllUser(){
        return userDao.findUser(null);
    }

    @Override
    public List<UserBo> getOneUser(UserBo userBo){
        if (isUserNull(userBo)) {
            throw new CustomerException(StateEnum.REQ_HAS_ERR);
        }
        try {
            return userDao.findUser(userBo.getId());
        }catch (Exception ex){
            log.error("××××× get One User has errors :",ex);
            throw new CustomerException(StateEnum.FAIL);
        }
    }
    @Override
    public void save(UserBo userBo){
        try{
            userDao.save(userBo);
        }catch(Exception ex){
            log.error("××××× save user info has errors:",ex);
            throw new CustomerException(StateEnum.FAIL);
        }
    }
    @Override
    public void update(UserBo userBo){
        try{
            userDao.update(userBo);
        }catch(Exception ex){
            log.error("××××× save user info has errors:",ex);
            throw new CustomerException(StateEnum.FAIL);
        }
    }
    @Override
    public void del(UserBo userBo){
        if (isUserNull(userBo)){
            throw new CustomerException(StateEnum.REQ_HAS_ERR);
        }
        try{
            userDao.delete(userBo.getId());
        }catch(Exception ex){
            log.error("××××× save user info has errors:",ex);
            throw new CustomerException(StateEnum.FAIL);
        }
    }

    /**
     * 判断userBo与user.getId是否为null
     */
    boolean isUserNull(UserBo userBo){
        if (userBo == null || StringUtils.isEmpty(userBo.getId())){
            return true;
        }
        return false;
    }

}
