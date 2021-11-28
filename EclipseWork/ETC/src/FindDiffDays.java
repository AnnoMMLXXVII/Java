import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class FindDiffDays {
	public static void main(String... args) {
		LocalDate startDateInclusive = LocalDate.of(2020, 10, 01);
		LocalDate endDateExclusive = LocalDate.now();
		long diff = ChronoUnit.DAYS.between(startDateInclusive, endDateExclusive);
		Period period = Period.between(startDateInclusive, endDateExclusive);
		System.out.printf("Years: %s\nMonths: %s\nDays: %s\nTotal: %s\n", period.getYears(), period.getMonths(),
				period.getDays(), diff);

	}
}