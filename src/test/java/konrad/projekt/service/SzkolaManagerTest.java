package konrad.projekt.service;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


import kolobry.projekt.service.SzkolaManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import kolobry.projekt.domain.Uczen;
import kolobry.projekt.domain.Lekcja;

import static org.junit.Assert.*;


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
	private final Long ID_1 = Long.valueOf(5);
	private final Long ID_2 = Long.valueOf(10);

	private final String RODZAJ_2 = "snowboard";
	private final String GODZ_2 = "3";

	public Uczen uczen1 = new Uczen();


	public Uczen uczen2 = new Uczen();

	@Before
	public void addUczenToDB(){
		uczen1.setImie(IMIE_1);
		uczen1.setNazw(NAZWISKO_1);
		uczen1.setDosw(DOSW_1);

		uczen2.setImie(IMIE_2);
		uczen2.setNazw(NAZWISKO_2);
		uczen2.setDosw(DOSW_2);

		szkolaManager.addUczen(uczen1);
		szkolaManager.addUczen(uczen2);
	}
	@After
	public void dropUczenFromDB(){
		szkolaManager.deleteUczen(uczen1);
		szkolaManager.deleteUczen(uczen2);
	}

	@Test
	public void addLekcjaCheck() {

		Lekcja lekcja = new Lekcja();
		lekcja.setRodzaj(RODZAJ_1);
		lekcja.setIdLekcja( ID_1);

		szkolaManager.addLekcja(lekcja);
		Lekcja lekcja2 = szkolaManager.findLekcjaById(lekcja.getIdLekcja());

		assertEquals(lekcja,lekcja2);

	}

	@Test
	public void addUczenCheck() {

		Uczen uczen = new Uczen();
		uczen.setImie(IMIE_1);
		uczen.setNazw(NAZWISKO_1);
		uczen.setDosw(DOSW_1);

		Long uczenId = szkolaManager.addUczen(uczen);

		Uczen retrievedUczen = szkolaManager.findUczenById(uczenId);
		assertEquals(IMIE_1, retrievedUczen.getImie());
		assertEquals(NAZWISKO_1, retrievedUczen.getNazw());
	}

	@Test
	public void dropUczenFromLekcjaCheck() {

		Uczen uczen1 = new Uczen();
		Lekcja lekcja1 = new Lekcja();

		uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1); uczen1.setLekcja(ID_1);uczen1.setZapisany(true);

		assertNotNull(szkolaManager.addUczen(uczen1));

		szkolaManager.addLekcja(lekcja1);

		szkolaManager.disposeUczenFromLekcja(lekcja1,uczen1);

		assertFalse(uczen1.getZapisany());

	}

	@Test
	public void dropLekcjaCheck() {  // test usuniecia lekcji z przypisanym uczniem

		Lekcja lekcja1 = new Lekcja();
		Uczen uczen1 = new Uczen();

		lekcja1.setGodz(GODZ_1); lekcja1.setIdLekcja(ID_1);lekcja1.setRodzaj(RODZAJ_1);

		uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1);uczen1.setZapisany(true);

		szkolaManager.addUczen(uczen1);
		szkolaManager.addLekcja(lekcja1);
		szkolaManager.giveUczenLekcja(lekcja1.getIdLekcja(),uczen1.getIdUczen());
		szkolaManager.deleteLekcja(lekcja1);

		assertFalse(uczen1.getZapisany());

		assertNull(szkolaManager.findLekcjaById(ID_1));

	}

	@Test
	public void uczenDropCheck() {  // test usuniecia lekcji z przypisanym uczniem

		Uczen uczen1 = new Uczen();

		uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1);uczen1.setZapisany(true);

		szkolaManager.addUczen(uczen1);
		szkolaManager.deleteUczen(uczen1);

		assertNull(szkolaManager.findUczenById(ID_1));

	}

	 @Test
	public void editUczenCheck() {

		 Uczen uczen1 = new Uczen();
		 Uczen uczen2 = new Uczen();

		 uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1); uczen1.setLekcja(ID_1);uczen1.setZapisany(true);
		 uczen2.setImie(IMIE_2); uczen2.setNazw(NAZWISKO_2); uczen2.setDosw(DOSW_2); uczen2.setLekcja(ID_2);uczen2.setZapisany(true);

		 szkolaManager.addUczen(uczen1);
		 szkolaManager.addUczen(uczen2);

		 assertEquals(uczen1.getImie(),IMIE_1);

		 uczen1.setImie("Konrad");

		 assertEquals(uczen1.getImie(),"Konrad");
		 assertEquals(uczen2.getImie(),IMIE_2);
	 }

	@Test
	public void giveUczenLekcjaCheck() {

		Lekcja lekcja1 = new Lekcja();
		Uczen uczen1 = new Uczen();

		lekcja1.setGodz(GODZ_1); lekcja1.setIdLekcja(ID_1);lekcja1.setRodzaj(RODZAJ_1);

		uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1);uczen1.setZapisany(false);

		szkolaManager.addUczen(uczen1);
		szkolaManager.addLekcja(lekcja1);

		szkolaManager.giveUczenLekcja(lekcja1.getIdLekcja(),uczen1.getIdUczen());

		assertTrue(uczen1.getZapisany());

	}

	@Test
	public void getUczenWithoutLekcja(){

		Uczen uczen1 = new Uczen();
		Uczen uczen2 = new Uczen();
		List<Uczen> uczniowie = new ArrayList<Uczen>();

		uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1);uczen1.setZapisany(false);
		uczen2.setImie(IMIE_1); uczen2.setNazw(NAZWISKO_1); uczen2.setDosw(DOSW_1);uczen2.setZapisany(false);

		szkolaManager.addUczen(uczen1);
		szkolaManager.addUczen(uczen2);

		uczniowie = szkolaManager.getAllUczen();

		for(Uczen uczen : uczniowie){
			assertEquals(false,uczen.getZapisany());
		}
	}

	@Test
	public void getUczenFromLekcja(){

		Lekcja lekcja1 = new Lekcja();
		Uczen uczen1 = new Uczen();
		Uczen uczen2 = new Uczen();
		List<Uczen> uczniowie = new ArrayList<Uczen>();

		lekcja1.setGodz(GODZ_1); lekcja1.setIdLekcja(ID_1);lekcja1.setRodzaj(RODZAJ_1);

		uczen1.setImie(IMIE_1); uczen1.setNazw(NAZWISKO_1); uczen1.setDosw(DOSW_1);uczen1.setZapisany(false);
		uczen2.setImie(IMIE_1); uczen2.setNazw(NAZWISKO_1); uczen2.setDosw(DOSW_1);uczen2.setZapisany(false);

		szkolaManager.addUczen(uczen1);
		szkolaManager.addUczen(uczen2);
		szkolaManager.addLekcja(lekcja1);
		szkolaManager.giveUczenLekcja(lekcja1.getIdLekcja(),uczen1.getIdUczen());
		szkolaManager.giveUczenLekcja(lekcja1.getIdLekcja(),uczen2.getIdUczen());

		uczniowie = lekcja1.getUczniowie();

		assertEquals(2,uczniowie.size());
	}
}
