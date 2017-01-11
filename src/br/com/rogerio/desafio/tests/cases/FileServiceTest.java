package br.com.rogerio.desafio.tests.cases;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import br.com.rogerio.desafio.dto.CityDTO;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.service.CitiesFileLoader;

public class FileServiceTest {

	
	@Test
	public void retrieveCitiesList() throws InvalidFileException, InvalidDataFormatException {
		CitiesFileLoader fileService = new CitiesFileLoader();
		ArrayList<CityDTO> citiesList = new ArrayList<CityDTO>();
		citiesList = fileService.loadCities("cidades.csv");
		assertNotNull(citiesList);
		assertTrue(citiesList.size() > 0);
	}
	
	@Test(expected = InvalidDataFormatException.class)
	public void invalidFile() throws InvalidFileException, InvalidDataFormatException{
		CitiesFileLoader fileService = new CitiesFileLoader();
		ArrayList<CityDTO> citiesList = fileService.loadCities("invalidFile.csv");
	}
	
	@Test(expected = InvalidFileException.class)
	public void fileNotFound() throws InvalidFileException, InvalidDataFormatException{
		CitiesFileLoader fileService = new CitiesFileLoader();
		ArrayList<CityDTO> citiesList = fileService.loadCities("fileNotFound.csv");
	}
	
	@Test(expected= InvalidFileException.class)
	public void nullFileLocation() throws InvalidFileException, InvalidDataFormatException{
		CitiesFileLoader fileService = new CitiesFileLoader();
		ArrayList<CityDTO> citiesList = fileService.loadCities(null);		
	}
	
	@Test(expected= InvalidFileException.class)
	public void emptyFileLocation() throws InvalidFileException, InvalidDataFormatException{
		CitiesFileLoader fileService = new CitiesFileLoader();
		ArrayList<CityDTO> citiesList = fileService.loadCities("");		
	}

}
