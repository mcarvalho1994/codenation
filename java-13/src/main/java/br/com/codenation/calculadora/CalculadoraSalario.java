package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
		//Use o Math.round apenas no final do método para arredondar o valor final.
		//Documentação do método: https://docs.oracle.com/javase/8/docs/api/java/lang/Math.html#round-double-
		double inss, ir, salarioLiquido;
		if(salarioBase < 1039.00 )
			return Math.round(0.0);
		else {
			inss = calcularInss(salarioBase);
			ir = calcularIRRF(salarioBase - inss);
			salarioLiquido = salarioBase - inss - ir;
			return Math.round(salarioLiquido);
		}
	}
	
	
	//Exemplo de método que pode ser criado para separar melhor as responsábilidades de seu algorítmo
	private double calcularInss(double salarioBase) {
		if(salarioBase <= 1500.00) {
			//Até R$ 1.500,00	8%
			return salarioBase * 0.08;
		}
		else if(salarioBase <= 4000) {
			//De R$ 1.500,01 até R$ 4.000,00	9%
			return salarioBase * 0.09;
		}
		else {
			//Acima de R$ 4.000,00	11%
			return salarioBase * 0.11;
		}
	}

	private double calcularIRRF(double salarioBaseMenosInss) {
		if(salarioBaseMenosInss > 6000) {
			//Acima de R$ 6.000,00	15%
			return salarioBaseMenosInss * 0.15;
		}
		else if(salarioBaseMenosInss > 3000) {
			//De R$ 3.000,01 até R$ 6.000,00	7.5%
			return salarioBaseMenosInss * 0.075;
		}
		else
			//Até R$ 3.000,00	ISENTO
			return 0.0;
	}

}
/*Dúvidas ou Problemas?
Manda e-mail para o meajuda@codenation.dev que iremos te ajudar! 
*/