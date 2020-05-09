package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {
		List fibonnaciList = new ArrayList();
		fibonnaciList.add(0);
		fibonnaciList.add(1);
		Integer soma = 0;

		while(soma < 350)
		{
			soma = (Integer) fibonnaciList.get(fibonnaciList.size() - 1)
					+ (Integer) fibonnaciList.get(fibonnaciList.size() - 2);

			fibonnaciList.add(soma);
		}
		return fibonnaciList;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}

}