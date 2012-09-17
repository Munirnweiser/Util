package com.algorithm.datamining.cluster;

import java.util.ArrayList;
import java.util.List;

public class PointData {
	private List<Point> points = new ArrayList<Point>();
	private int increment = 0;
	
	//get Point via id
	public Point get(int id){
		Point point = null;
		for (Point p : points){
			if (id == p.getId()){
				point = p;
				break;
			}
		}
		return point;
	}
	
	public List<Point> getPoints(){
		return points;
	}
	
	class Point{
		private int id;
		private int clusterId;
		private int[] position;
		
		public Point(int[] in) {
			this.id = ++increment;
			this.position = in;
			points.add(this);
		}
		public int getClusterId() {
			return clusterId;
		}
		public void setClusterId(int clusterId) {
			this.clusterId = clusterId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int[] getPosition() {
			return position;
		}
		public void setPosition(int[] position) {
			this.position = position;
		}
		//get error between 2 points
		public double getError(Point p){
			if(p == null)return 0;
			int[] po = p.getPosition();
			double temp = 0;
			for(int i = 0; i < position.length; i++){
				temp += StrictMath.pow(position[i]-po[i], 2);
			}
			return 	StrictMath.sqrt(temp);
		}
	}
}
