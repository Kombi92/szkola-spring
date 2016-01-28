package kolobry.projekt.service;

import kolobry.projekt.domain.Lekcja;
import kolobry.projekt.domain.Uczen;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SzkolaMangerHibernateImpl implements SzkolaManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addLekcja(Lekcja lekcja) {
		lekcja.setIdLekcja(null);
		sessionFactory.getCurrentSession().persist(lekcja);
	}
	
	@Override
	public void deleteLekcja(Lekcja lekcja) {
		lekcja = (Lekcja) sessionFactory.getCurrentSession().get(Lekcja.class,
				lekcja.getIdLekcja());

		// lazy loading here
		for (Uczen aUczen : lekcja.getUczniowie()) {
			aUczen.setZapisany(false);
			sessionFactory.getCurrentSession().update(aUczen);
		}
		sessionFactory.getCurrentSession().delete(lekcja);
	}

	@Override
	public void deleteUczen(Uczen uczen) {
		uczen = (Uczen) sessionFactory.getCurrentSession().get(Uczen.class,
				uczen.getIdUczen());

		sessionFactory.getCurrentSession().delete(uczen);
	}

	@Override
	public List<Uczen> getOwnedLekcja (Lekcja lekcja) {
		lekcja = (Lekcja) sessionFactory.getCurrentSession().get(Lekcja.class,
				lekcja.getIdLekcja());
		// lazy loading here - try this code without (shallow) copying
		List<Uczen> uczniowie = new ArrayList<Uczen>(lekcja.getUczniowie());
		return uczniowie;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Lekcja> getAllLekcja() {
		return sessionFactory.getCurrentSession().getNamedQuery("lekcja.all")
				.list();
	}

	@Override
	public Lekcja findLekcjaById(Long id) {
		//return (Lekcja) sessionFactory.getCurrentSession().getNamedQuery("lekcja.byId").setInteger("id", id).uniqueResult()
		return (Lekcja) sessionFactory.getCurrentSession().get(Lekcja.class, id);
	}


	@Override
	public Long addUczen(Uczen uczen) {
		uczen.setIdUczen(null);
		return (Long) sessionFactory.getCurrentSession().save(uczen);
	}

	@Override
	public void giveUczenLekcja(Long lekcjaId, Long uczenId) {
		Lekcja lekcja = (Lekcja) sessionFactory.getCurrentSession().get(
				Lekcja.class, lekcjaId);
		Uczen uczen = (Uczen) sessionFactory.getCurrentSession()
				.get(Uczen.class, uczenId);
		uczen.setZapisany(true);
		lekcja.getUczniowie().add(uczen);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Uczen> getAllUczen() {
		return sessionFactory.getCurrentSession().getNamedQuery("uczen.niezapis")
				.list();
	}
	@Override
	public void disposeUczenFromLekcja(Lekcja lekcja, Uczen uczen) {

		lekcja = (Lekcja) sessionFactory.getCurrentSession().get(Lekcja.class,
				lekcja.getIdLekcja());
		uczen = (Uczen) sessionFactory.getCurrentSession().get(Uczen.class,
				uczen.getIdUczen());

		Uczen toRemove = null;
		// lazy loading here (lekcja.getUczens)
		for (Uczen aUczen : lekcja.getUczniowie())
			if (aUczen.getIdUczen().compareTo(uczen.getIdUczen()) == 0) {
				toRemove = aUczen;
				break;
			}

		if (toRemove != null)
			lekcja.getUczniowie().remove(toRemove);

		uczen.setZapisany(false);
	}

	@Override
	public Uczen findUczenById(Long id) {
		return (Uczen) sessionFactory.getCurrentSession().get(Uczen.class, id);
	}

}
