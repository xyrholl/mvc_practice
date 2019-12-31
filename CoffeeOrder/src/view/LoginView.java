package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import singleton.Singleton;

public class LoginView extends JFrame{
	
	public LoginView() {

		setTitle("로그인화면");
		setSize(420, 230);
		setLayout(null);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 35, 45, 25);
		add(idLabel);
		
		JTextField idTextf = new JTextField(20);
		idTextf.setBounds(95, 37, 140, 25);
		idTextf.setText("");
		add(idTextf);
		
		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(50, 70, 45, 25);
		add(pwLabel);
	
		JPasswordField pwTextf = new JPasswordField(20);
		pwTextf.setBounds(95, 70, 140, 25);
		pwTextf.setText("");
		add(pwTextf);
		
		JButton btnlogin = new JButton("로그인");
		btnlogin.setBounds(250, 35, 80, 60);
		btnlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton singleton = Singleton.getInstance();
				if (!idTextf.getText().trim().equals("") && !pwTextf.getText().trim().equals("")) {
					if (singleton.memCtrl.checkLogin(idTextf.getText(), pwTextf.getText()).getId() != null) {
						singleton.selectCtrl.menu();
						singleton.setLoginID(idTextf.getText());
						dispose();
					}
				}else {
					JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 입력해주세요.");
				}
				
			}
		});
		add(btnlogin);
		
		JButton btnsign = new JButton("회원가입");
		btnsign.setBounds(95, 110, 235, 25);
		btnsign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton singleton = Singleton.getInstance();
				singleton.memCtrl.sign();
				dispose();
			}
		});
		add(btnsign);
		
		setLocation(600, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

}
