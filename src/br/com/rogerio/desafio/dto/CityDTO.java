package br.com.rogerio.desafio.dto;

public class CityDTO {

	private Long ibge_id;
	private String uf;
	private String name;
	private boolean capital;
	private Double lon;
	private Double lat;
	private String no_accents;
	private String alternative_names;
	private String microregion;
	private String mesoregion;
	
	
	public CityDTO(Long id, String uf, String name, boolean capital, Double longitude, Double latitude, String noAccents,
			String alternativeNames, String microRegion, String mesoRegion) {
		super();
		this.ibge_id = id;
		this.uf = uf;
		this.name = name;
		this.capital = capital;
		this.lon = longitude;
		this.lat = latitude;
		this.no_accents = noAccents;
		this.alternative_names = alternativeNames;
		this.microregion = microRegion;
		this.mesoregion = mesoRegion;
	}

	public Long getIbge_id() {
		return ibge_id;
	}

	public void setIbge_id(Long ibge_id) {
		this.ibge_id = ibge_id;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getNo_accents() {
		return no_accents;
	}

	public void setNo_accents(String no_accents) {
		this.no_accents = no_accents;
	}

	public String getAlternative_names() {
		return alternative_names;
	}

	public void setAlternative_names(String alternative_names) {
		this.alternative_names = alternative_names;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}

	@Override
	public String toString() {
		return ibge_id + ", " + uf + ", " + name + ", " + capital + ", " + lon + ", "
				+ lat + ", " + no_accents + ", " + alternative_names + ", "
				+ microregion + ", " + mesoregion;
	}
	
	
}
