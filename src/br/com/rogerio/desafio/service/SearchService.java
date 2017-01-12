package br.com.rogerio.desafio.service;

import java.util.ArrayList;

import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import br.com.rogerio.desafio.file.CitiesFileLoader;
import br.com.rogerio.desafio.model.CityDTO;
import br.com.rogerio.desafio.model.CommandEnum;

public class SearchService {

	private CityService cityService;
	private CitiesFileLoader fileLoader;

	public void execute(String command, String fileLocation) throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException{
		if(command == null || command.isEmpty()) throw new CommandNotFoundException(); 
		fileLoader = new CitiesFileLoader();
		cityService = new CityService();
		cityService.setCities(fileLoader.loadCities(fileLocation));
		if(command.equals(CommandEnum.EXIT.toString())) return;
		if(command.startsWith(CommandEnum.COUNT.toString())) count(command);
		if(command.startsWith(CommandEnum.FILTER.toString())) filter(command);
	}

	private void filter(String command) throws CommandNotFoundException, CityNotFoundException, EmptyCityListException, PropertyNotFoundException {
		if(command == null || command.isEmpty() || command.split(" ").length < 3) throw new CommandNotFoundException();
		String[] commandArray = command.split(" ");
		printFilterReturn(cityService.filter(commandArray[1], getPropertyName(command)));
	}

	private String getPropertyName(String command) {
		String c = command.replaceFirst(command.split(" ")[0], "").trim();
		return c.substring(c.indexOf(" ", 0), c.length()).trim();
	}

	private void count(String command) throws CommandNotFoundException, EmptyCityListException, PropertyNotFoundException {
		if(command == null || command.isEmpty() || !isValidCountCommand(command)) throw new CommandNotFoundException();
		String[] commandArray = command.split(" ");
		if(command.split(" ").length == 2 && commandArray[1].equals("*")) printCountReturn(cityService.countTotal());
		if(command.split(" ").length == 3) printCountReturn(cityService.countDistinct(getPropertyName(command)));
	}

	private boolean isValidCountCommand(String command) {
		return (command.split(" ").length == 2 && command.split(" ")[1].equals("*")) || command.split(" ").length == 3;
	}
	
	private void printFilterReturn(ArrayList<CityDTO> result) throws CityNotFoundException{
		if(result == null || result.isEmpty()) throw new CityNotFoundException();
		System.out.println("ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion");
		for (CityDTO cityDTO : result) {
			System.out.println(cityDTO.toString());
		}
		System.out.println("Total: " + result.size());
	}
	
	private void printCountReturn(Integer result){
		System.out.print("Total: " + result);
	}
}
