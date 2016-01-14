package kolobry.projekt.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "uczen.niezapis", query = "Select u from Uczen u where u.zapisany = false")
})

public class Uczen {

	private Long idUczen;
	private String imie;
	private String nazw;
	private String doswiadczenie;
	private Long Lekcja;
	private Boolean zapisany = false;

	public Uczen(String imie, String nazw, String doswiadczenie,  Long Lekcja) {

		this.imie = imie;
		this.nazw = nazw;
		this.doswiadczenie = doswiadczenie;
		this.Lekcja = Lekcja;

	}

	public Uczen(String imie, String nazw,   Long  Lekcja) {

		this.imie = imie;
		this.nazw = nazw;
		this.Lekcja = Lekcja;

	}
	public Uczen(String imie, String nazw,  String doswiadczenie) {

		this.imie = imie;
		this.nazw = nazw;
		this.doswiadczenie = doswiadczenie;

	}

	public Uczen() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdUczen() {
		return idUczen;
	}

	public void setIdUczen(Long idUczen) {
		this.idUczen = idUczen;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazw() {
		return nazw;
	}

	public void setNazw(String nazw) {
		this.nazw = nazw;
	}

	public String getDosw() {
		return doswiadczenie;
	}

	public void setDosw(String doswiadczenie) {
		this.doswiadczenie = doswiadczenie;
	}

	public Long getLekcja() {
		return Lekcja;
	}

	public void setLekcja(Long Lekcja) {
		this.Lekcja = Lekcja;
	}

	public Boolean getZapisany() {
		return zapisany;
	}

	public void setZapisany(Boolean zapis) {
		this.zapisany = zapis;
	}

}
