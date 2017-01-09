package br.com.rogerio.desafio;

import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.ParsingErroException;
import br.com.rogerio.desafio.menu.Menu;
import br.com.rogerio.desafio.service.SearchService;

public class Main {

	
	public static void main(String[] args) {
		SearchService searchService = new SearchService();
		Menu menu = new Menu();
		menu.printMenu();
		while (true) {
			try {
				String command = menu.getCommand();
				if(command.equals("exit")) break;
				searchService.execute(command);
			} catch (CommandNotFoundException e) {
				System.out.println("Command not found. \n");
			} catch (EmptyCityListException e) {
				System.out.println("Empty cities list. \n");
			}  catch (CityNotFoundException e) {
				System.out.println("No city found by filter. \n");
			}
		}
		System.out.println("Exiting...");
	}

}
