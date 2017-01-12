package br.com.rogerio.desafio.tests.cases;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.com.rogerio.desafio.exception.CityNotFoundException;
import br.com.rogerio.desafio.exception.EmptyCityListException;
import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.exception.PropertyNotFoundException;
import br.com.rogerio.desafio.file.CitiesFileLoader;
import br.com.rogerio.desafio.model.City;
import br.com.rogerio.desafio.service.CityService;

public class CityServiceTest {

	private CitiesFileLoader fileService;
	private CityService service;
	
	@Before
	public void config() throws InvalidFileException, InvalidDataFormatException{
		fileService = new CitiesFileLoader();
		service = new CityService();
		service.setCities(fileService.loadCities("resource/cidades.csv"));
	}
	
	@Test
	public void countTotal() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		assertTrue(service.countTotal().equals(5565));
	}

	@Test(expected = EmptyCityListException.class)
	public void countTotalEmptyList() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException {
		service.setCities(null);
		service.countTotal();
	}

	@Test()
	public void countDistinctUf() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		assertTrue(service.countDistinct("uf").equals(27));
	}
	
	@Test()
	public void countDistinctMesoRegion() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		assertTrue(service.countDistinct("mesoregion").equals(137));
	}
	
	@Test()
	public void countDistinctMicroRegion() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		assertTrue(service.countDistinct("microregion").equals(554));
	}
	
	@Test()
	public void countDistinctId() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		assertTrue(service.countDistinct("ibge_id").equals(5565));
	}
	
	@Test()
	public void countDistinctName() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		assertTrue(service.countDistinct("name").equals(5291));
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void countInvalidProperty() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		service.countDistinct("invalidproperty");
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void countEmptyProperty() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		service.countDistinct("");
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void countNullProperty() throws EmptyCityListException, InvalidFileException, InvalidDataFormatException, PropertyNotFoundException {
		service.countDistinct(null);
	}

	@Test
	public void filterByNotFoundName() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("name", "NOME");
		assertEquals(0, cities.size());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByInvalidProperty() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("size", "1");
		assertEquals(0, cities.size());
	}
	
	@Test(expected = EmptyCityListException.class)
	public void filterEmptyList() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		service.setCities(new ArrayList<City>());
		service.filter("name", "Cabixi");
	}
	
	@Test()
	public void filterByName() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("name", "Cabixi");
		assertEquals(1, cities.size());
		assertEquals("1100031", cities.get(0).getIbge_id().toString());
		assertEquals("RO", cities.get(0).getUf());
		assertEquals("Cabixi", cities.get(0).getName());
		assertEquals(false, cities.get(0).getCapital());
		assertEquals("-60.5443135812", cities.get(0).getLon().toString());
		assertEquals("-13.4997634597", cities.get(0).getLat().toString());
		assertEquals("Cabixi", cities.get(0).getNo_accents());
		assertEquals("", cities.get(0).getAlternative_names());
		assertEquals("Colorado do Oeste", cities.get(0).getMicroregion());
		assertEquals("Leste Rondoniense", cities.get(0).getMesoregion());
	}
	
	@Test()
	public void normalizeAndFilterByName() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("name", "Florianópolis");
		assertEquals(cities.size(), 1);
		assertEquals("4205407", cities.get(0).getIbge_id().toString());
		assertEquals("SC", cities.get(0).getUf());
		assertEquals("Florianópolis", cities.get(0).getName());
		assertEquals(true, cities.get(0).getCapital());
		assertEquals("-48.5476373782", cities.get(0).getLon().toString());
		assertEquals("-27.5877955486", cities.get(0).getLat().toString());
		assertEquals("Florianopolis", cities.get(0).getNo_accents());
		assertEquals("", cities.get(0).getAlternative_names());
		assertEquals("Florianópolis", cities.get(0).getMicroregion());
		assertEquals("Grande Florianópolis", cities.get(0).getMesoregion());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullName() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		service.filter("name", null);
	}
	
	@Test
	public void filterByEmptyName() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("name", "");
		assertEquals(0, cities.size());
	}
	
	@Test()
	public void filterByUf() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("uf", "RO");
		assertEquals(52, cities.size());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullUf() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		service.filter("uf", null);
	}

	@Test
	public void filterByEmptyUf() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("uf", "");
		assertEquals(0, cities.size());
	}
	
	@Test
	public void filterByNotFoundUf() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("uf", "XX");
		assertEquals(0, cities.size());
	}

	@Test
	public void filterByCapital() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("capital", "true");
		assertEquals(27, cities.size());
	}
	
	@Test
	public void filterByNonCapital() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("capital", "false");
		assertEquals(5538, cities.size());
	}
	
	@Test
	public void filterByEmptyCapital() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("capital", "");
		assertEquals(0, cities.size());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullCapital() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("capital", null);
		assertEquals(0, cities.size());
	}
	
	@Test()
	public void filterByLatitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("lat", "-13.4997634597");
		assertEquals(1, cities.size());
		assertEquals("1100031", cities.get(0).getIbge_id().toString());
		assertEquals("RO", cities.get(0).getUf());
		assertEquals("Cabixi", cities.get(0).getName());
		assertEquals(false, cities.get(0).getCapital());
		assertEquals("-60.5443135812", cities.get(0).getLon().toString());
		assertEquals("-13.4997634597", cities.get(0).getLat().toString());
		assertEquals("Cabixi", cities.get(0).getNo_accents());
		assertEquals("", cities.get(0).getAlternative_names());
		assertEquals("Colorado do Oeste", cities.get(0).getMicroregion());
		assertEquals("Leste Rondoniense", cities.get(0).getMesoregion());
	}

	@Test()
	public void filterByNotFoundLatitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("lat", "666");
		assertEquals(0, cities.size());
	}
	
	@Test()
	public void filterByEmptyLatitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("lat", "");
		assertEquals(0, cities.size());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullLatitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		service.filter("lat", null);
	}
	
	@Test()
	public void filterByLongitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("lon", "-60.5443135812");
		assertEquals(1, cities.size());
		assertEquals("1100031", cities.get(0).getIbge_id().toString());
		assertEquals("RO", cities.get(0).getUf());
		assertEquals("Cabixi", cities.get(0).getName());
		assertEquals(false, cities.get(0).getCapital());
		assertEquals("-60.5443135812", cities.get(0).getLon().toString());
		assertEquals("-13.4997634597", cities.get(0).getLat().toString());
		assertEquals("Cabixi", cities.get(0).getNo_accents());
		assertEquals("", cities.get(0).getAlternative_names());
		assertEquals("Colorado do Oeste", cities.get(0).getMicroregion());
		assertEquals("Leste Rondoniense", cities.get(0).getMesoregion());
	}

	@Test()
	public void filterByNotFoundLongitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("lon", "666");
		assertEquals(0, cities.size());
	}
	
	@Test()
	public void filterByEmptyLongitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("lon", "");
		assertEquals(0, cities.size());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullLongitude() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		service.filter("lon", null);
	}
	
	@Test()
	public void filterByNoAccents() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("no_accents", "Florianopolis");
		assertEquals(cities.size(), 1);
		assertEquals("4205407", cities.get(0).getIbge_id().toString());
		assertEquals("SC", cities.get(0).getUf());
		assertEquals("Florianópolis", cities.get(0).getName());
		assertEquals(true, cities.get(0).getCapital());
		assertEquals("-48.5476373782", cities.get(0).getLon().toString());
		assertEquals("-27.5877955486", cities.get(0).getLat().toString());
		assertEquals("Florianopolis", cities.get(0).getNo_accents());
		assertEquals("", cities.get(0).getAlternative_names());
		assertEquals("Florianópolis", cities.get(0).getMicroregion());
		assertEquals("Grande Florianópolis", cities.get(0).getMesoregion());
	}
	
	@Test()
	public void filterByNotFoundNoAccents() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("no_accents", "cidade");
		assertEquals(0, cities.size());
	}
	
	@Test()
	public void filterByEmptyNoAccents() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("no_accents", "");
		assertEquals(0, cities.size());
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullNoAccents() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("no_accents", null);
		assertEquals(0, cities.size());
	}
	
	@Test()
	public void filterByAltNames() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("alternative_names", "Assu");
		assertEquals(cities.size(), 1);
		assertEquals("2400208", cities.get(0).getIbge_id().toString());
		assertEquals("RN", cities.get(0).getUf());
		assertEquals("Açu", cities.get(0).getName());
		assertEquals(false, cities.get(0).getCapital());
		assertEquals("-36.91792322", cities.get(0).getLon().toString());
		assertEquals("-5.5719459992", cities.get(0).getLat().toString());
		assertEquals("Acu", cities.get(0).getNo_accents());
		assertEquals("Assu", cities.get(0).getAlternative_names());
		assertEquals("Vale do Açu", cities.get(0).getMicroregion());
		assertEquals("Oeste Potiguar", cities.get(0).getMesoregion());
	}
	
	@Test()
	public void filterByNotFoundAltNames() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("alternative_names", "cidade");
		assertEquals(cities.size(), 0);
	}
	
	@Test()
	public void filterByEmptyAltNames() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("alternative_names", "");
		assertEquals(cities.size(), 5563);
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullAltNames() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("alternative_names", null);
		assertEquals(cities.size(), 0);
	}
	
	@Test()
	public void filterByMicroregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("microregion", "Florianópolis");
		assertEquals(cities.size(), 9);
	}
	
	@Test()
	public void filterByNotFoundMicroregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("microregion", "cidade");
		assertEquals(cities.size(), 0);
	}
	
	@Test()
	public void filterByEmptyMicroregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("microregion", "");
		assertEquals(cities.size(), 0);
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullMicroregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("microregion", null);
		assertEquals(cities.size(), 0);
	}
	
	@Test()
	public void filterByMesoregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("mesoregion", "Grande Florianópolis");
		assertEquals(cities.size(), 21);
	}
	
	@Test()
	public void filterByNotFoundMesoregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("mesoregion", "cidade");
		assertEquals(cities.size(), 0);
	}
	
	@Test()
	public void filterByEmptyMesoregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("mesoregion", "");
		assertEquals(cities.size(), 0);
	}
	
	@Test(expected = PropertyNotFoundException.class)
	public void filterByNullMesoregion() throws CityNotFoundException, EmptyCityListException, PropertyNotFoundException{
		ArrayList<City> cities = service.filter("mesoregion", null);
		assertEquals(cities.size(), 0);
	}
}
