package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;

public class SignView extends JFrame{
	
	public SignView() {
		
		setTitle("회원가입 화면");
		setSize(420, 330);

		setLayout(null);

		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(70, 15, 45, 30);
		add(idLabel);

		JTextField idTextf = new JTextField(20);
		idTextf.setBounds(120, 15, 125, 25);
		idTextf.setText("");
		add(idTextf);

		JLabel pwLabel = new JLabel("PW");
		pwLabel.setBounds(70, 55, 45, 30);
		add(pwLabel);

		JTextField pwTextf = new JTextField(20);
		pwTextf.setBounds(120, 55, 200, 25);
		pwTextf.setText("");
		add(pwTextf);

		JLabel nameLabel = new JLabel("NAME");
		nameLabel.setBounds(70, 95, 45, 30);
		add(nameLabel);

		JTextField nameTextf = new JTextField(20);
		nameTextf.setBounds(120, 95, 200, 25);
		nameTextf.setText("");
		add(nameTextf);

		JLabel ageLabel = new JLabel("AGE");
		ageLabel.setBounds(70, 135, 45, 30);
		add(ageLabel);

		JTextField ageTextf = new JTextField(20);
		ageTextf.setBounds(120, 135, 200, 25);
		ageTextf.setText("");
		add(ageTextf);
		
		JLabel phoneLabel = new JLabel("PHONE");
		phoneLabel.setBounds(70, 175, 45, 30);
		add(phoneLabel);

		JTextField phoneTextf = new JTextField(20);
		phoneTextf.setBounds(120, 175, 200, 25);
		phoneTextf.setText("");
		add(phoneTextf);
		
		JButton btnSgin = new JButton("회원가입");
		btnSgin.setBounds(200, 215, 120, 30);
		btnSgin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton singleton = Singleton.getInstance();
				MemberDto dto = new MemberDto();
				dto.setId(idTextf.getText());
				dto.setPwd(pwTextf.getText());
				dto.setName(nameTextf.getText());
				dto.setAge(Integer.parseInt(ageTextf.getText()));
				dto.setPhone(phoneTextf.getText());
				dto.setAuth(0);
				System.out.println(dto.toString());
				singleton.memCtrl.addMember(dto);
				dispose();
				singleton.memCtrl.login();
			}
		});
		add(btnSgin);

		JButton btnReturn = new JButton("돌아가기");
		btnReturn.setBounds(70, 215, 120, 30);
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton singleton = Singleton.getInstance();
				singleton.memCtrl.login();
				dispose();
			}
		});
		add(btnReturn);

		JButton btnCheck = new JButton("check");
		btnCheck.setBounds(250, 15, 70, 25);
		btnCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				Singleton s = Singleton.getInstance();
//				
//				if (idTextf.getText().trim().equals("")) {
//					JOptionPane.showMessageDialog(null, "id를 입력해주세요");
//					return;
//				}
//				boolean b = s.memCtrl.idCheck(idTextf.getText().trim());
//				if (b) {
//					JOptionPane.showMessageDialog(null, "사용할 수 없는 id입니다.");
//					idTextf.setText("");
//				} else {
//					JOptionPane.showMessageDialog(null, "id는 사용하실수 있습니다.");
//				}
			}
		});
		add(btnCheck);

		setLocation(600, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}

}
