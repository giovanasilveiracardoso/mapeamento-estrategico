package br.com.empresa.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import br.com.empresa.util.UtilGeolocation;

public class UtilGeolocationTest {

	@Test
	public void haversine() {
		Double distancia = UtilGeolocation.haversine(-27.6019111, -48.5957299, -27.6066129, -48.5803426);
		assertThat(distancia).isEqualTo(1.603832548369759);
	}
	
	@Test
	public void formatarDistanciaMaiorQueUm() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(1.1);
		assertThat(distanciaFormatada).isEqualTo("1 Km e 100 Metros");
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
	
	@Test
	public void formatarDistancia1QuilometroE5Metros() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(1.005);
		assertThat(distanciaFormatada).isEqualTo("1 Km e 5 Metros");
	}
	
	@Test
	public void formatarDistancia2QuilometrosE50Metros() {
		String distanciaFormatada = UtilGeolocation.formatarDistancia(2.05);
		assertThat(distanciaFormatada).isEqualTo("2 Km e 50 Metros");
	}
}
