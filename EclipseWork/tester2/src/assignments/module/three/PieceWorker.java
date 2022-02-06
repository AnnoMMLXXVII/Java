package assignments.module.three;

public class PieceWorker extends Employee {

	private double wage;
	private int pieces;

	/**
	 * @param firstName
	 * @param lastName
	 * @param socialSecurityNumber
	 * @param wage
	 * @param pieces
	 */
	public PieceWorker(String firstName, String lastName, String socialSecurityNumber, double wage, int pieces) {
		super(firstName, lastName, socialSecurityNumber);
		if (wage < 0.0) {
			throw new IllegalArgumentException("Wage salary must be >= 0.0");
		}
		if (pieces < 0) {
			throw new IllegalArgumentException("Number pieces must be >= 0");
		}
		this.wage = wage;
		this.pieces = pieces;
	}

	@Override
	public double earnings() {
		return wage * (double) pieces;
	}

}
