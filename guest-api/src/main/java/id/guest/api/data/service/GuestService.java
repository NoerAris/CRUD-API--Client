package id.guest.api.data.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import id.guest.api.data.entity.Guest;

@Transactional
public interface GuestService {

    public List<Guest> getAllUser();

    public Guest findOne(Long id);

    public String deleteUser(Long id);

    public void save(Guest guest);
}
