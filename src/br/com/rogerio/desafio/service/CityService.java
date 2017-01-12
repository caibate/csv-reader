package br.com.rogerio.desafio.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import br.com.rogerio.desafio.file.CitiesFileLoader;
import br.com.rogerio.desafio.model.City;
import br.com.rogerio.desafio.model.CommandEnum;
import br.com.rogerio.desafio.normalizer.Normalizer;

public class CityService {

	private ArrayList<City> cities;
	private CitiesFileLoader fileLoader;
	
	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	public Integer countTotal() throws EmptyCityListException {
		if (cities == null || cities.isEmpty())
			throw new EmptyCityListException();
		return cities.size();
	}

	public CityService() {
		super();
		fileLoader = new CitiesFileLoader();
	}
	
	private String getPropertyValue(String command) {
		String c = command.replaceFirst(command.split(" ")[0], "").trim();
		return c.substring(c.indexOf(" ", 0), c.length()).trim();
	}
	
	public void execute(String command) throws EmptyCityListException, PropertyNotFoundException, CityNotFoundException, CommandNotFoundException{
		if(command == null || command.isEmpty()) throw new CommandNotFoundException(); 
		if(isExitCommand(command)) return;
		if(isCountCommand(command)) count(command);
		if(isFilterCommand(command)){
			printFilterReturn(filter(command.split(" ")[1], getPropertyValue(command)));
		}
		
	}

	private void printFilterReturn(ArrayList<City> filteredList) throws CityNotFoundException {
		if(filteredList == null || filteredList.isEmpty()) throw new CityNotFoundException();
		System.out.println("ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion");
		for (City cityDTO : filteredList) {
			System.out.println(cityDTO.toString());
		}
		System.out.println("Total: " + filteredList.size());
	}

	private boolean isExitCommand(String command) {
		return command.equals(CommandEnum.EXIT.toString());
	}

	private boolean isFilterCommand(String command) {
		return command.startsWith(CommandEnum.FILTER.toString()) && command.split(" ").length >= 3;
	}

	private boolean isCountCommand(String command) {
		return command.toUpperCase().startsWith(CommandEnum.COUNT.toString());
	}

	private void count(String command) throws EmptyCityListException, PropertyNotFoundException {
		int count = 0;
		if(command.split(" ").length == 2 && command.split(" ")[1].equals("*")) count = count();
		if(command.split(" ").length == 3) count = countDistinct(command.split(" ")[2]);
		System.out.print("Total: " + count);
	}

	public ArrayList<City> filter(String property, String value) throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		if(cities == null || cities.isEmpty()) throw new EmptyCityListException();
		if(property == null || value == null) throw new PropertyNotFoundException();
		ArrayList<City> filteredList = new ArrayList<City>();
		property = property.toLowerCase();
		String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
		try {
			for(City cityDTO : cities){
				Class clazz = City.class;
				Method method = clazz.getMethod(methodName);
				if(Normalizer.normalizeText(method.invoke(cityDTO).toString()).toLowerCase().equals(Normalizer.normalizeText(value.toLowerCase()))){
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
	
	public Integer count() throws EmptyCityListException{
		if(this.cities == null || cities.isEmpty()) throw new EmptyCityListException();
		return cities.size();
	}

	public Integer countDistinct(String property) throws EmptyCityListException, PropertyNotFoundException {
		if(property == null || property.isEmpty()) throw new PropertyNotFoundException();
		if(cities == null || cities.isEmpty()) throw new EmptyCityListException();
		ArrayList<City> uniqueList = new ArrayList<City>();
		try {
			Class clazz = City.class;
			String methodName = "get" + property.substring(0, 1).toUpperCase() + property.substring(1, property.length());
			Method method;
			method = clazz.getMethod(methodName);
			for (City cityDTO : cities) {
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

	private boolean isUnique(List<City> uniqueList, Method method, String value) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (City cityDTO : uniqueList) {
			if(method.invoke(cityDTO).toString().toLowerCase().equals(value)) return false;
		}
		return true;
	}

	public void loadCities(String fileLocation) throws InvalidFileException, InvalidDataFormatException {
		this.setCities(fileLoader.loadCities(fileLocation));
	}
}
