package konrad.projekt.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;


import kolobry.projekt.service.SzkolaManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kolobry.projekt.domain.Uczen;
import kolobry.projekt.domain.Lekcja;




@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SzkolaManagerTest {

	@Autowired
	SzkolaManager szkolaManager;

	private final String IMIE_1 = "Bolek";
	private final String NAZWISKO_1 = "Maly";
	private final String DOSW_1 = "male";

	private final String IMIE_2 = "Lolek";
	private final String NAZWISKO_2 = "Duzy";
	private final String DOSW_2 = "duze";

	private final String RODZAJ_1 = "narty";
	private final String GODZ_1 = "4";
	private final int ID_1 = 5;

	private final String RODZAJ_2 = "snowboard";
	private final String GODZ_2 = "3";

/*	@Test
	public void addLekcjaCheck() {

		List<Lekcja> retrievedLekcjas = szkolaManager.getAllLekcja();

		// If there is a client with PIN_1 delete it
		for (Lekcja lekcja : retrievedLekcjas) {
			if (lekcja.getRodzaj().equals(RODZAJ_1)) {
				szkolaManager.deleteLekcja(lekcja);
			}
		}

		Lekcja lekcja = new Lekcja();
		lekcja.setRodzaj(RODZAJ_1);
		lekcja.setIdLekcja((long) ID_1);
		// ... other properties here


		// ID is Unique
		szkolaManager.addLekcja(lekcja);

		Lekcja retrievedLekcja = szkolaManager.findLekcjaById((long) ID_1);

		assertEquals(RODZAJ_1, retrievedLekcja.getRodzaj());
		//assertEquals(ID_1, retrievedLekcja.getIdLekcja());
		// ... check other properties here
	}
*/
	@Test
	public void addUczenCheck() {

		Uczen uczen = new Uczen();
		uczen.setImie(IMIE_1);
		uczen.setNazw(NAZWISKO_1);
		uczen.setDosw(DOSW_1);
		// ... other properties here

		Long uczenId = szkolaManager.addUczen(uczen);

		Uczen retrievedUczen = szkolaManager.findUczenById(uczenId);
		assertEquals(IMIE_1, retrievedUczen.getImie());
		assertEquals(NAZWISKO_1, retrievedUczen.getNazw());
		// ... check other properties here

	}
/*
	@Test
	public void sellUczenCheck() {

		Lekcja lekcja = new Lekcja();
		lekcja.setFirstName(NAME_2);
		lekcja.setPin(PIN_2);

		szkolaManager.addClient(lekcja);

		Lekcja retrievedLekcja = szkolaManager.findClientByPin(PIN_2);

		Uczen uczen = new Uczen();
		uczen.setMake(MAKE_2);
		uczen.setModel(MODEL_2);

		Long uczenId = szkolaManager.addNewUczen(uczen);

		szkolaManager.sellUczen(retrievedLekcja.getId(), uczenId);

		List<Uczen> ownedUczens = szkolaManager.getOwnedUczens(retrievedLekcja);

		assertEquals(1, ownedUczens.size());
		assertEquals(MAKE_2, ownedUczens.get(0).getMake());
		assertEquals(MODEL_2, ownedUczens.get(0).getModel());
	}

	// @Test -
	public void disposeUczenCheck() {
		// Do it yourself
	}
*/
}
