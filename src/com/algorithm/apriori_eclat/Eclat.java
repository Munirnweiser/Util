package com.algorithm.apriori_eclat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;



public class Eclat implements Algorithm{

	public static final String NAME = "Eclat";
	private HashSet<TreeSet<String>> frequencySets = new HashSet<TreeSet<String>>();
	private List<TreeSet<String>> levelSets = new ArrayList<TreeSet<String>>();
	private List<List<String>> dataList = new ArrayList<List<String>>();
	private TreeSet<String> item1Set = new TreeSet<String>();
	private Map<String,TreeSet<String>> tidSetsMap = new HashMap<String, TreeSet<String>>();
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

	public void setItem1Set(TreeSet<String> itemSet) {
		this.item1Set = itemSet;
	}

	private void initItem1Set(){
		for (List<String> items : dataList){
			for(String s : items){
				item1Set.add(s);
			}
		}
		for (String s : item1Set){
			TreeSet<String> set = new TreeSet<String>();
			set.add(s);
			if (getTidSets(s).size() >= minSupport)
			levelSets.add(set);
			tidSetsMap.put(s, getTidSets(s));
		}
		frequencySets.addAll(levelSets);
	}
	
	
	private TreeSet<String> getTidSets(String s){
		TreeSet<String> set = new TreeSet<String>();
		for (int i = 0; i < dataList.size(); i++){
			if (dataList.get(i).contains(s)) {
				set.add(""+i+1);
			}
		}
		return set;
	}
	
	private TreeSet<String> getTidSets(TreeSet<String> from){
		TreeSet<String> set = null;
		for (String s : from){
			TreeSet<String> temp = tidSetsMap.get(s);
			if (set == null){
				set = new TreeSet<String>();
				for (String s1 : temp){
					set.add(s1);
				}
			}
			set.retainAll(temp);
		}
		return set;
	}
	
	private int getTidSetsSize(TreeSet<String> from){
		return getTidSets(from).size();
	}
	
	//Enumerate all the subsets of a set, using recursion
	private void getSubsets(List<TreeSet<String>> from){
		for (int i = 0; i < from.size(); i++){
			List<TreeSet<String>> list = new ArrayList<TreeSet<String>>();
			for (int j = from.size()-1; j > i; j--){
				TreeSet<String> set = new TreeSet<String>();
				set.addAll(from.get(i));
				set.addAll(from.get(j));
				if (getTidSetsSize(set) >= minSupport){
					list.add(set);
					frequencySets.add(set);
					getSubsets(list);
				}
			}
		}
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
		getSubsets(levelSets);
	}


	@Override
	public void finish() {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		return NAME;
	}
}
