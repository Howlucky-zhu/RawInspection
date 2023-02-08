package com.example.second.dao;

import com.example.second.entity.Role;
import com.example.second.entity.User;
import com.example.second.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@Mapper
@Component(value="UserDao")
public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User loadUserByUsername(String username);

    List<Role> getRolesById(Integer id);

    List<User> findAllUsers();

    User findUser(Integer id);

    void deleteUserById(Integer id);

    void addUser(User user);

    User getUserByName(String username);

    void insert_role(UserRole userRole);

    List<User> findUserByName(String username);

    List<User> findUserByStatus(Boolean enabled);

    void resetPassword(String password);

    User findCompanyByUsername(String username, String company);
}
