package com.java.smart.services.user;

import com.java.smart.exception.SmartException;
import com.java.smart.mybatis.mapper.UserMapper;
import com.java.smart.mybatis.model.User;
import com.java.smart.pojo.UserRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserMapper userMapper;



    public UserRes getUserInfoById(String id) throws SmartException{

        UserRes userRes = null;

        if(null == id || "".equals(id)){
            throw  new SmartException("900001","用户id不能为空!");
        }

        User user = userMapper.getUserInfoById(id);

        if (null == user){
            throw  new SmartException("900002","根据用户id查找用户信息失败!");
        }else {
            userRes = new UserRes();
            userRes.setId(user.getId());
            userRes.setName(user.getName());
            userRes.setPhone(user.getPhone());
            userRes.setPermissions(user.getPermissions());
            userRes.setIsDelete(user.getIsDelete());

        }

        return userRes;
    }

    public List<UserRes> getAllUserList() throws SmartException{

        List<UserRes> userRes = new ArrayList<UserRes>();
        List<User> userList = userMapper.getAllUserInfo();

        userRes = convert(userList);

        return userRes;
    }


    private List<UserRes> convert(List<User> userList){

        if(null == userList || userList.size() == 0){
            return null;
        }

        List<UserRes> userResList = new ArrayList<UserRes>();

        for(User user :userList){
            UserRes userRes = new UserRes();
            userRes.setId(user.getId());
            userRes.setName(user.getName());
            userRes.setPhone(user.getPhone());
            userRes.setPermissions(user.getPermissions());
            userRes.setIsDelete(user.getIsDelete());

            userResList.add(userRes);
        }

        return  userResList;
    }
}
