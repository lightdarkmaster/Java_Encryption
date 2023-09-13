import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldLimitExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("TextField Limit Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 100);

            JTextField textField = new JTextField(20); // 20 columns wide
            frame.add(textField);

            // Add a document filter to limit the input and show a warning dialog if exceeded
            ((AbstractDocument) textField.getDocument()).setDocumentFilter(new LengthDocumentFilter(16, textField));

            frame.setVisible(true);
        });
    }

        public static class LengthDocumentFilter extends DocumentFilter {
            private int maxLength;
            private JTextField textField;

            public LengthDocumentFilter(int maxLength, JTextField textField) {
                this.maxLength = maxLength;
                this.textField = textField;
            }

            @Override
            public void insertString(FilterBypass fb, int offset, String str, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + str.length()) <= maxLength) {
                    super.insertString(fb, offset, str, attr);
                } else {
                    showWarningDialog();
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() - length + str.length()) <= maxLength) {
                    super.replace(fb, offset, length, str, attr);
                } else {
                    showWarningDialog();
                }
            }

            private void showWarningDialog() {
                JOptionPane.showMessageDialog(textField, "Character limit exceeded (Max: " + maxLength + ")", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
}
