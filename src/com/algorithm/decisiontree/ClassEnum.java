package com.algorithm.decisiontree;

enum ClassEnum {
	 CLASS1,
	 CLASS2,
	 CLASS3,
	 CLASS4;
	 
	 private int num = 0;

	 public int getNum() {
		 return num;
	 }

	 public void setNum(int num) {
		 this.num = num;
	 }
	 
	 public void reset(){
		 this.num = 0;
	 }
	 
	 public void increment(){
		 this.num++;
	 }
	 
	 public static void resetNum(){
		 for (ClassEnum ce: ClassEnum.values()){
			 ce.reset();
		 }
	 }
}
