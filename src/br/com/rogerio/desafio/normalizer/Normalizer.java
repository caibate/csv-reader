package br.com.rogerio.desafio.normalizer;

public class Normalizer {

	public static String normalizeText(String value) {
		if (value == null || value.isEmpty())
			return value;
//		value = removeAcentoECedilha(value);
		value = removerCaracteresEspeciais(value);
		value = removerEspacosExtras(value);
		return value;
	}

	private static String removeAcentoECedilha(String value) {
		return java.text.Normalizer.normalize(value, java.text.Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
	}

	private static String removerEspacosExtras(String value) {
		return value.replaceAll("  ", " ").trim();
	}

	public static String removerCaracteresEspeciais(String value) {
		return value.replaceAll("[^\\w\\s]", "");
	}

}
