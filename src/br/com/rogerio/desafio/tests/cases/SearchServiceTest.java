package br.com.rogerio.desafio.tests.cases;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.CommandNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import br.com.rogerio.desafio.service.SearchService;

public class SearchServiceTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}
	
	@Test
	public void countAll() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count *", "cidades.csv");
		assertEquals("Total: 5565", outContent.toString());
	}
	
	@Test(expected = CommandNotFoundException.class)
	public void invalidCountAll() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count all", "cidades.csv");
	}
	
	@Test(expected = CommandNotFoundException.class)
	public void invalidFilterCommand() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("filter teste", "cidades.csv");
	}
	
	@Test()
	public void countDistinctUf() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count distinct uf", "cidades.csv");
		assertEquals("Total: 27", outContent.toString());
	}
	
	@Test()
	public void countDistinctMesoRegion() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count distinct mesoregion", "cidades.csv");
		assertEquals("Total: 137", outContent.toString());
	}
	
	@Test()
	public void countDistinctMicroRegion() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count distinct microregion", "cidades.csv");
		assertEquals("Total: 554", outContent.toString());
	}

	@Test(expected = PropertyNotFoundException.class)
	public void invalidPropertyFilter() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException{
		SearchService service = new SearchService();
		service.execute("filter property value", "cidades.csv");
	}
	
	//REVER
	@Test()
	public void filterNameFlorianopolis() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("filter name FLORIANÓPOLIS", "cidades.csv");
		assertTrue(outContent.toString().contains("Total: 1"));
	}
	
	@Test(expected = CommandNotFoundException.class)
	public void emptyCommand() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("", "cidades.csv");
	}
	
	@Test(expected = CommandNotFoundException.class)
	public void nullCommand() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute(null, "cidades.csv");
	}
	
	@Test(expected = InvalidFileException.class)
	public void invalidFileLocation() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count *", "invalid.csv");
	}

	@Test(expected = InvalidFileException.class)
	public void nullFileLocation() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count *", null);
	}
	
	@Test(expected = InvalidFileException.class)
	public void emptyFileLocation() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count *", "");
	}
	
	@Test(expected = InvalidDataFormatException.class)
	public void invalidFile() throws CommandNotFoundException, EmptyCityListException, CityNotFoundException, PropertyNotFoundException, InvalidFileException, InvalidDataFormatException {
		SearchService service = new SearchService();
		service.execute("count *", "invalidFile.csv");
	}
}
