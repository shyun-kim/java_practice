package practice;

import java.util.Scanner;

/*
 * 음식 주문/결제 프로그램(콘솔에서 실행)
 * -음식을 주문(1), 결제(2), 프로그램 종료(9)하는 메뉴로 구성됨
 * 프로그램 종료를 제외 하고 나머지 메뉴는 반복 선택 가능하다
 * 
 * 예외사항 처리: 메뉴선택, 결제금액 입력 시 정수형 타입이 아닌 경우 메세지 출력 후 재입력 유도
 * 입력값이 부족한 경우 재입력을 유도
 * 결제금액이 부족한 경우 재입력을 통해 금액을 누적으로 계산하여 저장 후 결제
 */

public class LunchOrderTestVer4 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		boolean menuFlag = true;
		
		//음식 리스트
		String[] menuList = {"햄버거(🍔)","피자(🍕)","라멘(🍜)","샐러드(🥗)"};
		int[] priceList = {100,200,300,400};
		
		// 주문 리스트 선언 및 생성
		System.out.print("몇가지 음식을 주문하시겠습니까?> ");
		final int MAX_SIZE = input.nextInt();
		String[] orderMenuList = new String[MAX_SIZE];
		int[] orderPriceList = new int[MAX_SIZE];
		int count =0;
		
		// 결제 리스트 선언 및 생성
		// 메뉴명, 결제금액, 입금액, 잔돈
		String paymentMenuList[][] = new String[99][];
		int totalAmountList[] = new int[99];
		int paidAmountList[] = new int[99];
		int changeList[] = new int[99];
		int orderCount = 0;
		int paymentCount = 0;

		while (menuFlag) {
			System.out.println("*******************************************");
			System.out.println("\t Welcome to Food Mart!");
			System.out.println("*******************************************");
			System.out.println("\t 1.음식 주문");
			System.out.println("\t 2.주문 내역");
			System.out.println("\t 3.음식 결제");
			System.out.println("\t 4.결제 내역");
			System.out.println("\t 9.프로그램 종료");
			System.out.println("*******************************************");
			
			System.out.print("메뉴를 선택하십시오> ");
			int selectednum=input.nextInt();
			
			switch (selectednum) {
			case 1:
				boolean orderFlag= true;
				if (count == 0) {
					while(orderFlag) {
						System.out.println("===음식 메뉴===");
						System.out.println("1.햄버거(🍔):100\t 2.피자(🍕):200");
						System.out.println("3.라멘(🍜):300\t 4.샐러드(🥗):400");
						System.out.print("주문 메뉴 선택(숫자)> ");
						if (input.hasNextInt()) {
							int menuNum = input.nextInt();
							if (menuNum>=1 && menuNum <=4) {
								orderMenuList[count] = menuList[menuNum-1]; 
								orderPriceList[count]=priceList[menuNum-1];
								System.out.println(menuList[menuNum-1]+"가 주문 추가되었습니다.");
								count++;
							} else {
								System.out.println("준비중 입니다.");
							}
							
							if (count != orderMenuList.length) {
								while(orderFlag) {
									System.out.print("더 주문하시겠습니까?(y/n)> ");
									String moreOrder = input.next();
									if (moreOrder.equals("n")) {
										orderCount++;
										orderFlag = false;
									} else if (moreOrder.equals("y")) {
										break;
									} else {
										System.out.println("잘못된 접근입니다.");
									}
								}
							} else {
								System.out.println("주문은 메뉴 "+orderMenuList.length+"가지 까지 가능합니다. 처음 화면으로 돌아갑니다.");
								orderCount++;
								orderFlag = false;
							}
						} else {
						System.out.println("올바르지 않은 입력값 입니다. 다시 입력해 주세요");
						input.next();
						}
						
					}
				} else {
					while(orderFlag) {
						System.out.println("이미 선택된 메뉴가 있습니다. 메뉴를 추가하시겠습니까?(y/n)> ");
						String addOrder = input.next();
						if (addOrder.equals("n")) {
							orderFlag = false;
						} else if (addOrder.equals("y")) {
							System.out.println("===음식 메뉴===");
							System.out.println("1.햄버거(🍔):100\t 2.피자(🍕):200");
							System.out.println("3.라멘(🍜):300\t 4.샐러드(🥗):400");
							System.out.print("주문 메뉴 선택(숫자)> ");
							if (input.hasNextInt()) {
								int menuNum = input.nextInt();
								if (menuNum>=1 && menuNum <=4) {
									orderMenuList[count] = menuList[menuNum-1]; 
									orderPriceList[count]=priceList[menuNum-1];
									System.out.println(menuList[menuNum-1]+"가 주문 추가되었습니다.");
									count++;
								} else {
									System.out.println("준비중 입니다.");
								}
								
								if (count != orderMenuList.length) {
									while(orderFlag) {
										System.out.print("더 주문하시겠습니까?(y/n)> ");
										String moreOrder = input.next();
										if (moreOrder.equals("n")) {
											orderFlag = false;
										} else if (moreOrder.equals("y")) {
											break;
										} else {
											System.out.println("잘못된 접근입니다.");
										}
									}
								} else {
									System.out.println("주문은 메뉴 "+orderMenuList.length+"가지 까지 가능합니다. 처음 화면으로 돌아갑니다.");
									orderFlag = false;
								}
							} else {
							System.out.println("올바르지 않은 입력값 입니다. 다시 입력해 주세요");
							input.next();
							}

						} else {
							System.out.println("잘못된 접근입니다.");
						}
					}
					
					
				}
				
				
				paymentMenuList[paymentCount] = new String [count];
				break;
			case 2:
				if (count !=0) {
					System.out.println("주문 리스트 출력");
					System.out.println("번호\t메뉴명\t금액");					
					for (int i =0; i <count; i++) {
						System.out.print((i+1)+".\t ");
						System.out.print(orderMenuList[i]+" \t");
						System.out.print(orderPriceList[i]+" \t\n");
					}
				} else {
					System.out.println("주문 내역이 없습니다.");
				}
				break;
			case 3:
				boolean payFlag = true;
				int totalPrice =0;
				int charge =0;
				int change =0;
				
				if (count != 0) {
					for (int i=0; i<count; i++) {
						 totalPrice += orderPriceList[i];
					}
					while(payFlag) {
						System.out.println("결제금액은 "+totalPrice+"원 입니다.");
						System.out.print("결제할 금액을 입력(숫자)> ");
						while(payFlag) {
							if (input.hasNextInt()) {
								charge += input.nextInt();
								System.out.println("총 입력 금액: "+charge+"원");
								if (charge >= totalPrice) {
									change=charge-totalPrice;
									payFlag=false;
								} else {
									System.out.print("요금이 부족합니다. 다시 입력해 주세요.");
								}
							} else {
							System.out.println("올바르지 않은 입력값 입니다.");
							input.next();
							}
						}
						
						for (int i=0;i<count;i++) {
							paymentMenuList[paymentCount][i]=orderMenuList[i];
						}
						totalAmountList[paymentCount] = totalPrice;
						paidAmountList[paymentCount] = charge;
						changeList[paymentCount] = change;
						
						
						System.out.println("주문메뉴\t\t결제금액\t지불금액\t잔돈");
						System.out.print(orderMenuList[0]+"및"+(count-1)+"개 \t");
						System.out.print(totalPrice+"원 \t");
						System.out.print(charge+"원 \t");
						System.out.println(change+"원 \t");
						
//						for (int i=0;i<count;i++) {
//						orderMenuList[i]=null;
//						orderPriceList[i]=0;
//						}
						
						orderMenuList = new String[MAX_SIZE];
						orderPriceList = new int[MAX_SIZE];
						
						count=0;
						paymentCount++;
						}
				}else {
					System.out.println("주문 내역이 없습니다.");
				}
				break;
			case 4:
				if (paymentCount != orderCount) {
					System.out.println("아직 결제가 완료되지 않은 항목이 있습니다.");
				} else {
					if (paymentMenuList[0]!=null) {
						for(int i=0; i<paymentCount; i++) {
							System.out.println("<"+(i+1)+">");
							System.out.println(">주문메뉴");
							for (int j=0; j<paymentMenuList[i].length; j++) {
								System.out.println(paymentMenuList[i][j]);
							}
							System.out.println(">결제금액: "+totalAmountList[i]+"원");
							System.out.println(">지불금액: "+paidAmountList[i]+"원");
							System.out.println(">잔돈: "+changeList[i]+"원");
						}
					} else {
						System.out.println("결제 내역이 없습니다.");
					}
				}
				
				break;
			case 9:
				System.out.println("프로그램을 종료합니다.");
				paymentCount=0;
				System.exit(0);
			default: 
				System.out.println("메뉴 준비중");
				}
		}
	}
}