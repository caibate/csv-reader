package br.com.rogerio.desafio.normalizer;

public class Normalizer {

	public static String normalizeText(String value) {
		if (value == null || value.isEmpty())
			return value;
		value = removerCaracteresEspeciais(value);
		value = removerEspacosExtras(value);
		return value;
	}

	private static String removerEspacosExtras(String value) {
		return value.replaceAll("  ", " ").trim();
	}

	public static String removerCaracteresEspeciais(String value) {
		return value.replaceAll("[^\\w\\s]", "");
	}

}
