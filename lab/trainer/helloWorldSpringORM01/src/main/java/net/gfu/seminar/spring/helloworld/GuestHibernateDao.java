package net.gfu.seminar.spring.helloworld;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository implementation based on plain Hibernate 4.x API.
 * 
 * @author tf
 *
 */
@Repository
@Transactional
public class GuestHibernateDao implements GuestDao {

	private SessionFactory sessionFactory;
	
	public GuestHibernateDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long create(Guest guest) {
		return (Long) sessionFactory.getCurrentSession().save(guest);
	}

	@Override
	public Guest findById(Long id) {
		return (Guest) this.sessionFactory.getCurrentSession().get(Guest.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> findByName(String name) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("from Guest as g where g.lastName = :name")
				.setString("name", name).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Guest> findAll() {
		return this.sessionFactory.getCurrentSession().createQuery("from Guest").list();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Guest update(Guest guest) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(guest);
		return guest;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Guest guest) {
		this.sessionFactory.getCurrentSession().delete(guest);
	}

}
