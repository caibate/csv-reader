package br.com.rogerio.desafio.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.rogerio.desafio.dto.CityDTO;

public class FileService {

	private static String FILE_DIVISOR = ",";
	private static String FILE_LOCATION = "cidades.csv";
	
	public ArrayList<CityDTO> retrieveCities() {
		ArrayList<CityDTO> cities = new ArrayList<CityDTO>();
		BufferedReader br = null;
		String line = "";
		try {
			br = new BufferedReader(new FileReader(FILE_LOCATION));
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] city = line.split(FILE_DIVISOR);
				CityDTO cityDTO = new CityDTO(Long.parseLong(city[0]), city[1], city[2], Boolean.parseBoolean(city[3]),
						Double.parseDouble(city[4]), Double.parseDouble(city[5]), city[6], city[7], city[8], city[9]);
				cities.add(cityDTO);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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
