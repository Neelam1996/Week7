package Week_7;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Test_ChocolateVendingMachine {

	@Test
	void test_validMoney_InvalidInput() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
		ArrayList<Integer> money=new ArrayList<>();
		try {
			assertEquals("You have not entered sufficient money to place order!! Please start again",vm.sufficientMoneyGiven(money, 3, 1));
		}
		catch(Exception e) {
			
		}
	}
	void test_validMoney_InvalidInput2() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
		ArrayList<Integer> money=new ArrayList<>();
		money.add(-1);
		try {
			assertEquals("You have not entered sufficient money to place order!! Please start again",vm.sufficientMoneyGiven(money, 3, 1));
		}
		catch(Exception e) {
			
		}
	}
	void test_validMoney_validInput() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
		ArrayList<Integer> money=new ArrayList<>();
		money.add(5);
		money.add(10);
		money.add(-1);
		try {
			assertEquals(true,vm.sufficientMoneyGiven(money, 4, 2));
		}
		catch(Exception e) {
			
		}
	}
	void test_returnChangeInvalidInput1() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
		ArrayList<Integer> money=new ArrayList<>();
		money.add(5);
		money.add(-1);
		try {
			assertEquals("You have not entered sufficient money to place order!! Please start again",vm.returnChange(money, 4, 2));
		}
		catch(Exception e) {
			
		}
	}
	void test_returnChangeInvalidInput2() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
		ArrayList<Integer> money=new ArrayList<>();
		money.add(6);
		money.add(-1);
		try {
			assertEquals("This last inserted coin's value is not acceptable",vm.returnChange(money, 2, 1));
		}
		catch(Exception e) {
			
		}
	}
	void test_returnChangeValidInput1() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
		ArrayList<Integer> money=new ArrayList<>();
		money.add(5);
		money.add(2);
		money.add(2);
		money.add(-1);
		ArrayList<Integer> res=new ArrayList<>();
		res.add(2);
		res.add(2);
		try {
			assertEquals(res,vm.returnChange(money, 4, 1));
		}
		catch(Exception e) {
			
		}
	}
	
	void test_hasSufficientItemInvalidInput1() {
		Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();

		
			assertEquals(false,vm.hasSufficientItem( 4, -1));
		
		
	}

}
