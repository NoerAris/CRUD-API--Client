package id.guest.api.data.controller;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.guest.api.base.annotation.Route;

@RestController
public class HomeController {

    @Route("/")
    @ResponseBody
    public String homeIndex(){
        return "Assalamu'alaikum Wr Wb";
    }
}
