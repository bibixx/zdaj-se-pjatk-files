import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class Tests {
	public static PESEL[] PESELKI = { new PESEL("01234567891"),
			new PESEL("87043095363"), new PESEL("85092377942"),
			new PESEL("96120827434") };
	public static Class<PESEL> ClassPointer;

	@Before
	public void Before() {
		ClassPointer = PESEL.class; 
	}

	@Test //dlugosc
	public void ValidTest() throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		Method isValid = ClassPointer.getDeclaredMethod("isValid");
		isValid.setAccessible(true);
		for (int i = 0; i != PESELKI.length; i++)
			isValid.invoke(PESELKI[i]);
	}

	@Test // plec
	public void SexTest() throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		Method getSex = ClassPointer.getDeclaredMethod("getSex");
		getSex.setAccessible(true);
		for (int i = 0; i != PESELKI.length; i++)
			getSex.invoke(PESELKI[i]);
	}

	@Test // bierzemy date urodzenia z peselu
	public void DateTest() throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Method getDate = ClassPointer.getDeclaredMethod("getDate");
		getDate.setAccessible(true);
		for (int i = 0; i != PESELKI.length; i++)
			getDate.invoke(PESELKI[i]);
	}

}
