// musi byc w osobnym folderze projektu
package Spotkanie07;
import java.lang.reflect.*;
import java.beans.*;

public class Introspekcja {

	public static void main(String[] arg) throws Exception {

		BeanInfo beanInfo = Introspector.getBeanInfo(Class.forName("Spotkanie07.Ziarno"));

		PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
		MethodDescriptor[] md = beanInfo.getMethodDescriptors();
		EventSetDescriptor[] evd = beanInfo.getEventSetDescriptors();

		
		System.out.println("W³aœciwoœci:");
		for (int i = 0; i < pd.length; i++) {
			System.out.println(pd[i].getShortDescription());
			System.out.println(" getter: " + pd[i].getReadMethod());
			System.out.println(" setter: " + pd[i].getWriteMethod());
		}

		
		System.out.println("\nMetody:");
		for (int i = 0; i < md.length; i++) {
			System.out.println(" " + md[i].getMethod());
		}

		
		System.out.println("\nZdarzenia:");
		for (int i = 0; i < evd.length; i++) {
			System.out.println("Zdarzenie : " + evd[i].getShortDescription());
			Method[] met = evd[i].getListenerMethods();
			System.out.println("Metody obs³ugi:");
			
			for (int j = 0; j < met.length; j++)
				System.out.println(" " + met[j]);
		}
	}

}