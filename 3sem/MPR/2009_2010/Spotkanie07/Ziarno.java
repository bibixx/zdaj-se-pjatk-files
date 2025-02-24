package Spotkanie07;
import java.beans.*;

class Ziarno {

	private PropertyChangeSupport chg = new PropertyChangeSupport(this);
	
	int language = 0;
	String slowa[] = {"polska",
					"niemcy",
					"rofland"
					};
	
	public synchronized int getLanguage() {
		return language;
	}
	
	public synchronized String getLang() {
		return slowa[language];
	}
	
	public synchronized void setLanguage(int lang) {
		int oldLang = language;
		language = lang;
		chg.firePropertyChange("language", oldLang, language);
	}
	
	
	public synchronized void addPropertyChangeListener(PropertyChangeListener listener) {
	chg.addPropertyChangeListener(listener);
	}
	
	public synchronized void removePropertyChangeListener(PropertyChangeListener listener) {
	chg.removePropertyChangeListener(listener);
	}
	
	
}