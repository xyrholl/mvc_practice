package view;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dto.CoffeeDto;
import dto.OrderDto;
import singleton.Singleton;

public class SelectView extends JFrame {

	public SelectView(Object objDto, CoffeeDto dto) {

		setTitle("선택화면");
		setSize(650, 230);
		setLayout(null);

		Singleton singleton = Singleton.getInstance();

		JLabel menuNameLabel = new JLabel(dto.getName());
		menuNameLabel.setBounds(35, 30, 300, 25);
		add(menuNameLabel);

		JLabel syrupLabel = new JLabel("시럽추가");
		syrupLabel.setBounds(35, 80, 100, 25);
		add(syrupLabel);

		Choice syrupChoice = new Choice();
		syrupChoice.add("none");
		syrupChoice.add("Vanilla");
		syrupChoice.add("Caramel");
		syrupChoice.add("Hazelnut");
		syrupChoice.setBounds(20, 110, 90, 20);
		add(syrupChoice);

		JLabel otherLabel = new JLabel("기타");
		otherLabel.setBounds(165, 80, 100, 25);
		add(otherLabel);

		Choice otherChoice = new Choice();
		otherChoice.add("none");
		otherChoice.add("Shot Add");
		otherChoice.add("Whipped Cream Add");
		otherChoice.setBounds(120, 110, 120, 20);
		add(otherChoice);

		JLabel sizeLabel = new JLabel("사이즈 선택");
		sizeLabel.setBounds(260, 80, 100, 25);
		add(sizeLabel);

		Choice sizeChoice = new Choice();
		sizeChoice.add("Short");
		sizeChoice.add("Tall");
		sizeChoice.add("Grande");
		sizeChoice.setBounds(250, 110, 90, 20);
		add(sizeChoice);

		JLabel eaLabel = new JLabel("몇 잔");
		eaLabel.setBounds(365, 80, 100, 25);
		add(eaLabel);

		JTextField eaChoice = new JTextField(10);
		eaChoice.setBounds(350, 110, 70, 23);
		eaChoice.setText("1");
		add(eaChoice);

		JButton logoutBtn = new JButton("메뉴로 돌아가기");
		logoutBtn.setBounds(440, 30, 150, 25);
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.selectCtrl.menu();
				dispose();
			}
		});
		add(logoutBtn);

		JButton selectBtn = new JButton("선택완료");
		selectBtn.setBounds(440, 110, 150, 25);
		selectBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.orderCtrl.orderAdd(objDto, dto, dto.getName(), sizeChoice.getSelectedIndex(), syrupChoice.getSelectedIndex(), otherChoice.getSelectedIndex(), eaChoice.getText());
				singleton.selectCtrl.menu();
				dispose();
			}
		});
		add(selectBtn);

		setLocation(600, 250);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
