package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;
//import javabean.MemberDao;

public class LoginView extends JFrame{
	
	public LoginView() {
		
		setTitle("로그인화면");
		setSize(285, 220);

		
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
	
		JPasswordField pwTextf = new JPasswordField(8);
		pwTextf.setBounds(95, 55, 100,25);
		pwTextf.setText("");
		add(pwTextf);
		
		JButton btnlogin = new JButton("login");
		btnlogin.setBounds(50, 100, 80, 45);
		btnlogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton s = Singleton.getInstance();
				MemberDto check = s.memCtrl.loginCheck(idTextf.getText(), pwTextf.getText());
				s.setLoginID(check.getId());
				if (check != null) {
					dispose();
					s.bbsCtrl.bbsView();
				}else {
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.");
				}
			}
		});
		add(btnlogin);
		
		JButton btnsign = new JButton("sign");
		btnsign.setBounds(140, 100, 80, 45);
		btnsign.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton s = Singleton.getInstance();
				s.memCtrl.regi();
				dispose();
			}
		});
		add(btnsign);
		
		setLocation(600, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
