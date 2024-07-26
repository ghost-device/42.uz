package uz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(method = RequestMethod.GET)
    public String throwToAdminLogin() {
        return "admin-login";
    }


    /*
    TODO
        mentor all
        delete mentor (if course has, throw an exception)

     */
}
