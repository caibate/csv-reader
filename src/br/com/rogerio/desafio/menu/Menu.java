package br.com.rogerio.desafio.menu;

import java.util.Scanner;

import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.model.CommandEnum;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);
	
	public void printMenu() {
		System.out.println("--- City Finder ---");
		System.out.println("Commands: 'count *', 'count distinct <property>', 'filter <property> <value>', 'exit' \n");
	}

	public String getCommand() throws CommandNotFoundException {
		System.out.print("Enter your command: ");
		String command = scanner.nextLine().toUpperCase();
		if(!isValid(command)) throw new CommandNotFoundException();
		return command;
	}

	private boolean isValid(String command) {
		String[] commandArray = command.split(" ");
		if(command == null || command.isEmpty() || commandArray.length == 0) return false;
		if(command.equals(CommandEnum.EXIT.toString())) return true;
		if (commandArray[0].equals(CommandEnum.FILTER.toString()))
			if (commandArray.length >= 3) return true;
			else return false;
		if (commandArray[0].equals(CommandEnum.COUNT.toString())) {
			if (commandArray.length == 2) return isCountTotal(commandArray);
			if (commandArray.length == 3) return true;
		}
		return false;
	}
	
	public void closeScanner(){
		if(scanner != null) scanner.close();
	}
	
	private boolean isCountTotal(String[] commandArray) {
		if(commandArray[1].equals("*")) return true;
		return false;
	}
}
