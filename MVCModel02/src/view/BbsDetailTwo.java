package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dto.BbsDto;
import singleton.Singleton;

public class BbsDetailTwo extends JFrame {

	public BbsDetailTwo(int seq) {

		setTitle("bbs update");
		setBounds(100, 100, 370, 480);

		setLayout(null);
		
		Singleton s = Singleton.getInstance();

		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 15, 45, 30);
		add(idLabel);

		JLabel idTextf = new JLabel(s.getLoginID());
		idTextf.setBounds(95, 15, 200, 25);
		add(idTextf);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(50, 55, 45, 30);
		add(titleLabel);

		JTextField titleTextf = new JTextField(20);
		titleTextf.setBounds(95, 55, 200, 25);
		titleTextf.setText("");
		add(titleTextf);

		JLabel contentsLabel = new JLabel("Contents");
		contentsLabel.setBounds(50, 95, 45, 30);
		add(contentsLabel);

		JTextArea contentsTextf = new JTextArea();
		contentsTextf.setBounds(50, 125, 250, 250);
		add(contentsTextf);

		JButton btnAdd = new JButton("add");
		btnAdd.setBounds(75, 380, 200, 45);
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (titleTextf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "제목을 작성해주세요");
					return;
				} else if (contentsTextf.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "내용을 작성해주세요");
					return;
				} else {
					boolean check = false;
					
					BbsDto dto = new BbsDto();
					dto.setId(idTextf.getText());
					dto.setTitle(titleTextf.getText());
					dto.setContent(contentsTextf.getText());
					check = s.bbsCtrl.UpdateWritePage(dto, seq);
					if (check) {
						JOptionPane.showMessageDialog(null, "정상적으로 수정이 되었습니다.");
						BbsListView view = new BbsListView();
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "수정할 권한이 없습니다.");
						BbsListView view = new BbsListView();
						dispose();
						return;
					}
				}
			}
		});
		add(btnAdd);

		setLocation(600, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
