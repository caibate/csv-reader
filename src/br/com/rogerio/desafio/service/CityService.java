package br.com.rogerio.desafio.service;

import java.util.ArrayList;
import java.util.List;

import br.com.rogerio.desafio.dto.CityDTO;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.ParsingErroException;

public class CityService {

	private ArrayList<CityDTO> cities;
	private FileService fileService;

	public Integer countTotal() throws EmptyCityListException {
		if (cities == null || cities.isEmpty())
			throw new EmptyCityListException();
		return cities.size();
	}

	public CityService() {
		super();
		this.getCitiesList();
	}

	public Integer countDistinct(String property) {
		List<String> distinctList = new ArrayList<String>();
		int count = 0;
		
		return count;
	}

	public ArrayList<CityDTO> filter(String property, String value) throws ParsingErroException, EmptyCityListException {
		ArrayList<CityDTO> filteredCities = new ArrayList<CityDTO>();
		if (property.equals("IBGE_ID"))
			filteredCities = findById(value);
		if (property.equals("UF"))
			filteredCities = findByUf(value);
		if (property.equals("NAME"))
			filteredCities = findByName(value);
		if (property.equals("CAPITAL"))
			filteredCities = findByIsCapital(value);
		if (property.equals("LONGITUDE"))
			filteredCities = findByLongitude(value);
		if (property.equals("LATITUDE"))
			filteredCities = findByLatitude(value);
		if (property.equals("NO_ACCENTS"))
			filteredCities = findByLNoAccentsName(value);
		if (property.equals("ALTERNATIVE_NAMES"))
			filteredCities = findByAlternativeName(value);
		if (property.equals("MICROREGION"))
			filteredCities = findByMicroRegion(value);
		if (property.equals("MESOREGION"))
			filteredCities = findByMesoRegion(value);
		
		if (filteredCities.isEmpty())
			throw new EmptyCityListException();
		return filteredCities;
	}

	private ArrayList<CityDTO> findByMesoRegion(String value) {
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getName().toUpperCase().equals(value))
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByMicroRegion(String value) {
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getMicroRegion().toUpperCase().equals(value))
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByAlternativeName(String value) {
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getAlternativeNames().toUpperCase().equals(value))
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByLNoAccentsName(String value) {
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getNoAccents().toUpperCase().equals(value))
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByLatitude(String value) throws ParsingErroException {
		try{
			Double latitude = Double.parseDouble(value);
			ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
			for (CityDTO cityDTO : cities) {
				if(cityDTO.getLatitude().equals(latitude))
					returnList.add(cityDTO);
			}
			return returnList;
		}catch (NumberFormatException e) {
			throw new ParsingErroException();
		}
	}

	private ArrayList<CityDTO> findByIsCapital(String value) throws ParsingErroException {
		if (!value.equals("TRUE") && !value.equals("FALSE")) throw new ParsingErroException();
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		Boolean isCapital = Boolean.parseBoolean(value);
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getCapital() == isCapital)
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByName(String value) {
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getName().toUpperCase().equals(value))
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByUf(String value) {
		ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
		for (CityDTO cityDTO : cities) {
			if (cityDTO.getUf().equals(value))
				returnList.add(cityDTO);
		}
		return returnList;
	}

	private ArrayList<CityDTO> findByLongitude(String value) throws ParsingErroException {
		try{
			Double longitude = Double.parseDouble(value);
			ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
			for (CityDTO cityDTO : cities) {
				if(cityDTO.getLongitude().equals(longitude))
					returnList.add(cityDTO);
			}
			return returnList;
		}catch (NumberFormatException e) {
			throw new ParsingErroException();
		}
	}
	
	private ArrayList<CityDTO> findById(String value) throws ParsingErroException {
		try {
			ArrayList<CityDTO> returnList = new ArrayList<CityDTO>();
			Long id = Long.parseLong(value);
			for (CityDTO cityDTO : cities) {
				if (cityDTO.getId().equals(id))
					returnList.add(cityDTO);
			}
			return returnList;
		} catch (NumberFormatException e) {
			throw new ParsingErroException();
		}
	}

	public void getCitiesList() {
		fileService = new FileService();
		cities = fileService.retrieveCities();
	}

}
