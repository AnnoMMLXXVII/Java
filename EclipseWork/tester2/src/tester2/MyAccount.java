package tester2;

public class MyAccount {

	public static void main(String[] args) {
		System.out.println("Welcome to my account");
		Account dis = Account.PREMIUM;
		System.out.println(dis.getDiscount() + "%");
	}

	enum Account {
		PREMIUM(10), GOLD(7), BASIC(3);

		private int discount;

		Account(int discounted) {
			this.discount = discounted;
		}

		public int getDiscount() {
			return discount;
		}
	}

}
