import java.lang.reflect.*;
import java.beans.*;

public class BeanAnalyze {

  static void say(String s) { System.out.println(s); }

  public static void main(String[] arg) throws Exception {

    //BeanInfo beanInfo = Introspector.getBeanInfo(Class.forName(arg[0]));
    BeanInfo beanInfo = Introspector.getBeanInfo(javax.swing.JButton.class);
    PropertyDescriptor[] pd = beanInfo.getPropertyDescriptors();
    MethodDescriptor[] md = beanInfo.getMethodDescriptors();
    EventSetDescriptor[] evd = beanInfo.getEventSetDescriptors();

    say("W³aœciwoœci:");
    for (int i = 0; i < pd.length; i++) {
      say(pd[i].getShortDescription());
      // getReadMethod i getWriteMethod zwracaj¹ obiekty typu Method
      say(" getter: "+ pd[i].getReadMethod());
      say(" setter: "+ pd[i].getWriteMethod());
    }

    say("\nMetody:");
    for (int i=0; i<md.length; i++) {
      say(" " + md[i].getMethod());
    }

    say("\nZdarzenia:");
    for (int i = 0; i < evd.length; i++) {
      say("Zdarzenie : " + evd[i].getShortDescription());
      Method[] met = evd[i].getListenerMethods();
      say("Metody obs³ugi:");
      for (int j=0; j < met.length; j++)  say(" " + met[j]);
    }
  }

}