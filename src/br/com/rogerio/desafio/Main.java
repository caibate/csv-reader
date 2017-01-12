package br.com.rogerio.desafio;

import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import br.com.rogerio.desafio.menu.Menu;
import br.com.rogerio.desafio.model.CommandEnum;
import br.com.rogerio.desafio.service.CityService;

public class Main {

	private static final String FILE_LOCATION = "cidades.csv";
	
	public static void main(String[] args) {
		CityService service = new CityService();
		try {
			service.loadCities(FILE_LOCATION);
		} catch (InvalidFileException | InvalidDataFormatException e1) {
			System.out.println("Invalid file. Exiting... \n");	
			return;
		}
		Menu menu = new Menu();
		menu.printMenu();
		while(true) {
			try {
				String command = menu.getCommand();
				if(command.equals(CommandEnum.EXIT.toString())) break;
				service.execute(command);
			} catch (CommandNotFoundException e) {
				System.out.println("Command not found. \n");
			} catch (EmptyCityListException e) {
				System.out.println("Empty cities list. \n");
			}  catch (CityNotFoundException e) {
				System.out.println("No city found by filter. \n");
			} catch (PropertyNotFoundException e) {
				System.out.println("Property not found. \n");
			}
		System.out.println("\n");
		}
		System.out.println("Exiting...");
	}

}
