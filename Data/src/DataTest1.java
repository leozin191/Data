import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class DataTest1 {

	@Test
	public void testValidMes() {
		DataTest m = new DataTest();
		m.setMes(12);
		int mes = m.getMes();
		assertEquals(12, mes);

	}

	@Test
	public void testValidDia() {
		DataTest d = new DataTest();
		d.setDia(15);
		int dia = d.getDia();
		assertEquals(15, dia);
	}

	@Test
	public void testValidAno() {
		DataTest a = new DataTest();
		a.setAno(2009);
		int ano = a.getAno();
		assertEquals(2009, ano);
	}

	@Test
	public void testValidData() {
		Random rand = new Random();
		int dia = rand.nextInt(30) + 30;
		int mes = rand.nextInt(12) + 12;
		int ano = rand.nextInt(10000);
		int[] data = { dia, mes, ano };
		DataTest r = new DataTest();
		Assert.assertTrue(r.validData(data) < 0);

	}

	@Test
	public void testGetMaxDias() {
		DataTest max = new DataTest();
		int mes = 0, ano = max.getMaxDias(02, 2020);
		assertEquals(29, mes, ano);
	}

	@Test
	public void testAddDias() {
		DataTest add = new DataTest();
		add.setDia(25);
		add = add.AddDias(10);
		assertEquals(4, add.getDia());
		assertEquals(2, add.getMes());

	}

	@Test
	public void testCompareData() {
		DataTest comp = new DataTest();
		DataTest comp1 = new DataTest(19, 01, 2005);
		int comparator = DataTest.compareData(comp, comp1);
		assertEquals(1, comparator);

	}

}
