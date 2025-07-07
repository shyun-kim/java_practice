package practice;

import java.util.Scanner;

public class Laptime {
	//Field
	int driverNum;
	int count =0;
	LaptimeIngredient dlist[];
	Scanner input = new Scanner(System.in);
	boolean repeatCheck =true;

	
	//Constructor
	public Laptime (){
		
	}
	
	//Method
	public void main() {
		System.out.println("==랩타임 입력/체크 프로그램==");;
		System.out.println("1. 랩타임 입력 \t2.랩타임 체크");
		System.out.println("메뉴를 입력해 주십시오");
		menuCheck(input.nextInt());
	}
	
	public void menuCheck(int menuSel) {
		if(menuSel == 1) {
			driverCount();
			driverName();
			lapRecord();
			main();
		} else {
			lapCheck();
			
			while (repeatCheck) {
				System.out.println("계속 체크하시겠습니까?(y/n)> ");
				String repeat = input.next();
				if (repeat.equals("y")) {
					lapCheck();
				} else if(repeat.equals("n")){
					System.out.println("메인 메뉴로 돌아갑니다.");
					main();
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			}
		}
	}
	
	public void driverCount() {
		
		System.out.print("입력하실 드라이버는 몇명입니까?> ");
		driverNum=input.nextInt();
		System.out.println(driverNum+"명이 입력되었습니다.");
		dlist = new LaptimeIngredient[driverNum];
	}
	
	public void driverName() {
		for (int i=0;i<driverNum;i++) {
			dlist[i] = new LaptimeIngredient();
			System.out.print("드라이버의 이름을 입력해 주십시오.> ");
			String driverName = input.next();
			dlist[i].driver = driverName;
			System.out.println((i+1)+"명 입력했습니다.");
		}
	}
	
	public void lapRecord() {
		for (int i=0;i<driverNum;i++) {
			System.out.print(dlist[i].driver+" 드라이버의 랩타입을 입력합니다.\n총 몇 랩을 주행하였습니까?");
			int lapCount = input.nextInt();
			dlist[i].laptime = new String[lapCount];
			for (int j=0; j<lapCount; j++) {
				System.out.println((j+1)+"번째 랩 입니다. 랩타임을 다음과 같은 형식으로 입력해 주십시오.(mm.ss,SSS)");
				System.out.println("예시: 1분 23.456초 > 01.23,456");
				String laptime = input.next();
				dlist[i].laptime[j]=laptime;
			}
		}	
	}
	
	public void lapCheck() {
		System.out.println("랩타임을 체크할 드라이버의 이름을 입력하십시오.");
		String driverName = input.next();
		for (int i=0; i<driverNum; i++) {
			if(driverName.equals(dlist[i].driver)){
				System.out.println(dlist[i].driver+"의 랩타임을 확인 합니다.");
				for (int j = 0; j<dlist[i].laptime.length; j++) {
					System.out.println((j+1)+"번째 랩타임> "+dlist[i].laptime[j]);
				}
			}
		}
		
		//
		
	}
}
