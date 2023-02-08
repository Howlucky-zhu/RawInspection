package com.example.second.service.impl;

import com.example.second.dao.UserDao;
import com.example.second.entity.User;
import com.example.second.entity.UserRole;
import com.example.second.exception.ValidateException;
import com.example.second.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class UserServiceBean implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles(userDao.getRolesById(user.getId()));
        return user;
    }


    public List<User> findAllUsers() {
        List<User> users = this.userDao.findAllUsers();
//        for (int i = 0; i < users.size(); i++) {
//            users.get(i).setPassword(null);
//        }
        return users;
    }

    @Override
    public User findUser(Integer id) {
        User user = this.userDao.findUser(id);
        return user;
    }

    public void deleteUserById(Integer id) {
        this.userDao.deleteUserById(id);
    }

    //返回错误数组
    public List<String> errors(User user) {
        List<String> errors = new ArrayList<>();
        // 验证用户名是否已经注册
        List<User> exsitUsername = this.userDao.findUserByName(user.getUsername());
        if (!exsitUsername.isEmpty()) {
            errors.add("该用户已存在，请重新输入");
        }
        return errors;
    }

    @Transactional
    public void addUser(User user){
        if(errors(user).size() > 0) {
            throw new ValidateException("-1", errors(user).toString());
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = sdf.format(new Date());
            UserRole userRole = new UserRole();
            user.setUsername(user.getUsername());
            user.setRegistTime(format);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            int count = insert(user);
            userRole.setUid(user.getId());
            userRole.setRid(3);
            insert_role(userRole);
        }
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }

    public void insert_role(UserRole userRole) {
        this.userDao.insert_role(userRole);
    }

    @Override
    public User selectByName(String username) {
        return userDao.getUserByName(username);
    }

    public void updateUser(User user){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password= user.getPassword();
        String newPassword = encoder.encode(password);
        user.setPassword(newPassword);
        this.userDao.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<User> findUserByName(String username) {
        return this.userDao.findUserByName(username);
    }

    @Override
    public List<User> findUserByStatus(Boolean enabled) {
        return this.userDao.findUserByStatus(enabled);
    }

    @Override
    public void resetPassword(String password) {
        this.userDao.resetPassword(password);
    }
}
