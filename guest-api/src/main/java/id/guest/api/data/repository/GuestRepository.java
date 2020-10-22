package id.guest.api.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.guest.api.data.entity.Guest;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
	
}
