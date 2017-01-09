package br.com.rogerio.desafio.service;

import java.util.ArrayList;

import br.com.rogerio.desafio.dto.CityDTO;
import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;

public class SearchService {

	private CityService cityService = new CityService();

	public void execute(String command) throws CommandNotFoundException, EmptyCityListException, CityNotFoundException{
		if(command.equals("exit")) return;
		if(command.startsWith("count")) countCities(command);
		if(command.startsWith("filter")) filterCities(command);
	}

	private void filterCities(String command) throws CommandNotFoundException, CityNotFoundException, EmptyCityListException {
		if(command == null || command.isEmpty() || command.split(" ").length < 3) throw new CommandNotFoundException();
		String[] commandArray = command.split(" ");
		printFilterReturn(cityService.filter(commandArray[1], getFilterValue(command)));
	}

	private String getFilterValue(String command) {
		String c = command.replaceFirst("filter", "").trim();
		return c.substring(c.indexOf(" ", 0), c.length()).trim();
	}

	private void countCities(String command) throws CommandNotFoundException, EmptyCityListException {
		if(command == null || command.isEmpty() || !isValidCountCommand(command)) throw new CommandNotFoundException();
		String[] commandArray = command.split(" ");
		if(command.split(" ").length == 2 && commandArray[1].equals("*")) printCountReturn(cityService.countTotal());
//		if(command.split(" ").length == 3) printCountReturn(cityService.countDistinct(commandArray[1]));
	}

	private boolean isValidCountCommand(String command) {
		return command.split(" ").length == 2 || command.split(" ").length == 3;
	}
	
	private void printFilterReturn(ArrayList<CityDTO> result) throws CityNotFoundException{
		if(result == null || result.isEmpty()) throw new CityNotFoundException();
		System.out.println("ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion");
		for (CityDTO cityDTO : result) {
			System.out.println(cityDTO.toString());
		}
		System.out.println("\n");
	}
	
	private void printCountReturn(Integer result){
		System.out.println("Total cities: " + result + "\n");
		
	}
}
