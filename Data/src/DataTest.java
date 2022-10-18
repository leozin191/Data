import java.util.Scanner;

public class DataTest {

	public static void main(String[] args) {
		Scanner leitorScanner = new Scanner(System.in);
		DataTest[] vetorData = new DataTest[2];

		for (int i = 0; i < 2; i++) {
			System.out.println("Digite uma data no formato xx/xx/xxxx:");
			String entrada = leitorScanner.nextLine();
			vetorData[i] = new DataTest(entrada);
		}
		int comparator = compareData(vetorData[0], vetorData[1]);
		DataTest maiorData = null;
		if (comparator == -1) {
			maiorData = vetorData[0];
		} else {
			maiorData = vetorData[1];
		}

		System.out.println("A maior data é:");
		maiorData.imprimir();

		System.out.println("Escolha uma das duas datas efetuar sua soma: (1 ou 2)");
		int entrada = leitorScanner.nextInt();
		DataTest d;
		if (entrada == 1) {
			d = vetorData[0];
		} else {
			d = vetorData[1];
		}

		System.out.println("Quantos dias quer adicionar na data?");
		entrada = leitorScanner.nextInt();
		DataTest adicionado = d.AddDias(entrada);

		System.out.println("A data adicionada é:");
		adicionado.imprimir();
		leitorScanner.close();

	}

	private int Dia;
	private int Mes;
	private int Ano;

	public int getDia() {
		return this.Dia;
	}

	public int getMes() {
		return this.Mes;
	}

	public int getAno() {
		return this.Ano;
	}

	public void setDia(int dia) {
		this.Dia = dia;
	}

	public void setMes(int mes) {
		this.Mes = mes;
	}

	public void setAno(int ano) {
		this.Ano = ano;
	}

	public DataTest(String string) {
		int[] data = dataFromString(string);
		if (validData(data) == 1) {
			this.setDia(data[0]);
			this.setMes(data[1]);
			this.setAno(data[2]);

		}
	}

	public DataTest(int dia, int mes, int ano) {
		this.Dia = dia;
		this.Mes = mes;
		this.Ano = ano;
	}

	public DataTest(DataTest other) {
		this.Dia = other.getDia();
		this.Mes = other.getMes();
		this.Ano = other.getAno();
	}

	public DataTest() {
		this(01, 01, 2001);
	}

	public static int[] dataFromString(String string) {
		String[] aux = string.split("/");
		int[] data = new int[3];
		data[0] = Integer.parseInt(aux[0]);
		data[1] = Integer.parseInt(aux[1]);
		data[2] = Integer.parseInt(aux[2]);

		return data;
	}

	public boolean Bissexto(int ano) {
		return (ano % 400 == 0 || (ano % 100 != 0 && ano % 4 == 0));
	}

	public boolean validMes(int mes) {
		return (mes > 0 && mes <= 12);
	}

	public boolean validDia(int dia, int max_dias) {
		return (dia > 0 && dia <= max_dias);
	}

	public boolean validAno(int ano) {
		return (ano >= 0 && ano < 2500);
	}

	public int validData(int[] dataTemp) {
		int max_dias;
		if (validMes(dataTemp[1])) {
			max_dias = getMaxDias(dataTemp[1], dataTemp[2]);
		} else {
			System.out.println("Mês Invalido");
			return -2;
		}
		if (!validDia(dataTemp[0], max_dias)) {
			if (dataTemp[0] == 29 && dataTemp[1] == 2)
				System.out.println("Ano não bissexto");
			else
				System.out.println("Dia Invalido");
			return -1;
		}
		if (!validAno(dataTemp[2])) {
			System.out.println("Ano Invalido");
			return -3;
		}
		return 1;
	}

	public int getMaxDias(int mes, int ano) {
		if (mes == 2)
			return (Bissexto(ano)) ? 29 : 28;
		else if (mes == 4 || mes == 6 || mes == 9 || mes == 11)
			return 30;
		return 31;
	}

	public DataTest AddDias(int num) {
		DataTest add = new DataTest(this);
		int diaSoma = add.getDia() + num;
		if (diaSoma > getMaxDias(add.getMes(), add.getAno())) {
			while (diaSoma > getMaxDias(add.getMes(), add.getAno())) {
				add.setDia(diaSoma - getMaxDias(add.getMes(), add.getAno()));
				diaSoma -= getMaxDias(add.getMes(), add.getAno());
				if (add.getMes() + 1 > 12) {
					add.setMes(1);
					add.setAno(add.getAno() + 1);
				} else
					add.setMes(add.getMes() + 1);
			}
		} else {
			add.setDia(diaSoma);
		}

		return add;

	}

	public static int compareData(DataTest data1, DataTest data2) {
		int maiorData = 0;
		if (data1.getAno() > data2.getAno()) {
			maiorData = -1;
		} else if (data1.getAno() < data2.getAno()) {
			maiorData = 1;
		} else {
			if (data1.getMes() > data2.getMes()) {
				maiorData = -1;
			} else if (data1.getMes() < data2.getMes()) {
				maiorData = 1;
			} else {
				if (data1.getDia() > data2.getDia()) {
					maiorData = -1;
				} else if (data2.getDia() > data1.getDia()) {
					maiorData = 1;
				}
			}
		}

		return maiorData;
	}

	public void imprimir() {
		if (Dia != 0) {
			String str_dia = (Dia < 10) ? "0" + Dia : Integer.toString(Dia);
			String str_mes = (Mes < 10) ? "0" + Mes : Integer.toString(Mes);
			System.out.println(str_dia + "/" + str_mes + "/" + Ano);
		} else {
			System.out.println("Data Invalida, não é possivel retornar");
		}
	}
}
