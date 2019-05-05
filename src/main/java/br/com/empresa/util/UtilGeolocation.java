package br.com.empresa.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilGeolocation {

	private final static Double RAIO_TOTAL_DA_TERRA = 6371.0;
	private final static Double PI_EM_RADIANOS = 0.017453292519943295;
	
	public static Double haversine(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {
		Double somatorioInterno = 0.5 - Math.cos((latitude2 - latitude1) * PI_EM_RADIANOS) / 2
				+ Math.cos(latitude1 * PI_EM_RADIANOS) * Math.cos(latitude2 * PI_EM_RADIANOS)
						* (1 - Math.cos((longitude2 - longitude1) * PI_EM_RADIANOS)) / 2;

		return (RAIO_TOTAL_DA_TERRA * 2) * Math.asin(Math.sqrt(somatorioInterno));
	}

	public static String formatarDistancia(Double distancia) {
		String unidadeDeMedidaMetros = " Metros";
		Integer quilometro = new BigDecimal(distancia).setScale(0, RoundingMode.DOWN).intValue();
		BigDecimal casaDecimal = new BigDecimal(distancia - quilometro).setScale(3, RoundingMode.HALF_DOWN);
		Integer metrosDeDistancia = new BigDecimal(casaDecimal.doubleValue() * 1000.0).setScale(0, RoundingMode.HALF_DOWN).intValue();
		String distanciaFormatada = metrosDeDistancia.toString() + unidadeDeMedidaMetros;
		
		if (quilometro >= 1) {
			String unidadeDeMedidaKm = " Km";
			
			String distanciaFormatadaEmKm = quilometro.toString() + unidadeDeMedidaKm;
			if(metrosDeDistancia > 0) {
				distanciaFormatadaEmKm += " e " + distanciaFormatada;
			}
			
			return distanciaFormatadaEmKm;
		}
		
		return distanciaFormatada;
	}
	
}
