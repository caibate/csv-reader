package br.com.rogerio.desafio.tests.cases;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.rogerio.desafio.exception.InvalidDataFormatException;
import br.com.rogerio.desafio.exception.InvalidFileException;
import br.com.rogerio.desafio.normalizer.Normalizer;

public class NormalizerTest {

	
	@Test
	public void alreadyNormalizedString() throws InvalidFileException, InvalidDataFormatException {
		assertEquals("Teste",Normalizer.normalizeText("Teste"));
	}
	
	@Test
	public void removeAccents() throws InvalidFileException, InvalidDataFormatException {
		assertEquals("Rogrio Torres Filho",Normalizer.normalizeText("Rogrio Torres Filho"));
	}
	
	@Test
	public void removeDoubleWhitespaces() throws InvalidFileException, InvalidDataFormatException {
		assertEquals("Rogerio Torres",Normalizer.normalizeText("Rogerio  Torres"));
	}
	
	@Test
	public void nullInput() throws InvalidFileException, InvalidDataFormatException {
		assertEquals("Teste",Normalizer.normalizeText("Teste"));
	}
	
	@Test
	public void specialCharacter() throws InvalidFileException, InvalidDataFormatException {
		assertEquals("Rgerio",Normalizer.normalizeText("R@gerio"));
	}
}