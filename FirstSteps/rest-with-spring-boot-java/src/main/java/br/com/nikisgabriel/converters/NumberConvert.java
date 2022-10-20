package br.com.nikisgabriel.converters;

public class NumberConvert {
	public static Double convertToDouble(String strNumber) {
		if(strNumber == null)return 0D;
		strNumber = strNumber.replace(",", ".");
		if(!isNumeric(strNumber)) return 0D;
		return Double.parseDouble(strNumber);
	}
	
	public static boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		strNumber = strNumber.replace(",", ".");
		return strNumber.matches("[+-]?[0-9]*\\.?[0-9]+");
	}
}
