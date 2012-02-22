package com.algorithm.cluster;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.algorithm.cluster.ClusterData.Cluster;
import com.algorithm.cluster.PointData.Point;

public class ClusterData {
	
	private List<Cluster> clusters = new ArrayList<Cluster>();
	private int increment = 0;
	
	//get Cluster via id
	public Cluster get(int id){
		Cluster Cluster = null;
		for (Cluster p : clusters){
			if (id == p.getId()){
				Cluster = p;
				break;
			}
		}
		return Cluster;
	}
	
	public List<Cluster> getClusters(){
		return clusters;
	}
	
	class Cluster {
		private int id;
		private int centroid;
		private List<Point> points = new ArrayList<PointData.Point>();
		
		public Cluster(){
			this.id = ++increment;
			clusters.add(this);
		}
		public void add(Point p){
			p.setClusterId(this.id);
			points.add(p);
		}
		public void remove(Point p){
			p.setClusterId(0);
			points.remove(p);
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getCentroid() {
			return centroid;
		}
		public List<Point> getPoints() {
			return points;
		}
		public void setPoints(List<Point> points) {
			this.points = points;
		}
		public Point get(int i){
			return points.get(i);
		}
		public int size(){
			return points.size();
		} 
		
		public boolean contains(Point p){
			return points.contains(p);
		}
		
		public void firstSetCentroid(){
			if(points.size() == 1 || points.size() == 2){
				centroid=points.get(0).getId();
				return;
			}
			int id = 0;
			double temp = 0;
			for (Point p : points){
				if(temp == 0)temp = getAvgError(p);
				if(temp > getAvgError(p)){
					temp = getAvgError(p);
					id = p.getId();
				}
			}
			centroid = id;
		}
		
		public boolean updateCentroid(){
			if (points.size()<3){
				return false;
			}
			int id = 0;
			double temp = -1;
			for (Point p : points){
				if(temp == -1){
					temp = getAvgError(p);
					id = p.getId();
				}
				if(temp > getAvgError(p)){
					temp = getAvgError(p);
					id = p.getId();
				}
			}
			if(id == 0 || this.centroid == id )return false;
			centroid = id;
			return true;
		}
		
		//get a point's average error in a cluster
		private double getAvgError(Point p){
			double temp = 0;
			for(Point tp : points){
				temp += p.getError(tp);
			}
			return temp/(points.size()-1);
		}
	}

}
