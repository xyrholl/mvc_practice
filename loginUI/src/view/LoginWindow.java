package view;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginWindow extends JFrame{
	
	public LoginWindow() {
		setTitle("로그인화면");
		setSize(400, 400);

		
		setLayout(null);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 15, 45, 30);
		add(idLabel);
		
		JTextField idTextf = new JTextField(8);
		idTextf.setBounds(95, 15, 100, 25);
		idTextf.setText("");
		add(idTextf);
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(50, 55, 45, 30);
		add(pwLabel);
	
		JTextField pwTextf = new JTextField(8);
		pwTextf.setBounds(95, 55, 100,25);
		pwTextf.setText("");
		add(pwTextf);
		
		
		setLocation(100, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
