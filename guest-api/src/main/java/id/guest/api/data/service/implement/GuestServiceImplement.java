package id.guest.api.data.service.implement;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import id.guest.api.data.entity.Guest;
import id.guest.api.data.service.GuestService;

@Service
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GuestServiceImplement extends BaseServiceImplement implements GuestService {


    @Override
    public Guest findOne(Long id) {
        return guestRepository.findOne(id);
    }

    @Override
    public String deleteUser(Long id) {
        guestRepository.delete(id);
        return "{\"success\" : true}";
    }

    @Override
    public void save(Guest guest) {
        guestRepository.save(guest);
    }

	@Override
	public List<Guest> getAllUser() {
		return guestRepository.findAll();
	}

}

