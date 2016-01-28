package kolobry.projekt.service;

import kolobry.projekt.domain.Uczen;
import kolobry.projekt.domain.Lekcja;

import java.util.List;

public interface SzkolaManager {
	
	void addLekcja(Lekcja lekcja);
	List<Lekcja> getAllLekcja();
	void deleteLekcja(Lekcja lekcja);
	Lekcja findLekcjaById(Long id);

	Long addUczen(Uczen uczen);
	List<Uczen> getAllUczen();
	void disposeUczenFromLekcja(Lekcja lekcja, Uczen uczen);
	Uczen findUczenById(Long id);
	void deleteUczen(Uczen uczen);

	//List<Uczen> getOwnedLekcja(Lekcja lekcja);
	void giveUczenLekcja(Long lekcjaID, Long uczenID);

}

