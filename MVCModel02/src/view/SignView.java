package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dto.MemberDto;
import singleton.Singleton;

public class SignView extends JFrame {

	public SignView() {

		setTitle("회원가입 화면");
		setSize(300, 280);

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
		pwTextf.setBounds(95, 55, 100, 25);
		pwTextf.setText("");
		add(pwTextf);

		JLabel nameLabel = new JLabel("NAME");
		nameLabel.setBounds(50, 95, 45, 30);
		add(nameLabel);

		JTextField nameTextf = new JTextField(8);
		nameTextf.setBounds(95, 95, 100, 25);
		nameTextf.setText("");
		add(nameTextf);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(50, 135, 45, 30);
		add(emailLabel);

		JTextField emailTextf = new JTextField(8);
		emailTextf.setBounds(95, 135, 100, 25);
		emailTextf.setText("");
		add(emailTextf);

		JButton btnSgin = new JButton("sgin");
		btnSgin.setBounds(50, 175, 145, 30);
		btnSgin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton s = Singleton.getInstance();
				
				if (!idTextf.getText().equals("") && !pwTextf.getText().equals("") && !nameTextf.getText().equals("")) {
					MemberDto dto = new MemberDto();
					if (emailTextf.getText().equals("")) {
						dto = new MemberDto(idTextf.getText(), pwTextf.getText(), nameTextf.getText(), null, 1);
					}else {
						dto = new MemberDto(idTextf.getText(), pwTextf.getText(), nameTextf.getText(), emailTextf.getText(), 1);
					}
					System.out.println(dto.toString());
					boolean b = s.memCtrl.idCheck(idTextf.getText());
					if (!b) {
						s.memCtrl.refiAf(dto);
						JOptionPane.showMessageDialog(null, "성공적으로 회원가입이 완료 되었습니다. 로그인을 해주세요.");
						dispose();
						s.memCtrl.login();
					}else {
						JOptionPane.showMessageDialog(null, "중복된 이메일 입니다. 다시 확인해 주세요.");
						return;
					}
				}else if(pwTextf.getText().equals("")){
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요.");
					return;
				}else if(nameTextf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "이름을 입력해주세요.");
					return;
				}else if(idTextf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요.");
					return;
				}
			
			}
		});
		add(btnSgin);

		JButton btnCheck = new JButton("check");
		btnCheck.setBounds(205, 15, 70, 25);
		btnCheck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Singleton s = Singleton.getInstance();
				
				if (idTextf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "id를 입력해주세요");
					return;
				}
				boolean b = s.memCtrl.idCheck(idTextf.getText().trim());
				if (b) {
					JOptionPane.showMessageDialog(null, "사용할 수 없는 id입니다.");
					idTextf.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "id는 사용하실수 있습니다.");
				}
			}
		});
		add(btnCheck);

		setLocation(450, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
