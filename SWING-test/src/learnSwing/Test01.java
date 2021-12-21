package learnSwing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Test01 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Login Example");
		frame.setSize(350, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.add(panel);
		
		panel.setLayout(null);
		JLabel userLabel = new JLabel("User: ");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		JTextField userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		JLabel pswordLabel = new JLabel("password: ");
		pswordLabel.setBounds(10, 50, 80, 25);
		panel.add(pswordLabel);
		
		JTextField pswordText = new JTextField(20);
		pswordText.setBounds(100, 50, 165, 25);
		panel.add(pswordText);
		
		JButton loginButton = new JButton("login");
		loginButton.setBounds(120, 100, 80, 25);
		panel.add(loginButton);
		
		frame.setVisible(true);
	}
}
