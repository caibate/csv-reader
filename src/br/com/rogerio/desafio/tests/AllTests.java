package br.com.rogerio.desafio.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.rogerio.desafio.tests.cases.CityServiceTest;
import br.com.rogerio.desafio.tests.cases.FileServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ CityServiceTest.class, FileServiceTest.class })
public class AllTests {

}
