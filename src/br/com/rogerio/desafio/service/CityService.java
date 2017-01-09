package br.com.rogerio.desafio.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import br.com.rogerio.desafio.dto.CityDTO;
import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;

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

	public ArrayList<CityDTO> filter(String property, String value) throws CityNotFoundException, EmptyCityListException{
		if(cities == null || cities.isEmpty()) throw new EmptyCityListException();
		ArrayList<CityDTO> filteredList = new ArrayList<CityDTO>();
		property = property.toLowerCase();
		String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
		try {
			for(CityDTO cityDTO : cities){
				Class clazz = cityDTO.getClass();
				Method method = clazz.getMethod(methodName);
				if(method.invoke(cityDTO).toString().toLowerCase().equals(value)){
					filteredList.add(cityDTO);
				}
			}
		} catch (NoSuchMethodException e) {
			System.out.println("P");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return filteredList;

		
	}

	public void getCitiesList() {
		fileService = new FileService();
		cities = fileService.retrieveCities();
	}

}
