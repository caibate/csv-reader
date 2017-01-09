package br.com.rogerio.desafio.service;

import java.util.ArrayList;

import br.com.rogerio.desafio.dto.CityDTO;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.ParsingErroException;

public class SearchService {

private CityService cityService = new CityService();
	
	public void execute(String command) throws CommandNotFoundException, EmptyCityListException, ParsingErroException{
		if(command.equals("EXIT")) return;
		if(command.startsWith("COUNT")) countCities(command);
		if(command.startsWith("FILTER")) filterCities(command);
	}

	private void filterCities(String command) throws CommandNotFoundException, ParsingErroException, EmptyCityListException {
		if(command == null || command.isEmpty() || command.split(" ").length < 3) throw new CommandNotFoundException();
		String[] commandArray = command.split(" ");
		printFilterReturn(cityService.filter(commandArray[1].toUpperCase(), getFilterValue(command)));
	}

	private String getFilterValue(String command) {
		String c = command.replaceFirst("FILTER", "").trim();
		return c.substring(c.indexOf(" ", 0), c.length()).trim();
	}

	private void countCities(String command) throws CommandNotFoundException, EmptyCityListException {
		if(command == null || command.isEmpty() || !isValidCountCommand(command)) throw new CommandNotFoundException();
		String[] commandArray = command.split(" ");
		if(command.split(" ").length == 2 && commandArray[1].equals("*")) printCountReturn(cityService.countTotal());
		if(command.split(" ").length == 3) printCountReturn(cityService.countDistinct(commandArray[1]));
	}

	private boolean isValidCountCommand(String command) {
		return command.split(" ").length == 2 || command.split(" ").length == 3;
	}
	
	private void printFilterReturn(ArrayList<CityDTO> result){
		for (CityDTO cityDTO : result) {
			System.out.println(cityDTO.toString());
		}
		System.out.println("\n");
	}
	
	private void printCountReturn(Integer result){
		System.out.println("Total cities: " + result + "\n");
		
	}
}
