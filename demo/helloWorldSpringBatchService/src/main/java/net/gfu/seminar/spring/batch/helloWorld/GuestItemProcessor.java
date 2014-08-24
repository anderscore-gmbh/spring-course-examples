package net.gfu.seminar.spring.batch.helloWorld;

import org.springframework.batch.item.ItemProcessor;

public class GuestItemProcessor implements ItemProcessor<Guest, Guest> {

	@Override
	public Guest process(final Guest guest) throws Exception {
		final String firstName = guest.getFirstName().toUpperCase();
		final String lastName = guest.getLastName().toUpperCase();
		final Guest transformedGuest = new Guest(firstName, lastName);

		System.out.println("Converting (" + guest + ") into ("
				+ transformedGuest + ")");

		return transformedGuest;
	}

}