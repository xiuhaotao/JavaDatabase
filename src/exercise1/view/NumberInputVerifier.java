package exercise1.view;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class NumberInputVerifier extends InputVerifier {
	@Override
	public boolean verify(JComponent input) {
		String text = ((JTextField) input).getText();
		try {
			Integer i = Integer.valueOf(text);
			return (i>0 && i<10000000);			
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
