package br.com.rogerio.desafio.tests.cases;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import br.com.rogerio.desafio.service.CityService;
import br.com.rogerio.desafio.service.FileService;

public class CityServiceTest {

	private FileService fileService = new FileService();
	
	@Test
	public void countTotal() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		assertTrue(service.countTotal().equals(5565));
	}

	@Test(expected = InvalidFileException.class)
	public void countTotalEmptyFileLocation() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities(""));
	}
	
	@Test(expected = InvalidFileException.class)
	public void countTotalInvalidFileLocation() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("invalid.txt"));
	}
	
	@Test(expected = InvalidDataFormatException.class)
	public void countTotalInvalidDataFormat() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("invalidFile.csv"));
	}
	
	@Test(expected = EmptyCityListException.class)
	public void countTotalEmptyList() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		CityService service = new CityService();
		service.countTotal();
	}

	@Test()
	public void countDistinctUf() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		assertTrue(service.countDistinct("uf").equals(27));
	}
	
	@Test()
	public void countDistinctMesoRegion() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		assertTrue(service.countDistinct("mesoregion").equals(137));
	}
	
	@Test()
	public void countDistinctMicroRegion() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		assertTrue(service.countDistinct("microregion").equals(554));
	}
	
	@Test()
	public void countDistinctId() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		assertTrue(service.countDistinct("ibge_id").equals(5565));
	}
	
	@Test()
	public void countDistinctName() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		assertTrue(service.countDistinct("name").equals(5291));
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void countInvalidProperty() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		service.countDistinct("invalidproperty");
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void countEmptyProperty() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		service.countDistinct("");
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void countNullProperty() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		CityService service = new CityService();
		service.setCities(fileService.retrieveCities("cidades.csv"));
		service.countDistinct(null);
	}
	
	
}
