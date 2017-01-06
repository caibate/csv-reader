package br.com.rogerio.desafio;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import br.com.rogerio.desafio.dto.CityDTO;

public class Main {

	public static void main(String[] args) {
		run();
	}

	public static void run() {

		ArrayList<CityDTO> cities = new ArrayList<CityDTO>();
		String arquivoCSV = "cidades.csv";
		BufferedReader br = null;
		String linha = "";
		String csvDivisor = ",";
		try {

			br = new BufferedReader(new FileReader(arquivoCSV));
			br.readLine();
			System.out.println("ibge_id, uf, name, capital, lon, lat, no_accents, alternative_names, microregion, mesoregion");
			while ((linha = br.readLine()) != null) {
				String[] city = linha.split(csvDivisor);
				CityDTO cityDTO = new CityDTO(Long.parseLong(city[0]), city[1], city[2], Boolean.parseBoolean(city[3]), Double.parseDouble(city[4]), Double.parseDouble(city[5]), city[6], city[7], city[8], city[9]);
				cities.add(cityDTO);
			}
			
			for (CityDTO cityDTO : cities) {
				System.out.println(cityDTO);
				break;
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
		
	}
}
