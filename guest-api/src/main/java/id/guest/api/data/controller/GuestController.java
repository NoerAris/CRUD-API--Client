package id.guest.api.data.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import id.guest.api.base.annotation.Route;
import id.guest.api.base.constant.CommonConstant;
import id.guest.api.base.constant.ResponseState;
import id.guest.api.data.entity.Guest;

@RestController
@Route("/api/guest")
public class GuestController extends BaseController {

    private Logger log = LoggerFactory.getLogger(GuestController.class);

    @GetMapping("")
    public List<Guest> getAllGuest() {    	
        return guestService.getAllUser();
    }
   
    @PostMapping("/save")
    public ResponseState saveUserData(@Valid @RequestBody Guest guest) {
        ResponseState response = new ResponseState();
        try {
            guestService.save(guest);
            response.setState(CommonConstant.RESPONSE_STATUS.OK.getValue());
            response.setMessage(CommonConstant.RESPONSE_MSG.OK.getValue());
        } catch (Exception j) {
            log.error(j.getCause().getCause().getMessage());
            response.setState(CommonConstant.RESPONSE_STATUS.FAILED.getValue());
            response.setMessage(j.getCause().getCause().getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    public Guest getDataUser(@PathVariable("id") Long id) {
        return guestService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        return guestService.deleteUser(id);
    }
}
