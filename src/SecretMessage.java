import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SecretMessage extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JButton btnMoveUp;
	private JButton btnClear;
	
	public String encoding(String message, int keyVal) {
		String output = "";
		char key = (char) keyVal;
		for ( int x = 0; x < message.length(); x++ ) {
			char input = message.charAt(x);
			if (input >= 'A' && input <= 'Z') 
			{ 
				input += key;
				if (input > 'Z')
					input -= 26;
				if (input < 'A')
					input += 26;
			}
			else if (input >= 'a' && input <= 'z')
			{ 
				input += key;
				if (input > 'z')
					input -= 26;
				if (input < 'a')
					input += 26;
			}
			else if (input >= '0' && input <= '9')
			{
				input += (keyVal % 10);
				if (input > '9')
					input -= 10;
				if (input < '0')
					input += 10;
			}
			output += input;
		}
		return output;
	}
	
	public SecretMessage() {
		getContentPane().setBackground(new Color(135, 206, 235));
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 8, 571, 140);
		getContentPane().add(scrollPane);
		
		txtIn = new JTextArea();
		scrollPane.setViewportView(txtIn);
		txtIn.setLineWrap(true);
		txtIn.setWrapStyleWord(true);
		txtIn.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(8, 215, 571, 140);
		getContentPane().add(scrollPane_1);
		
		txtOut = new JTextArea();
		scrollPane_1.setViewportView(txtOut);
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setFont(new Font("Segoe UI", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("Key:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(185, 167, 56, 29);
		getContentPane().add(lblNewLabel);
		
		txtKey = new JTextField();
		txtKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int key = Integer.parseInt(txtKey.getText());
					slider.setValue(key);
				} catch (Exception ex) {
					// TODO: handle exception
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		txtKey.setHorizontalAlignment(SwingConstants.RIGHT);
		txtKey.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtKey.setBounds(237, 170, 56, 23);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnEncodedecode = new JButton("Encode/Decode");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String message = txtIn.getText();
					int textKey = Integer.parseInt(txtKey.getText());
					String codeText = encoding(message, textKey);
					txtOut.setText(codeText);
				} catch (Exception ex) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "Please enter the whole number value for an encryption key.");
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		btnEncodedecode.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEncodedecode.setBackground(UIManager.getColor("Button.light"));
		btnEncodedecode.setBounds(301, 167, 149, 29);
		getContentPane().add(btnEncodedecode);
		
		slider = new JSlider();
		slider.setValue(0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				 txtKey.setText("" + slider.getValue());
				 String message = txtIn.getText();
				 int textKey = slider.getValue();
				 String codeText = encoding(message, textKey);
				 txtOut.setText(codeText);
			}
		});
		slider.setPaintTicks(true);
		slider.setBackground(new Color(135, 206, 235));
		slider.setPaintLabels(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(13);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setBounds(8, 167, 170, 40);
		getContentPane().add(slider);
		
		btnMoveUp = new JButton("Move Up ^");
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIn.setText(txtOut.getText());
				slider.setValue(-slider.getValue());
				
			}
		});
		btnMoveUp.setBackground(UIManager.getColor("Button.light"));
		btnMoveUp.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnMoveUp.setBounds(458, 168, 121, 27);
		getContentPane().add(btnMoveUp);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIn.setText("");
				txtOut.setText("");
				txtKey.setText("");
				slider.setValue(0);
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(488, 363, 90, 23);
		getContentPane().add(btnClear);
		setTitle("Secret Message App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SecretMessage gui = new SecretMessage();
		gui.setSize(600, 430);
		gui.setVisible(true);
	}
}
