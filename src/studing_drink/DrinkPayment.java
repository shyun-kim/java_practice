package studing_drink;

import java.util.Scanner;

public class DrinkPayment {
	//Field
	Scanner input = new Scanner(System.in);
	DrinkOrderSystem dOS = new DrinkOrderSystem();
	int charge;
	int change;
	int total;
	
	//Constructor
	
	//Method
	public void payCheck() {
			int total = totalPayment();
			System.out.println("결제 예정 금액> "+total);
			System.out.print("결제할 요금 입력(숫자)> ");
			if (input.hasNextInt()) {
				charge += input.nextInt();
				System.out.println("총 입력 금액: "+charge);
				if (charge >= total) {
					change=charge-total;
					
					
					System.out.println("결제 완료");
					System.exit(0);
					
				} else {
					System.out.println("요금이 부족합니다. 다시 입력해 주세요.");
					payCheck();
				}
			} else {
				System.out.println("올바르지 않은 입력값 입니다.");
				input.next();
			}
		}
	
	public int totalPayment() {
		int total=0;
		for (int i=0;i<dOS.orderCount; i++) {
			dOS.dorder[i].amount += total;
		}
		return total;
	}
	
	public void paymentList() {
		System.out.println("============================");
		System.out.println("\t 결제 리스트");
		System.out.println("============================");
		System.out.println("주문메뉴\t결제금액\t입금액\t잔돈");
		System.out.println("============================");
		
		for(int i =0; i<dOS.orderCount; i++)
		System.out.print(dOS.dorder[i].menuName+"등 \t");
		System.out.print(total+"\t");
		System.out.print(charge+"\t");
		System.out.print(change+"\t");
	}
	
}
