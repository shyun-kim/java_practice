package practice;

public class StudentPractice {
	//Field
	String name[]=new String[10];
	int kor[]=new int[10];
	int eng[]=new int[10];
	int math[]=new int[10];
	int tot[]=new int[10];
	int avg[]=new int[10];
	
	//Cons
	public StudentPractice () {
		
	}
	
	//Method
	public void getTot() {
		int i=0;
		for (i=0; i<3; i++) {
			tot[i]=kor[i]+eng[i]+math[i];
		}
	}
				
	public void getAvg() {
		int i=0;
		for (i=0;i<3;i++) {
			avg[i]=(kor[i]+eng[i]+math[i])/3;
		}
		
	}
}
