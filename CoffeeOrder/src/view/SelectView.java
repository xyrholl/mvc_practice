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
	
	private String name;
	private String sizePrice_short;
	private String sizePrice_tall;
	private String sizePrice_grande;
	

	public SelectView(Object objDto, CoffeeDto dto) {

		setTitle("선택화면");
		setSize(650, 230);

		setLayout(null);

		Singleton singleton = Singleton.getInstance();
		
		name = dto.getName();
		sizePrice_short = dto.getSize_short();
		sizePrice_tall = dto.getSize_tall();
		sizePrice_grande = dto.getSize_grande();
		
		if (objDto != null) {
			if (objDto instanceof CoffeeDto) {
				name = ((CoffeeDto)objDto).getName();
				sizePrice_short = ((CoffeeDto)objDto).getSize_short();
				sizePrice_tall = ((CoffeeDto)objDto).getSize_tall();
				sizePrice_grande = ((CoffeeDto)objDto).getSize_grande();
			}
		}

		JLabel menuNameLabel = new JLabel(name);
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
//				singleton.orderCtrl.orderAdd(singleton.getLoginID(), dto.getName(), sizeChoice.getSelectedIndex());
//				
//				singleton.getLoginID(); // string
//				dto.getName(); // string
//				sizeChoice.getSelectedIndex(); // int
//				syrupChoice.getSelectedIndex(); // int
//				otherChoice.getSelectedIndex(); // int
//				eaChoice.getText().trim(); // string
				
				Singleton singleton = Singleton.getInstance();
				OrderDto orderDto = new OrderDto();
				
				orderDto.setLoginID(singleton.getLoginID());
				orderDto.setCoffeeName(name);
				if (sizeChoice.getSelectedIndex() == 0) {
					orderDto.setPrice(sizePrice_short);
					orderDto.setSize("Short");
				} else if (sizeChoice.getSelectedIndex() == 1) {
					orderDto.setPrice(sizePrice_tall);
					orderDto.setSize("Tall");
				} else if (sizeChoice.getSelectedIndex() == 2) {
					orderDto.setPrice(sizePrice_grande);
					orderDto.setSize("Grande");
				}
				System.out.println(orderDto.getPrice());
				
				int price = Integer.parseInt(orderDto.getPrice());

				if (syrupChoice.getSelectedIndex() == 0) {
					orderDto.setSyrupAdd("추가안함");
				} else if (syrupChoice.getSelectedIndex() == 1) {
					orderDto.setSyrupAdd("바닐라 시럽");
					orderDto.setPrice(Integer.toString((price + 500)));
				} else if (syrupChoice.getSelectedIndex() == 2) {
					orderDto.setSyrupAdd("카라멜 시럽");
					orderDto.setPrice(Integer.toString((price + 500)));
				} else if (syrupChoice.getSelectedIndex() == 3) {
					orderDto.setSyrupAdd("헤이즐넛 시럽");
					orderDto.setPrice(Integer.toString((price + 500)));
				}

				if (otherChoice.getSelectedIndex() == 0) {
					orderDto.setOtherAdd("추가안함");
				} else if (otherChoice.getSelectedIndex() == 1) {
					orderDto.setOtherAdd("샷추가");
					orderDto.setPrice(Integer.toString((price + 500)));
				} else if (otherChoice.getSelectedIndex() == 2) {
					orderDto.setOtherAdd("휘핑크림 추가");
					orderDto.setPrice(Integer.toString((price + 500)));
				}
				orderDto.setEA(eaChoice.getText().trim());
				int total_price = price*Integer.parseInt(eaChoice.getText());
				orderDto.setPrice(Integer.toString(total_price));
				for (int i = 0; i < singleton.orderList.size(); i++) {
					if (singleton.orderList.get(i).getCoffeeName().equals(name)
							&& !singleton.orderList.get(i).getPrice().equals(Integer.toString(total_price))) {
						singleton.orderList.remove(i);
					} else if(singleton.orderList.get(i).getCoffeeName().equals(name) || singleton.orderList.get(i).getPrice().equals(Integer.toString(total_price))) {
						singleton.orderList.remove(i);
					}
				}
				singleton.orderList.add(orderDto);
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
