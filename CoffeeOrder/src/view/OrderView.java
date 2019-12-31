package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.OrderDto;
import singleton.Singleton;

public class OrderView extends JFrame{
	
	private JTable jtable2;
	private JScrollPane jscrPane2;

	String orderMenuRow[] = { "Espresso Beverages", "시럽", "샷추가 or 휘핑추가", "크기", "잔", "가격" };

	Object orderRowData[][];

	DefaultTableModel model2;
	
	
	public OrderView() {
		
		setTitle("주문확인 및 결제");
		setSize(650, 300);
		setLayout(null);
		
		Singleton singleton = Singleton.getInstance();
		
		JLabel orderMenu = new JLabel("주문내역");
		orderMenu.setBounds(40, 20, 100, 22);
		add(orderMenu);
		
		if (singleton.orderList != null) {
			orderRowData = new Object[singleton.orderList.size()][6];
			for (int i = 0; i < singleton.orderList.size(); i++) {
				OrderDto orderDto = singleton.orderList.get(i);
				orderRowData[i][0] = orderDto.getCoffeeName();
				orderRowData[i][1] = orderDto.getSyrupAdd();
				orderRowData[i][2] = orderDto.getOtherAdd();
				orderRowData[i][3] = orderDto.getSize();
				orderRowData[i][4] = orderDto.getEA();
				orderRowData[i][5] = orderDto.getPrice();
			}
		}
		
		model2 = new DefaultTableModel(orderMenuRow, 0);
		model2.setDataVector(orderRowData, orderMenuRow); // 2차원 배열을 사용했음. 이게 뭥미

		jtable2 = new JTable(model2);
		// column의 폭을 설정하는 방법
		jtable2.getColumnModel().getColumn(0).setMaxWidth(300); // 메뉴
		jtable2.getColumnModel().getColumn(1).setMaxWidth(150); // 시럽
		jtable2.getColumnModel().getColumn(2).setMaxWidth(150); // 샷추가
		jtable2.getColumnModel().getColumn(3).setMaxWidth(150); // 휘핑
		jtable2.getColumnModel().getColumn(4).setMaxWidth(150); // 크기
		jtable2.getColumnModel().getColumn(5).setMaxWidth(150); // 잔수

		jscrPane2 = new JScrollPane(jtable2);
		jscrPane2.setBounds(10, 50, 600, 100);
		add(jscrPane2);
		
		ArrayList<OrderDto> list = (ArrayList<OrderDto>) singleton.orderList;
		int totalPrice = 0;
		int totalEA = 0;
		for (OrderDto orderDto : list) {
			 totalPrice += Integer.parseInt(orderDto.getPrice());
			 totalEA += Integer.parseInt(orderDto.getEA());
		}
		
		JLabel totalPriceLabel = new JLabel("총 금액");
		totalPriceLabel.setBounds(500, 20, 100, 22);
		add(totalPriceLabel);
		
		JLabel orderPrice = new JLabel(totalPrice+"");
		orderPrice.setBounds(550, 20, 100, 22);
		add(orderPrice);
		
		JLabel totalEALabel = new JLabel("총 개수");
		totalEALabel.setBounds(400, 20, 100, 22);
		add(totalEALabel);
		
		JLabel orderEA = new JLabel(totalEA+"");
		orderEA.setBounds(450, 20, 100, 22);
		add(orderEA);
		
		JButton orderStart = new JButton("결제하기");
		orderStart.setBounds(500, 200, 110, 30);
		orderStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.orderCtrl.addOrder(singleton.orderList);
				JOptionPane.showMessageDialog(null, singleton.getLoginID()+" 님의 주문이 결제 완료 되었습니다.");
				dispose();
				singleton.orderList.clear();
				singleton.memCtrl.login();
			}
		});
		add(orderStart);
		
		JButton returnBtn = new JButton("메뉴로 돌아가기");
		returnBtn.setBounds(320, 200, 150, 30);
		returnBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				singleton.selectCtrl.menu();
			}
		});
		add(returnBtn);
		
		setLocation(600, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
