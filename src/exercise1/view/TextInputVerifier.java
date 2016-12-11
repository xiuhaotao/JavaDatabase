package exercise1.view;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

public class TextInputVerifier extends InputVerifier {
	@Override
	public boolean verify(JComponent input) {
		String text = ((JTextField) input).getText();
		if (text.length() > 0 && text.length() < 255) {
			return true;
		} else {
			return false;
		}
	}
}
