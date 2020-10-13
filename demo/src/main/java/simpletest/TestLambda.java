package simpletest;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.junit.Test;

import collections.Person;

public class TestLambda /* implements Formula */ {

	static int outerStaticNum;
	int outerNum;

	public static void main(String[] args) {
		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

		Collections.sort(names, new Comparator<String>() {

			@Override
			public int compare(String a, String b) {
				return b.compareTo(a);
			}
		});

		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});

		Collections.sort(names, (String a, String b) -> b.compareTo(a));

		Collections.sort(names, (a, b) -> b.compareTo(a));
	}

	@Test
	public void testConverter() {

		Converter<String, Integer> normal = new Converter<String, Integer>() {

			@Override
			public Integer convert(String from) {
				return Integer.valueOf(from);
			}
		};

		Integer converted = normal.convert("123");
		System.out.println(converted);

		// String : F, Integer : T, �����ʵ�� from, ����ֵΪ Integer.valueOf(from)
		Converter<String, Integer> converter = from -> Integer.valueOf(from);
		converted = converter.convert("456");

		// Converter converter = (from) -> Integer.valueOf((String) from);
		// converted = (Integer) converter.convert("456");

		System.out.println(converted);

	}

	@Test
	public void testConvertStaticMethod() {
		Converter<String, Integer> converter = Integer::valueOf;

		Integer converted = converter.convert("123");
		System.out.println(converted);
	}

	@Test
	public void testConvertObjectMethod() {
		Something something = new Something();
		Converter<String, String> converter = something::startsWith;
		String converted = converter.convert("Java");
		System.out.println(converted);

		converter = Something::t;
		converted = converter.convert("World");
		System.out.println(converted);

	}

	@Test
	public void testConvertConstructor() {
		PersonFactory<Person> personFactory = Person::new;
		Person person = personFactory.create("Peter", "Parker");
		System.out.println(person);
	}

	@Test
	public void testFinalLocal() {
		final int num = 1;

		int num2 = 2; // final or effectively final

		Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num + num2);
		String convert = stringConverter.convert(2);
		System.out.println(convert);

		// num2 = 3; // no work
	}

	@Test
	public void testOuter() {
		Converter<Integer, String> convert = (from) -> {
			outerNum = 23;
			outerStaticNum = 72;
			return String.valueOf(from + outerNum);
		};

		String convert2 = convert.convert(3);
		System.out.println(convert2);
	}

	@Test
	public void TestInterface() {
		Formula formula = new Formula() {
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};

		System.out.println(formula.calculate(100)); // 100.0
		System.out.println(formula.sqrt(16)); // 4.0
	}

	@Test
	public void testDefault() {
		// ������಻ʵ�ֽӿ�Formula, ��������ʹ��sqrt����
		// Formula formula = (a) -> sqrt( a * 100);

	}

	@Test
	public void testComparator() {
		Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);

		Person p1 = new Person("John", "Doe");
		Person p2 = new Person("Alice", "Wonderland");

		comparator.compare(p1, p2); // > 0
		comparator.reversed().compare(p1, p2); // < 0
	}

	@Test
	public void testStreams() {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

		stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);

		// Keep in mind that sorted does only create a sorted view of the stream
		// without manipulating the ordering of the backed collection. The
		// ordering of stringCollection is untouched:

		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);

		System.out.println(stringCollection);
		// ddd2, aaa2, bbb1, aaa1, bbb3, ccc, bbb2, ddd1

		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true

		long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

		System.out.println(startsWithB); // 3

		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		// "aaa1#aaa2#bbb1#bbb2#bbb3#ccc#ddd1#ddd2"
	}

	@Test
	public void testDate() {
		Clock clock = Clock.systemDefaultZone();
		long millis = clock.millis();
		System.out.println(millis);

		millis = System.currentTimeMillis();
		System.out.println(millis);

		Instant instant = clock.instant();
		Date legacyDate = Date.from(instant); // legacy java.util.Date
		System.out.println(legacyDate);

		Calendar c = Calendar.getInstance();
		legacyDate = c.getTime();
		System.out.println(legacyDate);
	}

	@Test
	public void testZoneId() {
		System.out.println(ZoneId.getAvailableZoneIds());
		// prints all available timezone ids

		ZoneId zone1 = ZoneId.of("Europe/Berlin");
		ZoneId zone2 = ZoneId.of("Asia/Shanghai");
		System.out.println(zone1.getRules());
		System.out.println(zone2.getRules());

		LocalTime now1 = LocalTime.now(zone1);
		LocalTime now2 = LocalTime.now(zone2);

		System.out.println(now1);
		System.out.println(now2);

		System.out.println(now1.isBefore(now2)); // true

		long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
		long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

		System.out.println(hoursBetween); // 6
		System.out.println(minutesBetween); // 360

		LocalTime late = LocalTime.of(23, 59, 59);
		System.out.println(late); // 23:59:59

	}

	@Test
	public void testLocalDate() {
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
		LocalDate yesterday = tomorrow.minusDays(2);
		System.out.println(yesterday);

		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		DayOfWeek dayOfWeek = independenceDay.getDayOfWeek();
		System.out.println(dayOfWeek); // FRIDAY

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM dd, yyyy - HH:mm");
		LocalDateTime parsed = LocalDateTime.parse("11 03, 2014 - 07:13", dateTimeFormatter);
		String string = dateTimeFormatter.format(parsed);
		System.out.println(string);

		// default format
		DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
				.withLocale(Locale.GERMAN);

		LocalDate xmas = LocalDate.parse("24.12.2014", germanFormatter);
		System.out.println(xmas);

	}

	@Test
	public void testLocalDateTime() {
		LocalDateTime sylvester = LocalDateTime.of(2014, Month.DECEMBER, 31, 23, 59, 59);

		System.out.println(sylvester);

		DayOfWeek dayOfWeek = sylvester.getDayOfWeek();
		System.out.println(dayOfWeek); // WEDNESDAY

		Month month = sylvester.getMonth();
		System.out.println(month); // DECEMBER

		long minuteOfDay = sylvester.getLong(ChronoField.MINUTE_OF_DAY);
		System.out.println(minuteOfDay); // 1439

		LocalTime localTime = sylvester.toLocalTime();
		System.out.println(localTime);

		/// *ZoneId.systemDefault()*/
		Instant instant = sylvester.atZone(ZoneId.of("Europe/Berlin")).toInstant();

		Date legacyDate = Date.from(instant);
		System.out.println(legacyDate); // Wed Dec 31 23:59:59 CET 2014
	}

}

@FunctionalInterface
interface Formula {
	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(a);
	}
}

@FunctionalInterface
interface PersonFactory<P extends Person> {
	P create(String firstName, String lastName);
}


class Something {
	String startsWith(String s) {
		return String.valueOf(s.charAt(0));
	}

	public static String t(String s) {
		return String.valueOf(s.charAt(0));
	}
}

@FunctionalInterface
interface Converter<F, T> {
	T convert(F from);
}