package net.gfu.seminar.spring.helloworld;

import java.util.List;

public interface GuestDao {
	
	Long create(Guest guest);

	Guest findById(Long id);

	List<Guest> findByName(String name);

	List<Guest> findAll();

	Guest update(Guest guest);

	void delete(Guest guest);
}