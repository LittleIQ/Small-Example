package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Vector;
import com.example.dosql.UseSql;

/**
 * 类上面的“Controller”注解，表示这是一个Spring的控制器
 */
@Controller
public class HelloWorldController {

    /**
     * 1. 使用RequestMapping注解来映射请求的URL，这里匹配的请求路径是“http://localhost:8080/hello。
     * 2. 返回值会通过视图解析器解析为实际的物理视图, 对于InternalResourceViewResolver视图解析器，会做如下解析： 通过prefix+returnVal+suffix 这样的方式得到实际的物理视图。
     * 3. RequestMapping可限制请求方式（GET、POST、PUT、DELETE等），比如用户留言，我们可只允许POST提交：@RequestMapping("/subMessage",method=RequestMethod.POST)。
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(Model model) {
        /**
         * 设置用于返回的模型对象
         */
//        model.addAttribute("message", "Hello ! Spring MVC~!");
        /**
         * spring-content.xml 中已配置通过 prefix+returnVal+suffix 的方式得到实际的物理视图，
         * returnVal 就是这里返回的字符串，所以执行完这个方法后，我们可以得到这样的请求资源路径“/WEB-INF/views/success.jsp”。
         */
        System.out.println("aaa");
        return "getHello";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserModel user, Model model) {
        String username = user.getUsername();
        String password = user.getPassword();
        UseSql x = new UseSql("blog");
        Vector all = x.read("select password from users where username=\'"+username+"\'",1);
        String datapass = null;
        if(all!=null) {
            datapass = (String) ((Vector) all.get(0)).get(0);
            System.out.println(password);
            System.out.println(datapass);
        }

        if(datapass==null){
            model.addAttribute("message", "没有此用户");
            return "error";
        }else if(datapass.equals(password)){
            model.addAttribute("message", username);
            return "success";
        }else {
            model.addAttribute("message", "密码错误");
            return "error";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
        return "sign";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(UserModel user, Model model) {
        UseSql connect = new UseSql("blog");
        String[] data = {
                user.getUsername(),
                user.getPassword()
        };
        System.out.println(data[0]+' '+data[1]);
        System.out.println(connect.insert("insert into users(username,password)values(?,?)", data));
        return "redirect:/hello";
    }
}