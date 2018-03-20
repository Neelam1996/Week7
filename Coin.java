package Week_7;

public enum Coin {
	one(1), two(2), five(5), ten(10);

	private int denomination;

	Coin(int denomination) {

		this.denomination = denomination;
	}

	public int getDenomination() {
		return this.denomination;
	}
}
