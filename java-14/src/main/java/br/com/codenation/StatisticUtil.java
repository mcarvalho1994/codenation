package br.com.codenation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StatisticUtil {

	public static int average(int[] elements) {
		int sum = 0;
		for(int i = 0; i < elements.length; i++) {
			sum += elements[i];
		}

		return sum / elements.length;
	}

	public static int mode(int[] elements) {
		Arrays.sort(elements);
		List<ModaQtdeUtil> modasList = new ArrayList<>();
		for(int i = 0; i < elements.length; i++) {
			if(i != elements.length - 1 && elements[i] == elements[i + 1]) {
				if(modasList.size() == 0) {
					ModaQtdeUtil mq = new ModaQtdeUtil(elements[i],1);
					modasList.add(mq);
				} else if(elements[i] == modasList.get(modasList.size() - 1).getModa()) {
					modasList.get(modasList.size() - 1).setQtde(modasList.get(modasList.size() - 1).getQtde() + 1);
				} else {
					ModaQtdeUtil mq = new ModaQtdeUtil(elements[i],1);
					modasList.add(mq);
				}
			} else if(modasList.size() > 0 && elements[i] == modasList.get(modasList.size() - 1).getModa()) {
				modasList.get(modasList.size() - 1).setQtde(modasList.get(modasList.size() - 1).getQtde() + 1);
			}
		}

		for(int i = 0; i < modasList.size();i++) {
			for(int j = 1; j < modasList.size(); j++) {
				if(modasList.get(i).getQtde() < modasList.get(j).getQtde()) {
					ModaQtdeUtil modaTemp = modasList.get(i);
					modasList.set(i, modasList.get(j));
					modasList.set(j,modaTemp);
				}
			}
		}

		if(modasList.size() > 1 && modasList.get(0).getQtde() == modasList.get(1).getQtde())
			return 0;
		else
			return modasList.get(0).getModa();
	}

	public static int median(int[] elements) {
		Arrays.sort(elements, 0, elements.length);
		if(elements.length % 2 == 1)
		{
			return elements[elements.length/2];
		}
		else {
			return (elements[elements.length/2] + elements[elements.length/2 - 1])/2;
		}
	}
}