package studing_drink;

import java.util.Scanner;

public class DrinkOrderSystem {
	Scanner scan = new Scanner(System.in);
	String drinkMenuName[] = {"☕ 아메리카노","🍵 바닐라 라떼","🥤 딸기 쉐이크"};
	int drinkMenuPrice[] = {2800,3500,4000};
	int orderCount=0;
	DrinkMenu dmenu[] = new DrinkMenu[3];
	DrinkOrder dorder[] = new DrinkOrder[3];
	DrinkPayment dPay = new DrinkPayment();
		
	
	public void createMenu() {
		for (int i=0;i<dmenu.length;i++) {
			dmenu[i].no=i+1;
			dmenu[i].menuName=drinkMenuName[i];
			dmenu[i].price=drinkMenuPrice[i];
		}
	}
	
	public void mainMenu() {
		System.out.println("=== 메뉴판 ===");
		System.out.println("1.☕ 아메리카노 - 2,800원");
		System.out.println("2.🍵 바닐라 라떼 - 3,500원");
		System.out.println("3.🥤 딸기 쉐이크 - 4,000원");
		createMenu();
		orderMenu();
	}
	
	public void orderMenu() {
		System.out.print("주문할 메뉴 번호 입력> ");
		if(scan.hasNextInt()) {
			int menuSel=scan.nextInt();
			orderCheck(menuSel);
		}else {
			System.out.println("잘못된 입력입니다.");
			scan.next();
			orderMenu();
		}
	}
	
	
	public void orderCheck(int menuSel) {
		if(menuSel >= 1&&menuSel<=4) {
			order(menuSel);
		}else {
			System.out.println("메뉴 준비중입니다.");
			orderMenu();
		}
	}
	
	public int searchOrderItemIdx(int menuSel) {
		int idx = -1;
		for(int i=0; i<orderCount; i++) {
			DrinkOrder order = dorder[i];
			if (order.menuName.equals(dmenu[menuSel].menuName)) {
				idx = i;
			}
		}
		return idx;
	}
	
	public void order(int menuSel) {
		for(int i =0; i<dorder.length; i++) {
			int idx = searchOrderItemIdx(menuSel);
			if(idx == -1) {
				dorder[i].menuNo = dmenu[menuSel].no;
				dorder[i].menuName = dmenu[menuSel].menuName;
				dorder[i].amount += dmenu[menuSel].price;
				dorder[i].qty +=1;
				orderCount++;
			} else {
				dorder[idx].qty++;
			}
			break;
		}
		System.out.println("==주문 완료==");
		repeat();
	}
	
	
	public void repeat() {
		String reOrder;
		System.out.print("계속 주문하시겠습니까?(y/n)> ");
		reOrder=scan.next();
		if(reOrder.equals("y")) {
			mainMenu();
		} else if (reOrder.equals("n")) {
			payment();
		} else {
			System.out.println("잘못된 입력입니다.");
			repeat();
		}
	}
	
	public void payment() {
		dPay.payCheck();
	}
	

}
