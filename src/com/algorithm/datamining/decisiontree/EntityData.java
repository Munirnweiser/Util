package com.algorithm.datamining.decisiontree;

public class EntityData {
	private ClassEnum classAttribute;
	private double continuousAttribute;
	
	public EntityData(double continuousAttribute, ClassEnum classAttribute) {
		this.classAttribute = classAttribute;
		this.continuousAttribute = continuousAttribute;
	}
	
	public EntityData() {
	}

	public ClassEnum getClassAttribute() {
		return classAttribute;
	}
	public void setClassAttribute(ClassEnum classAttribute) {
		this.classAttribute = classAttribute;
	}
	public double getContinuousAttribute() {
		return continuousAttribute;
	}
	public void setContinuousAttribute(double continuousAttribute) {
		this.continuousAttribute = continuousAttribute;
	}
}
