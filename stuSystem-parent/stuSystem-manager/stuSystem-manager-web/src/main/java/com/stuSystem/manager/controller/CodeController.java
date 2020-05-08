package com.stuSystem.manager.controller;

import com.stuSystem.tools.ValidateCode;
import org.apache.http.io.BufferInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("code")
@Controller
public class CodeController {

    /**
     * (1)返回验证码
     * (2)保存验证码的内容
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/showYzm")
    public void getYZM(HttpServletRequest request, HttpServletResponse response)throws IOException {
        ValidateCode vc = new ValidateCode();
        BufferedImage img = vc.getImg();
        String yzmContext = vc.getText();
        System.out.println("此次验证码为："+yzmContext);
        request.getSession().setAttribute("yzm",yzmContext);
        ValidateCode.output(img,response.getOutputStream());
    }

    /**
     * 检验用户输入的验证码是否正确：
     * (1)返回json数据->正确返回1，并移除session暂存值，不正确返回0
     * @param request
     * @param inputCode
     * @return
     * @throws IOException
     */
    @RequestMapping("/CodeValidate")
    public @ResponseBody
    String CodeValidate(HttpServletRequest request, String inputCode) throws IOException {
        HttpSession session = request.getSession();
        String realCodeText = (String) session.getAttribute("yzm");
        System.out.println("真值："+realCodeText+",输入值："+inputCode);
        String value;
        /**
         * ajax处理String类型的请求。
         */
        if(realCodeText.equals(inputCode)) {
            value="1";
            session.removeAttribute("yzm");
        }else {
            value="0";
        }
        return value;
    }
}
