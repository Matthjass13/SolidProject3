package screens.ui;

import screens.Screen;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Customised password fields
 * @see TextField
 * @author Matthias Gaillard
 * @since 12.12.2024
 */
public class PasswordField extends TextField {

    private final PasswordDocument passwordDocument;

    public PasswordField(String placeholder, int x, int y, int width, int height, int fontSize, Screen screen) {
        super(placeholder, x, y, width, height, fontSize, screen);
        this.passwordDocument = new PasswordDocument();
        this.setDocument(passwordDocument); // Associer le document personnalisé
    }
    public PasswordField(String placeholder, int x, int y, Screen screen) {
        this(placeholder, x, y, 200, 30, 20, screen);
    }

    public String getText() {
        return passwordDocument.getRealText();
    }


    /**
     * Inner class to keep track of the real password
     * ChatGPT generated
     */
    private static class PasswordDocument extends PlainDocument {
        private final StringBuilder realText = new StringBuilder(); // Contient le texte réel

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) return;
            realText.insert(offs, str);
            super.insertString(offs, "*".repeat(str.length()), a);
        }

        @Override
        public void remove(int offs, int len) throws BadLocationException {
            realText.delete(offs, offs + len);
            super.remove(offs, len);
        }

        public String getRealText() {
            return realText.toString();
        }
    }


}

