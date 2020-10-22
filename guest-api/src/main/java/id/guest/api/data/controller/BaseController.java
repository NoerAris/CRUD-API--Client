package id.guest.api.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import id.guest.api.data.service.GuestService;

@Controller
public class BaseController {

    @Autowired
    protected GuestService guestService;
}
