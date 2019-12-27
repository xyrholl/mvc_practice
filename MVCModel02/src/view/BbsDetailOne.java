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

public class BbsDetailOne extends JFrame {

	public BbsDetailOne(int seqNum) {

		setTitle("BbsDetailOne");
		setBounds(100, 100, 370, 600);

		setLayout(null);

		Singleton s = Singleton.getInstance();
		BbsDto dto = s.bbsCtrl.selectOne(seqNum);
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setBounds(50, 15, 45, 30);
		add(idLabel);

		JLabel idTextf = new JLabel(dto.getId());
		idTextf.setBounds(95, 15, 200, 25);
		add(idTextf);

		JLabel wDateLabel = new JLabel("Wdate");
		wDateLabel.setBounds(50, 55, 45, 30);
		add(wDateLabel);

		JTextField wDateTextf = new JTextField(20);
		wDateTextf.setBounds(95, 55, 200, 25);
		wDateTextf.setText(dto.getWdate());
		add(wDateTextf);

		JLabel readCountLabel = new JLabel("Count");
		readCountLabel.setBounds(50, 95, 45, 30);
		add(readCountLabel);

		JTextField readCountTextf = new JTextField(20);
		readCountTextf.setBounds(95, 95, 200, 25);
		readCountTextf.setText(dto.getReadcount()+"");
		add(readCountTextf);

		JLabel titleLabel = new JLabel("Title");
		titleLabel.setBounds(50, 135, 45, 30);
		add(titleLabel);

		JTextField titleTextf = new JTextField();
		titleTextf.setBounds(95, 135, 200, 25);
		titleTextf.setText(dto.getTitle());
		add(titleTextf);

		JLabel contentsLabel = new JLabel("Contents");
		contentsLabel.setBounds(50, 175, 45, 30);
		add(contentsLabel);

		JTextArea contentsTextf = new JTextArea();
		contentsTextf.setBounds(50, 205, 250, 250);
		contentsTextf.setText(dto.getContent());
		add(contentsTextf);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(50, 470, 70, 45);
		btnUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getId().equals(s.getLoginID())) {
					JOptionPane.showMessageDialog(null, "글을 수정 하시겠습니까?");
					s.bbsCtrl.bbsDetailTwo(seqNum);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "작성자만 수정이 가능합니다.");
				}
				
			}
		});
		add(btnUpdate);

		JButton btnDel = new JButton("Delete");
		btnDel.setBounds(140, 470, 70, 45);
		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dto.getId().equals(s.getLoginID())) {
					JOptionPane.showMessageDialog(null, "글을 삭제 하시겠습니까?");
					s.bbsCtrl.delete(seqNum);
					dispose();
					BbsListView view = new BbsListView();
					JOptionPane.showMessageDialog(null, titleTextf.getText() + " 글이 삭제 되었습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "작성자만 삭제가 가능합니다.");
				}
			}
		});
		add(btnDel);
		
		JButton btnReturn = new JButton("Return");
		btnReturn.setBounds(230, 470, 70, 45);
		btnReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				BbsListView view = new BbsListView();
			}
		});
		add(btnReturn);

		setLocation(600, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
