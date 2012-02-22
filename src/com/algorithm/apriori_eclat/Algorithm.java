package com.algorithm.apriori_eclat;


import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public interface Algorithm {
	public String getName();
	public void setUp(List<List<String>> dataList, int minSupport);
	public void run();
	public void finish();
	public int getMinSupport();
	public HashSet<TreeSet<String>> getFrequencySets();
}
