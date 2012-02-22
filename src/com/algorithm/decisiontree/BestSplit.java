package com.algorithm.decisiontree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class BestSplit {
	private static List<EntityData> dataSet;
	private static String measureCriteria;
	private static final String GINI = "GINI";
	private static final String ENTROPY = "ENTROPY";
	
	public static String getMeasureCriteria() {
		return measureCriteria;
	}
	public static void setMeasureCriteria(String measureCriteria) {
		BestSplit.measureCriteria = measureCriteria;
	}

	static{
		dataSet =  new ArrayList<EntityData>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(BestSplit.class.getResource("testData.txt").toURI())));
			String s;
			while ((s = br.readLine()) != null){
				String[] temp = s.split(",");
				if (temp.length == 2){
					try {
						EntityData ed = new EntityData(Double.parseDouble(temp[0]), ClassEnum.valueOf(temp[1].toUpperCase()));
						dataSet.add(ed);
					} catch (Exception e){
						continue;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static double log2(double digit){
		if (digit == 0d) return 0d;
		return Math.log(digit)/Math.log(2);
	}
	public static double square(double digit){
		return Math.pow(digit, 2);
	}

	public static void sortByContinuousAttribute(){
		Collections.sort(dataSet, new Comparator<EntityData>() {
			@Override
			public int compare(EntityData o1, EntityData o2) {
				return (int)(o1.getContinuousAttribute() - o2.getContinuousAttribute());
			}
		});
		System.out.println("Sorted dataSet:");
		for (EntityData ed : dataSet){
			System.out.println(ed.getContinuousAttribute() + "," + ed.getClassAttribute());
		}
	}
	
	public static void storeNumInEnum(List<EntityData> list){
		ClassEnum.resetNum();
		for (EntityData ed : list){
			ed.getClassAttribute().increment();
		}
	} 
	
	public static double computeGini(List<EntityData> list){
		if (list.size() == 0) return 1d;
		storeNumInEnum(list);
		double temp = 0d;
		for (ClassEnum ce: ClassEnum.values()){
			double p = (double)ce.getNum()/(double)list.size();
			temp += square(p);
		 }
		return 1-temp;
	}
	
	public static double computeEntropy(List<EntityData> list){
		if (list.size() == 0) return 1d;
		storeNumInEnum(list);
		double temp = 0d;
		for (ClassEnum ce: ClassEnum.values()){
			double p = (double)ce.getNum()/(double)list.size();
			temp += p*log2(p);
		 }
		return -temp;
	}
	
	public static double compute(int splitPosition){
		List<EntityData> subList1 = dataSet.subList(0, splitPosition);
		List<EntityData> subList2 = dataSet.subList(splitPosition, dataSet.size());
		// if gini, compute the gini of split
		// if entropy, compute the gain of split
		if (GINI.equals(getMeasureCriteria())){
			double gini1 = ((double)subList1.size()/(double)dataSet.size())*computeGini(subList1);
			double gini2 = ((double)subList2.size()/(double)dataSet.size())*computeGini(subList2);
			return  gini1 + gini2;
		} else if (ENTROPY.equals(getMeasureCriteria())){
			double parentEntropy = computeEntropy(dataSet);
			double entr1 = ((double)subList1.size()/(double)dataSet.size())*computeEntropy(subList1);
			double entr2 = ((double)subList2.size()/(double)dataSet.size())*computeEntropy(subList2);
			return  parentEntropy - (entr1 + entr2);
		}
		return -1d;
	}
	
	public static int chooseBestSplit(){
		int bestIndex = 0;
		double temp = -1d;
		for (int i = 0; i < dataSet.size(); i++){
			if (temp == -1d) temp = compute(i);
			System.out.println(getMeasureCriteria() + " compute " + i + ":" + compute(i));
			if (GINI.equals(getMeasureCriteria())){
				// if gini, choose by the minimizes gini
				if (temp > compute(i)){
					temp = compute(i);
					bestIndex = i;
				}
			} else {
				// if entropy, choose by the maximizes gain
				if (temp < compute(i)){
					temp = compute(i);
					bestIndex = i;
				}
			}
		}
		return bestIndex;
	}
	
	public static void main(String[] args) {
		sortByContinuousAttribute();
		setMeasureCriteria(GINI);
//		setMeasureMethod(ENTROPY);
		int bestIndex = chooseBestSplit();
		System.out.println();
		System.out.println("Best compute index:" + bestIndex);
		System.out.println("Best split at continuous attribute:" + dataSet.get(bestIndex).getContinuousAttribute());
	}
}
