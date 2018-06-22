/*
 * 作者：xuda
 * 创建时间：18-5-3 上午11:13
 * 模块名称：admin
 */

package com.fyerp.admin.controller;

import com.fyerp.admin.service.LoginService;
import com.fyerp.admin.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.impl.util.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

//    @GetMapping("/")
//    @ApiImplicitParam(name = "username", value = "用户名", required = false, dataType = "String", paramType = "path")
//    public Result<User> index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username, Model model) {
//        model.addAttribute("username", username);
//        return ResultUtil.success(userService.findByUsername(username));
//    }
//
//    @GetMapping("/login")
//    public Result<User> login(HttpSession session) {
//        String sessionId = session.getId();
//        System.out.println("sessionId:"+sessionId);
//        return ResultUtil.success(userService.findByUsername((String) session.getAttribute(WebSecurityConfig.SESSION_KEY)));
//    }
//
//    @PostMapping(value = "/login")
//    public Result<Object> loginVerify(String username,String password,HttpSession session){
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(password);
//        String sessionId = session.getId();
//        System.out.println("sessionId:"+sessionId);
//        boolean verify = loginService.verifyLogin(user);
//        if (verify) {
//            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
//            return ResultUtil.success(userService.findByUsername(username));
//        } else {
//            ResultUtil.error(50,"用户名或密码错误");
//        }
//        return ResultUtil.success(userService.findByUsername(username));
//    }
//
//    @GetMapping("/logout")
//    public void logout(HttpSession session) {
//        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
//    }

//    /**
//     * 登录API
//     *
//     * @param user
//     * @return
//     */
//    @RequestMapping(value = "/loginPost")
//    public Result<User> login(@RequestBody String username, HttpServletRequest request) {
//        Result respInfo = new Result();
//        User user = userService.findByUsername(username);
//        HttpSession session = request.getSession();
//        String sessionId = session.getId();
//        System.out.println("sessionId:" + sessionId);
//        if (user == null) {
//            respInfo.setCode(50);
//            respInfo.setMsg("用户名和密码不能为空");
//        } else {
//            //判断之前是否登录 查看Redis中是否存在缓存（stringUtil是封装了对Redis的操作）
//            if (stringUtil.get(sessionId) != null) {
//                respInfo.setCode(20);
//                respInfo.setMsg("已经登录过了，返回之前保存的信息");
//                //stringUtil是封装了对Redis的操作
//                String json = stringUtil.get(sessionId).toString();
//                user = JSON.parseObject(json, User.class);
//                respInfo.setData(user);
//            } else {
//                //之前没有在任何站点登录过
//                if (user.getUsername().equals(username) && user.getPassword().equals(username)) {
//                    String json = JSON.toJSONString(user);
//                    respInfo.setCode(20);
//                    respInfo.setMsg("登录成功");
//                    respInfo.setData(user);
//                    //stringUtil是封装了对Redis的操作
//                    stringUtil.set(sessionId, json);
//                } else {//登录密码或用户名输入错误
//                    respInfo.setCode(50);
//                    respInfo.setMsg("用户名或密码错误，username=admin&&password=admin");
//                }
//            }
//        }
//        //最后将数据返回给前端
//        return ResultUtil.success(userService.findByUsername(username));
//    }

    @RequestMapping(value = "/unauth")
    @ResponseBody
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", "1000000");
        map.put("msg", "未登录");
        return map;
    }

    @ApiOperation(value = "login", notes = "ajaxlogin")
    @RequestMapping(value = "/ajaxLogin", produces = "application/json;charset=UTF-8")
    public String ajaxLogin(@RequestParam(value = "username", required = false)String username,
                            @RequestParam(value = "password", required = false)String password) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
            System.out.println("===========登录成功===========");
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
            System.out.println("============密码错误===========");
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
            System.out.println("============登录失败，该用户已被冻结===========");
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
            System.out.println("============该用户不存在===========");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
