package id.guest.api.data.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.guest.api.data.repository.GuestRepository;

@Service
public class BaseServiceImplement {

    @Autowired
    protected GuestRepository guestRepository;
}
