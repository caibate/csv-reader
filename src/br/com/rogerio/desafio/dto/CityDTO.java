package br.com.rogerio.desafio.dto;

public class CityDTO {

	private Long id;
	private String uf;
	private String name;
	private boolean capital;
	private Double longitude;
	private Double latitude;
	private String noAccents;
	private String alternativeNames;
	private String microRegion;
	private String mesoRegion;
	
	
	public CityDTO(Long id, String uf, String name, boolean capital, Double longitude, Double latitude, String noAccents,
			String alternativeNames, String microRegion, String mesoRegion) {
		super();
		this.id = id;
		this.uf = uf;
		this.name = name;
		this.capital = capital;
		this.longitude = longitude;
		this.latitude = latitude;
		this.noAccents = noAccents;
		this.alternativeNames = alternativeNames;
		this.microRegion = microRegion;
		this.mesoRegion = mesoRegion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroRegion() {
		return microRegion;
	}

	public void setMicroRegion(String microRegion) {
		this.microRegion = microRegion;
	}

	public String getMesoRegion() {
		return mesoRegion;
	}

	public void setMesoRegion(String mesoRegion) {
		this.mesoRegion = mesoRegion;
	}

	@Override
	public String toString() {
		return id + ", " + uf + ", " + name + ", " + capital + ", " + longitude + ", "
				+ latitude + ", " + noAccents + ", " + alternativeNames + ", "
				+ microRegion + ", " + mesoRegion;
	}
	
	
}
