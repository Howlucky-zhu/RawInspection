package com.example.second.controller;

import com.example.second.config.VerificationCode;
import com.example.second.entity.Menu;
import com.example.second.entity.RespBean;
import com.example.second.entity.User;
import com.example.second.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@RestController
public class LoginController {

    @Autowired
    MenuService menuService;
    //这里注入一个角色的服务

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录，请登录");
    }


    @GetMapping("/menu")
    public List<Menu> getMenusByUserId() {
        //首先查询出当前登录人员id,根据uerid查询所对应的角色，在根据角色id查询出所拥有的菜单
        int uid = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Menu> result = this.menuService.getMenusByUserId(uid);
        return result;
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
}
