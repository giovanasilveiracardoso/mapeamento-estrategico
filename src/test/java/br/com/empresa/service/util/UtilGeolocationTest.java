package br.com.empresa.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class UtilGeolocationTest {

	@Test
	public void formatarDistanciaMaiorQueUm() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(1.1);
		assertThat(distanciaFormatada).isEqualTo("1 Km");
	}
	
	@Test
	public void formatarDistanciaIgualUm() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(1.0);
		assertThat(distanciaFormatada).isEqualTo("1 Km");
	}	
	
	@Test
	public void formatarDistanciaIgualZero() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(0.0);
		assertThat(distanciaFormatada).isEqualTo("0 Metros");
	}
	
	@Test
	public void formatarDistanciaIgual999Metros() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(0.999);
		assertThat(distanciaFormatada).isEqualTo("999 Metros");
	}
	
	@Test
	public void formatarDistanciaIgual500Metros() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(0.5);
		assertThat(distanciaFormatada).isEqualTo("500 Metros");
	}
	
	@Test
	public void formatarDistanciaIgual5Metros() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(0.005);
		assertThat(distanciaFormatada).isEqualTo("5 Metros");
	}
}
