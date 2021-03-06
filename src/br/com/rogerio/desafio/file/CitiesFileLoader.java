package br.com.rogerio.desafio.file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.model.City;

public class CitiesFileLoader {

	private static String FILE_DIVISOR = ",";
	
	public ArrayList<City> loadCities(String fileLocation) throws InvalidFileException, InvalidDataFormatException {
		if(fileLocation == null || fileLocation.equals("")) throw new InvalidFileException();
		ArrayList<City> cities = new ArrayList<City>();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(fileLocation));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] city = line.split(FILE_DIVISOR);
				City cityDTO = new City(Long.parseLong(city[0]), city[1], city[2], Boolean.parseBoolean(city[3]),
						Double.parseDouble(city[4]), Double.parseDouble(city[5]), city[6], city[7], city[8], city[9]);
				cities.add(cityDTO);
			}
			if(cities.isEmpty()) throw new InvalidFileException();
		} catch (FileNotFoundException e) {
			throw new InvalidFileException();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			throw new InvalidDataFormatException();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return cities;
	}
}
