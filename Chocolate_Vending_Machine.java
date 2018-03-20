package Week_7;

import java.util.*;

public class Chocolate_Vending_Machine implements Cash_Inventory, Item_Inventory {
	private HashMap<Coin, Integer> cash = new HashMap<>();
	HashMap<Item, Integer> items = new HashMap<>();

	public void initialize_CashInventory() {
	
		for (Coin c : Coin.values()) {
			cash.put(c, 5);
		}
	}

	public void initialize_CashInventory(int coins[]) {
		int i = 0;
		for (Coin c : Coin.values()) {
			cash.put(c, coins[i]);
			i++;

		}
	}

	public void intialize_Item_Inventory() {

		for (Item it : Item.values()) {
			items.put(it, 5);

		}
	}

	public void intialize_Item_Inventory(int item_values[]) {
		int i = 0;
		for (Item it : Item.values()) {
			items.put(it, item_values[i]);
			i++;

		}
	}

	public void addCoin(Coin c, int no) {
		int value = cash.get(c);
		cash.put(c, value + no);
		// totalCash+=(c.getDenomination()*no);
	}

	public void addItem(Item i, int no) {
		int val = items.get(i);
		items.put(i, val + no);
	}

	public void removeCoin(Coin c, int no) {
		int value = cash.get(c);
		if (value >= no) {
			cash.put(c, value - no);
		} else {
			cash.put(c, 0);
		}
	}

	public void removeItem(Item i, int no) throws OutOfStockException {
		int val = items.get(i);
		if (val >= no) {
			items.put(i, val - no);
		} else {
			throw new OutOfStockException("Sorry!! We don't have enough chocolates to meet your demand");
		}
	}

	Chocolate_Vending_Machine() {
		initialize_CashInventory();
		intialize_Item_Inventory();
	}

	public void displayMenu() {
		System.out.println("Welcome");
		System.out.println("MENU:");
		System.out.println("1. Dairy Milk				Rs. 10/-");
		System.out.println("2. Munch				Rs. 5/-");
		System.out.println("3. Milky Bar				Rs. 15/-");
		System.out.println("4. 5 Star				Rs. 5/-");
		System.out.println(
				"Please press the corresponding no. from the menu on the keypad and then quantity to get your chocolate ");

	}

	public boolean hasSufficientItem(int s_no, int qty) {
		Item items_in[] = Item.values();
		int avail_qty = items.get(items_in[s_no - 1]);
		if (avail_qty < qty||qty<0) {
			return false;
		}
		return true;
	}

	public boolean sufficientMoneyGiven(ArrayList<Integer> money, int s_no, int qty) throws NoSufficientMoneyEnteredByCustomer {
		int tot_Money = 0;
		for (int i = 0; i < money.size() - 1; i++) {
			tot_Money += money.get(i);
		}
		Item items_in[] = Item.values();
		int actualPrice = items_in[s_no - 1].getPrice(items_in[s_no - 1]) * qty;
		if (tot_Money < actualPrice) {
			throw new NoSufficientMoneyEnteredByCustomer("You have not entered sufficient money to place order!! Please start again");
		}
		return true;
	}

	public ArrayList<Coin> returnChange(ArrayList<Integer> money, int s_no, int qty)
			throws NoSufficientChangeException {
		ArrayList<Coin> changes = new ArrayList<>();
		int coindeduct[] = new int[4];
		int tot_Money = 0;
		for (int i = 0; i < money.size() - 1; i++) {
			tot_Money += money.get(i);
			switch (money.get(i)) {
			case 10:
				addCoin(Coin.ten, 1);
				break;
			case 5:
				addCoin(Coin.five, 1);
				break;
			case 2:
				addCoin(Coin.two, 1);
				break;
			case 1:
				addCoin(Coin.one, 1);
				break;
			}
		}
		Item items_in[] = Item.values();
		int actualPrice = items_in[s_no - 1].getPrice(items_in[s_no - 1]) * qty;
		int diffMoney = tot_Money - actualPrice;

		if (diffMoney > 0) {
			while (diffMoney > 0) {
				if (diffMoney >= Coin.ten.getDenomination() && cash.get(Coin.ten) >= coindeduct[0]) {
					diffMoney -= Coin.ten.getDenomination();
					coindeduct[0]++;
					changes.add(Coin.ten);
				} else if (diffMoney >= Coin.five.getDenomination() && cash.get(Coin.five) >= coindeduct[1]) {
					diffMoney -= Coin.five.getDenomination();
					coindeduct[1]++;
					changes.add(Coin.five);
				} else if (diffMoney >= Coin.two.getDenomination() && cash.get(Coin.two) >= coindeduct[2]) {
					diffMoney -= Coin.two.getDenomination();
					coindeduct[2]++;
					changes.add(Coin.two);
				} else if (diffMoney >= Coin.one.getDenomination() && cash.get(Coin.two) >= coindeduct[3]) {
					diffMoney -= Coin.one.getDenomination();
					coindeduct[3]++;
					changes.add(Coin.one);
				} else {
					throw new NoSufficientChangeException("Machine does not have sufficient change");
				}
			}
		}
		return changes;
	}

	private void updateCashInventory(ArrayList<Coin> changes) {
		for (Coin c : changes) {
			removeCoin(c, 1);
		}
	}

	private void updateItemInventory(int s_no, int qty) {
		Item items_in[] = Item.values();
		removeItem(items_in[s_no - 1], qty);

	}

	public void collectMoney_And_UpdateInventories(ArrayList<Coin> changes, int s_no, int qty) {
		Item items_in[] = Item.values();
		System.out.println("Collect your money!! ");
		if (changes.size() > 0) {
			for (Coin c : changes) {
				System.out.print(c.getDenomination() + " ");
			}
			System.out.println();
		} else {
			System.out.println("No return change left");
		}
		System.out.println("Your order ");
		System.out.println("Item                      Quantity");
		System.out.println(items_in[s_no - 1].getName(items_in[s_no - 1]) + "                	" + qty);
		System.out.println();
		System.out.println("Please Visit Again");
		updateCashInventory(changes);
		updateItemInventory(s_no, qty);
	}

	public void printInventoryStatus() {
		Item items_in[] = Item.values();
		for (Item i : items_in) {
			System.out.println(i.getName(i) + "     " + items.get(i));
		}
	}

	public static void main(String[] args) {
		try {
			Scanner s = new Scanner(System.in);
			Chocolate_Vending_Machine vm = new Chocolate_Vending_Machine();
			vm.displayMenu();

			int s_no = s.nextInt();
			if (!(s_no >= 1 && s_no <= 4)) {
				throw new InvalidItemNoEntered("Wrong serial no of the item entered");
			}
			int qty = s.nextInt();
			if (!vm.hasSufficientItem(s_no, qty)) {
				throw new OutOfStockException("Sorry!! We don't have enough chocolates of your demand");
			}

			ArrayList<Integer> money = new ArrayList<>();
			System.out.println("Start entering money and enter -1 to halt");
			int coin = 0;
			do {
				coin = s.nextInt();
				if(!(coin!=-1||coin!=1||coin!=2||coin!=5||coin!=10) ){
					throw new NotAcceptableDenominationEntered("This last inserted coin's value is not acceptable");
				}
				money.add(coin);
			} while (coin != -1);
			if (vm.sufficientMoneyGiven(money, s_no, qty)) {
				ArrayList<Coin> changedMoney = vm.returnChange(money, s_no, qty);
				vm.collectMoney_And_UpdateInventories(changedMoney, s_no, qty);
				vm.printInventoryStatus();
			}

		} catch (Exception e) {
			System.out.println("Exception Caught");
			System.out.println(e);

		}

	}

}
