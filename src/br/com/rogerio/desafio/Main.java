package br.com.rogerio.desafio;

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
				searchService.execute(command);
				if(command.equals("EXIT")) break;
			} catch (CommandNotFoundException e) {
				System.out.println("Command not found.");
			} catch (EmptyCityListException e) {
				System.out.println("Empty cities list.");
			} catch (ParsingErroException e) {
				System.out.println("Invalid value for especificated property.");
			}
		}
		System.out.println("Exiting...");
	}

}
