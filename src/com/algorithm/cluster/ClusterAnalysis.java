package com.algorithm.cluster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

import com.algorithm.cluster.ClusterData.Cluster;
import com.algorithm.cluster.PointData.Point;

public class ClusterAnalysis {
	private ClusterData clusters = new ClusterData();
	private PointData pd = new PointData();
	private int dimension;
	private int iteration;

	private void initCluster(){
		// initial clusters are specified
		if (pd.getPoints().size() >= 8) {
			Cluster c1 = clusters.new Cluster();
			c1.add(pd.get(1));
			c1.add(pd.get(2));
			c1.add(pd.get(3));
			Cluster c2 = clusters.new Cluster();
			c2.add(pd.get(4));
			c2.add(pd.get(5));
			c2.add(pd.get(6));
			Cluster c3 = clusters.new Cluster();
			c3.add(pd.get(7));
			c3.add(pd.get(8));

			for (Cluster c : clusters.getClusters()){
				c.firstSetCentroid();
			}
		}
	}
	private void prepareDataFromFile(FileReader f, int dimension) {
		this.dimension = dimension;
		BufferedReader br = new BufferedReader(f);
		String temp = null;
		try {
			while ((temp = br.readLine()) != null) {
				String[] s = temp.split(" ");
				if (s != null && s.length >= dimension) {
					int[] in = new int[dimension];
					for (int i = 0; i < dimension; i++) {
						in[i] = Integer.parseInt(s[i]);
					}
					pd.new Point(in);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		initCluster();
	}

	private void k_means() {
		preOutput();
		boolean isDone = false;
		// repeat util all the centroids can not be updated
		while (!isDone) {
			boolean sign = true;
			for (Point p : pd.getPoints()) {
				int tmpClosestClusterId = 0;
				double temp = -1;
				int tmpOfClusterId = 0;
				for (Cluster c : clusters.getClusters()){
						double error = p.getError(pd.get(c.getCentroid()));
						if (temp == -1) temp = error;
						if (tmpClosestClusterId == 0) tmpClosestClusterId = c.getId();
						// find the needed cluster
						if (error >= 0 && compare(temp, error)) {
							temp = error;
							tmpClosestClusterId = c.getId();
						}
						if(c.contains(p))tmpOfClusterId = c.getId();
				}
				
				// update controid id
				if (tmpClosestClusterId != 0 && !clusters.get(tmpClosestClusterId).contains(p)) {
					clusters.get(tmpClosestClusterId).add(p);
					sign = sign && !clusters.get(tmpClosestClusterId).updateCentroid();
					
					clusters.get(tmpOfClusterId).remove(p);
					sign = sign && !clusters.get(tmpOfClusterId).updateCentroid();
				}
			}
			isDone = sign;
			iteration++;
			resultOutput();
		}
		System.out.println("\nK-means End.");
	}
	
	public boolean compare(double d1, double d2){
		return d1 > d2;
	}

	private void preOutput() {
		System.out.println("K-means Start:");
		System.out.println("D:" + dimension);
		System.out.println("K:" + clusters.getClusters().size());
		System.out.println("n:" + pd.getPoints().size());
		String s = "";
		for (Cluster c : clusters.getClusters()) {
			s += "{";
			for (Point p : c.getPoints()) {
				s += p.getId() + ",";
			}
			s = s.substring(0, s.length() - 1);
			s += "},";
		}
		s = s.substring(0, s.length() - 1);
		System.out.println("Initial clusters:" + s);
	}

	private void resultOutput() {
		System.out.println("\nIteration:" + iteration);
		String s = "";
		for (Cluster c : clusters.getClusters()) {
			s += "{";
			for (Point p : c.getPoints()) {
				s += p.getId() + ",";
			}
			if(s.charAt(s.length()-1) == ',')s = s.substring(0, s.length() - 1);
			s += "},";
		}
		s = s.substring(0, s.length() - 1);
		System.out.println("Now the clusters:" + s);
	}

	public static void main(String[] args) {
		ClusterAnalysis cas = new ClusterAnalysis();
		String fileName = null;
		int dimension = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter data source file name, dimension.");
		try {
			String input = br.readLine();
			String[] in = input.split(" ");
			if (in != null && in.length == 2) {
				fileName = in[0];
				dimension = Integer.parseInt(in[1]);
			} else {
				System.out.println("Incorrect input.");
				return;
			}
			File f = new File(cas.getClass().getResource(fileName).toURI());
			cas.prepareDataFromFile(new FileReader(f), dimension);
			cas.k_means();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
