package br.com.rogerio.desafio.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.rogerio.desafio.dto.CityDTO;
import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;

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

	public ArrayList<CityDTO> filter(String property, String value) throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		if(cities == null || cities.isEmpty()) throw new EmptyCityListException();
		ArrayList<CityDTO> filteredList = new ArrayList<CityDTO>();
		property = property.toLowerCase();
		String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
		try {
			for(CityDTO cityDTO : cities){
				Class clazz = CityDTO.class;
				Method method = clazz.getMethod(methodName);
				if(method.invoke(cityDTO).toString().toLowerCase().equals(value)){
					filteredList.add(cityDTO);
				}
			}
		} catch (NoSuchMethodException e) {
			throw new PropertyNotFoundException();
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}		
		return filteredList;
	}

	public void getCitiesList() {
		fileService = new FileService();
		cities = fileService.retrieveCities();
	}

	public Integer countDistinct(String property) throws EmptyCityListException, PropertyNotFoundException {
		if(cities == null || cities.isEmpty()) throw new EmptyCityListException();
		ArrayList<CityDTO> uniqueList = new ArrayList<CityDTO>();
		try {
			Class clazz = CityDTO.class;
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
			Method method;
			method = clazz.getMethod(methodName);
			for (CityDTO cityDTO : cities) {
				String value = method.invoke(cityDTO).toString().toLowerCase();
				if(isUnique(uniqueList, method, value)) uniqueList.add(cityDTO);
			}
		} catch (NoSuchMethodException e) {
			throw new PropertyNotFoundException();
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return uniqueList.size();
	}

	private boolean isUnique(List<CityDTO> uniqueList, Method method, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (CityDTO cityDTO : uniqueList) {
			if(method.invoke(cityDTO).toString().toLowerCase().equals(value)) return false;
		}
		return true;
	}
}
