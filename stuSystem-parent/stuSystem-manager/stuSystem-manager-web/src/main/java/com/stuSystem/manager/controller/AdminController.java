package com.stuSystem.manager.controller;

import com.stuSystem.manager.other.UserFactory;
import com.stuSystem.manager.pojo.Announce;
import com.stuSystem.manager.service.AnnounceService;
import com.stuSystem.tools.filesave.FileDealToLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private AnnounceService announceService;
    /**
     * 跳转到学生信息导入界面
     * @param request
     * @return
     */
    @RequestMapping(value="/goStuImportUI",method= RequestMethod.GET)
    public String goStuImport(HttpServletRequest request){
       if(isLogin(request)){
           return "admin/stuImport";
       }
       return "login";
    }

    /**
     * 跳转到教师信息导入界面
     * @param request
     * @return
     */
   @RequestMapping(value = "/goTeaImporUi",method = RequestMethod.GET)
    public String goSTeaImport(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/teaImport";
        }
        return "login";
    }

    /**
     * 判断超级管理员是否登陆。
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        UserFactory.CstmUser user = (UserFactory.CstmUser<Object>) session.getAttribute("User");
        if(user!=null && user.getUserType() == 3){
            return true;
        }
        return false;
    }

    /**
     * 跳转到学生搜索界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goStuSearchUI",method = RequestMethod.GET)
    public String goStuSearchUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/stuSearch";
        }
        return "login";
    }

    /**
     * 跳转到老师搜索界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goTeaSearchUI",method = RequestMethod.GET)
    public String goTeaSearchUI(HttpServletRequest request){
        if(isLogin(request)){
            return "admin/teaSearch";
        }
        return "login";
    }

    /**
     * 跳转公告发布界面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goApUI",method = RequestMethod.GET)
    public String goApUI(HttpServletRequest request){

        if(isLogin(request)){
            return "admin/put_announce";
        }
        return "login";
    }
    @RequestMapping(value = "/annoPreUI",method = RequestMethod.GET)
    public String announce_preview(HttpServletRequest request){
        System.out.println(request.getSession().getServletContext().getRealPath(""));

        if(isLogin(request)){
            return "admin/announce-yulan";
        }
        return "login";
    }

    @RequestMapping(value = "/putAnnounce",method=RequestMethod.POST)
    public ModelAndView putAnnounce(Announce announce, MultipartFile otherfile) {
        ModelAndView mv = new ModelAndView();
        System.out.println("我已经进来了");
        System.out.println(otherfile);
        String str = System.getProperty("inf-root");
        if(!(otherfile==null || otherfile.isEmpty())) {
            try {
                FileDealToLocal fileDealToLocal = new FileDealToLocal();
                String linkName = fileDealToLocal.saveFile(otherfile);
                announce.setAnnouLink(linkName);
            } catch (Exception e) {
                e.printStackTrace();
                mv.addObject("msg","公告发布失败");
                mv.setViewName("metion");
                return mv;
            }
        }
        boolean flag = announceService.InsertOneAnnounce(announce);
        if(flag){
            mv.addObject("msg","公告发布成功");
        }else{
            mv.addObject("msg","公告发布失败");
        }
        mv.setViewName("metion");
        return mv;
    }

}
