package com.algorithm.datamining.apriori_eclat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Apriori implements Algorithm{
	public static final String NAME = "Apriori";
	private HashSet<TreeSet<String>> frequencySets = new HashSet<TreeSet<String>>();
	private List<List<String>> dataList = new ArrayList<List<String>>();
	private TreeSet<String> item1Set = new TreeSet<String>();
	private List<List<TreeSet<String>>> candidateSetList = new ArrayList<List<TreeSet<String>>>();
	private List<List<TreeSet<String>>> frequencySetList = new ArrayList<List<TreeSet<String>>>();
	private int minSupport = 0;
	
	public List<List<String>> getDataList() {
		return dataList;
	}

	public void setDataList(List<List<String>> dataList) {
		this.dataList = dataList;
	}

	public HashSet<TreeSet<String>> getFrequencySets(){
		return frequencySets;
	}
	
	public int getMinSupport() {
		return minSupport;
	}

	public void setMinSupport(int minSupport) {
		this.minSupport = minSupport;
	}
		
	public TreeSet<String> getItem1Set() {
		return item1Set;
	}

	public void setItem1Set(TreeSet<String> item1Set) {
		this.item1Set = item1Set;
	}
	
	private void initItem1Set(){
		for (List<String> items : dataList){
			for(String s : items){
				item1Set.add(s);
			}
		}
		List<TreeSet<String>> list = new ArrayList<TreeSet<String>>();
		for (String s1 : item1Set){
			TreeSet<String> set = new TreeSet<String>();
			set.add(s1);
			list.add(set);
		}
		candidateSetList.add(list);
	}
	
	private int count(Set<String> itemSet){
		int i = 0;
		for (List<String> data : dataList){
			if(data.containsAll(itemSet))i++;
		}
		return i;
	}
	
	// i >= 2
	private void canditateSetGen(int i){
		List<TreeSet<String>> preList = candidateSetList.get(i-2);
		Iterator<TreeSet<String>> firstListIt = candidateSetList.get(0).iterator();
		List<TreeSet<String>> nList = new ArrayList<TreeSet<String>>();
		
		while (firstListIt.hasNext()){
			TreeSet<String> set1 = firstListIt.next();
			for (TreeSet<String> set2 : preList){
				TreeSet<String> temp = new TreeSet<String>();
				temp.addAll(set1);
				temp.addAll(set2);
				if(!preList.contains(temp) && !nList.contains(temp))nList.add(temp);
			}
		}
		candidateSetList.add(nList);
	}
	
	// i >= 1
	private void frequencySetGen(int i){
		List<TreeSet<String>> list = candidateSetList.get(i-1);
		Iterator<TreeSet<String>> it = list.iterator();
		while(it.hasNext()){
			TreeSet<String> set = it.next();
			if (count(set) < minSupport) {
				it.remove();
			}
		}
		frequencySetList.add(list);
	}

	@Override
	public void setUp(List<List<String>> dataList, int minSupport) {
			if (minSupport < 0) minSupport = 0;
			if (minSupport > 100) minSupport = 100;
			int temp1 = dataList.size() * minSupport;
			minSupport = temp1/100;
			if (temp1 % 100 != 0)
			minSupport += 1;
			setMinSupport(minSupport);
			setDataList(dataList);
			initItem1Set();
	}

	@Override
	public void run() {
		frequencySetGen(1);
		int i = 1;
		do {
			i++;
			canditateSetGen(i);
			frequencySetGen(i);
		} while (!frequencySetList.get(i-1).isEmpty());
	}

	@Override
	public void finish() {
		for (List<TreeSet<String>> list: frequencySetList) {
			frequencySets.addAll(list);
		}
	}

	@Override
	public String getName() {
		return NAME;
	}
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list2.add("1");
		System.out.println(list.retainAll(list2));
		System.out.println(list.size());
		
	}
	
}
