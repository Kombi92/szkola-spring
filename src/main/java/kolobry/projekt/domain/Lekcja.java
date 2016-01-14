package kolobry.projekt.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "lekcja.all", query = "Select l from Lekcja l"),
	@NamedQuery(name = "lekcja.byId", query = "Select l from Lekcja l where l.id = :id")
})
public class Lekcja {

	private Long idLekcja;
	private String rodzaj;
	private String ilosc_godzin;

	private List<Uczen> uczniowie = new ArrayList<Uczen>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdLekcja() {
		return idLekcja;
	}
	public void setIdLekcja(Long idLekcja) {
		this.idLekcja = idLekcja;
	}
	
	public String getRodzaj() {
		return rodzaj;
	}
	public void setRodzaj(String rodzaj) {
		this.rodzaj = rodzaj;
	}

	public String getGodz() {
		return ilosc_godzin;
	}
	public void setGodz(String ilosc_godzin) {
		this.ilosc_godzin = ilosc_godzin;
	}

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Uczen> getUczniowie() {
		return uczniowie;
	}
	public void setUczniowie(List<Uczen> uczniowie) {
		this.uczniowie = uczniowie;
	}
}
