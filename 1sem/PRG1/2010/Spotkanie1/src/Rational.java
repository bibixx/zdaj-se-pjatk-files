public class Rational {

	int licznik;
	int mianownik;

	public Rational(int licznik, int mianownik) {

		if (licznik < 0 && mianownik < 0) {
			licznik = -licznik;
			mianownik = -mianownik;
		} else if (mianownik < 0 && licznik > 0) {
			licznik = -licznik;
			mianownik = -mianownik;
		} else if (mianownik == 0) {
			throw new IllegalArgumentException();
		} else if (licznik == 0) {
			mianownik = 1;
			licznik = 0;
		}

		this.licznik = licznik;
		this.mianownik = mianownik;
		reduce();

	}

	public String toString() {

		if (licznik == 0) {
			return "0";
		} else if (licznik == mianownik) {
			return "1";
		} else if (licznik == -mianownik) {
			return "-1";
		} else if (mianownik < licznik) {
			int reszta = licznik % mianownik;
			if (reszta != 0) {
				return (licznik / mianownik) + "[" + (reszta) + "/" + mianownik
						+ "]";
			} else
				return (licznik / mianownik) + "";
		}

		return "[" + licznik + "/" + mianownik + "]";
	}

	private void reduce() {
		int d = getNWD(licznik, mianownik);
		if (d != 0) {
			licznik = licznik / d;
			mianownik = mianownik / d;
		}
	}

	private int getNWD(int a, int b) {
		if (a != 0 && b != 0) {
			if (a < 0)
				a = -a;
			if (b < 0)
				b = -b;
			while (a != b) {
				if (a > b)
					a = a - b;
				else
					b = b - a;
			}
			return a;
		} else
			return 0;
	}

	public Rational add(Rational arg) {
		return new Rational(
				(licznik * arg.mianownik) + arg.licznik * mianownik, mianownik
						* arg.mianownik);
	}

	public Rational mul(Rational arg) { // mnozenie ulamkow (metoda statyczna)

		return new Rational(licznik * arg.licznik, mianownik * arg.mianownik);
	}

	public Rational div(Rational arg) { // metoda dzielenia ulamkow (statyczna)
		if (arg.licznik == 0)
			throw new ArithmeticException();
		return new Rational(licznik * arg.mianownik, mianownik * arg.licznik);

	}

	public boolean lessThan(Rational arg) {
		if (licznik * arg.mianownik < arg.licznik * mianownik)
			return true;
		else
			return false;
	}

	public boolean greaterThan(Rational arg) {
		if (licznik * arg.mianownik > arg.licznik * mianownik)
			return true;
		else
			return false;
	}
	public boolean lessThanOrEqual(Rational arg) {
		if (licznik * arg.mianownik <= arg.licznik * mianownik)
			return true;
		else
			return false;
	}

	public boolean greaterThanOrEqual(Rational arg) {
		if (licznik * arg.mianownik >= arg.licznik * mianownik)
			return true;
		else
			return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rational other = (Rational) obj;
		if (licznik != other.licznik)
			return false;
		if (mianownik != other.mianownik)
			return false;
		return true;
	}
}
