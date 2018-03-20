package Week_7;

public enum Item {
	DairyMilk("Dairy Milk", 10), Munch("Munch", 5), MilkyBar("Milky Bar", 15), FiveStar("5 Star", 5);
	private String name;
	private int price;

	private Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	String getName(Item i) {
		return i.name;
	}

	int getPrice(Item i) {
		return i.price;
	}
}
