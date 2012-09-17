package com.algorithm.datamining.apriori_eclat;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class TestAlgorithm implements Algorithm{
	
	private Algorithm algorithm;
	
	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
	}

	public void setAlgorithm(String method) throws URISyntaxException{
		String s = method.toLowerCase();
		String s1 = s.substring(0,1).toUpperCase() + s.substring(1);
		try {
			Class c = Class.forName(this.getClass().getPackage().getName() + "." + s1);
			Algorithm al = (Algorithm) c.newInstance();
			this.setAlgorithm(al);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getName() {
		if(algorithm != null) return algorithm.getName();
		return "TestAlgorithm";
	}
	@Override
	public void setUp(List<List<String>> dataList, int minSupport) {
		if(algorithm != null) algorithm.setUp(dataList,minSupport);
		System.out.println(algorithm.getName()+" SetUp:");
		System.out.println("\tTransactions number:" + dataList.size());
		System.out.println("\tMinimal support level:" + getMinSupport());
	}

	@Override
	public void run() {
		if(algorithm != null) algorithm.run();		
	}

	@Override
	public void finish() {
		if(algorithm != null) algorithm.finish();
	}
	
	@Override
	public int getMinSupport() {
		if(algorithm != null) return algorithm.getMinSupport();
		return 0;
	}
	
	@Override
	public HashSet<TreeSet<String>> getFrequencySets() {
		if(algorithm != null) return algorithm.getFrequencySets();
		return null;
	}
	
	public List<List<String>> fetchDataList(String fileName){
		List<List<String>> dataList = null;
		try {
			File f = new File(this.getClass().getResource(fileName).toURI());
			BufferedReader br = new BufferedReader(new FileReader(f));
			dataList = new ArrayList<List<String>>();
			String temp = null;
			while ((temp = br.readLine()) != null) {
				String[] tempArray = temp.split(" ");
				List<String> list = new ArrayList<String>();
				for (String s : tempArray){
					list.add(s);
				}
				dataList.add(list);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}
	
	public static void main(String[] args) throws URISyntaxException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TestAlgorithm test = new TestAlgorithm();
		try {
			System.out.println("Please enter algorithm name, file name and minimal support level, separate by space.");
			String input = br.readLine();
			if (input != null){
				String[] cmd = input.split(" ");
				if(cmd !=null && cmd.length == 3 && Integer.valueOf(cmd[2]) != null){
					String method = cmd[0];
					test.setAlgorithm(method);
					List<List<String>> dataList = test.fetchDataList(cmd[1]);
					
					test.setUp(dataList, Integer.valueOf(cmd[2]));
					test.run();
					test.finish();
					
					System.out.println("The frequency sets are following(size "+test.getFrequencySets().size()+"):");
					for (TreeSet<String> set : test.getFrequencySets()){
						System.out.println("Set:");
						for(String s : set){
							System.out.print(s+" ");
						}
						System.out.println();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
