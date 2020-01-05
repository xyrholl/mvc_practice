package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.OrderDto;
import singleton.Singleton;

public class OldOrderView extends JFrame{
	
	private JTable jtable2;
	private JScrollPane jscrPane2;

	String orderMenuRow[] = { "Espresso Beverages", "주문시간", "사이즈", "잔", "가격" };

	Object orderRowData[][];

	DefaultTableModel model2;
	
	public OldOrderView() {

		setTitle("이전 주문내역 확인");
		setSize(650, 330);
		setLayout(null);
		
		Singleton singleton = Singleton.getInstance();
		
		JLabel orderMenu = new JLabel("이전 주문내역");
		orderMenu.setBounds(40, 20, 100, 22);
		add(orderMenu);
		
		singleton.oldOrderList = singleton.orderCtrl.selectOne(singleton.getLoginID());
		
		if (singleton.oldOrderList != null) {
			orderRowData = new Object[singleton.oldOrderList.size()][5];
			for (int i = 0; i < singleton.oldOrderList.size(); i++) {
				OrderDto orderDto = singleton.oldOrderList.get(i);
				orderRowData[i][0] = orderDto.getCoffeeName();
				orderRowData[i][1] = orderDto.getOrderDate();
				orderRowData[i][2] = orderDto.getSize();
				orderRowData[i][3] = orderDto.getEA();
				orderRowData[i][4] = orderDto.getPrice();
			}
		}
		
		model2 = new DefaultTableModel(orderMenuRow, 0);
		model2.setDataVector(orderRowData, orderMenuRow); // 2차원 배열을 사용했음. 이게 뭥미

		jtable2 = new JTable(model2);
		// column의 폭을 설정하는 방법
		jtable2.getColumnModel().getColumn(0).setMaxWidth(300); // 메뉴
		jtable2.getColumnModel().getColumn(1).setMaxWidth(300); // 주문시간
		jtable2.getColumnModel().getColumn(2).setMaxWidth(150); // 사이즈
		jtable2.getColumnModel().getColumn(3).setMaxWidth(150); // 잔
		jtable2.getColumnModel().getColumn(4).setMaxWidth(150); // 가격

		jscrPane2 = new JScrollPane(jtable2);
		jscrPane2.setBounds(10, 50, 600, 183);
		add(jscrPane2);
		
		int totalPrice = 0;
		int totalEA = 0;
		for (OrderDto orderDto : singleton.oldOrderList) {
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
		
//		JButton nextBtn = new JButton("다음페이지");
//		nextBtn.setBounds(510, 240, 100, 25);
//		nextBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				singleton.selectedIndex = -1;
//				singleton.rowEndNum = singleton.orderList.size();
//				if (singleton.rowEndNum % 10 == 0) {
//					JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.");
//				} else if (singleton.rowEndNum == singleton.selectCtrl.rowNum20(singleton.rowStartNum)) {
//					singleton.rowStartNum = singleton.rowStartNum + 1;
//					dispose();
//					singleton.selectCtrl.menu();
//				}
//				
//				else if (singleton.rowEndNum != singleton.selectCtrl.rowNum20(singleton.rowStartNum)) {
//					JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.");
//				}
//
//			}
//		});
//		add(nextBtn);
//
//		JButton backTBtn = new JButton("이전페이지");
//		backTBtn.setBounds(410, 240, 100, 25);
//		backTBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				singleton.selectedIndex = -1;
//				if (singleton.rowStartNum <= 0) {
//					JOptionPane.showMessageDialog(null, "첫 페이지 입니다.");
//				} else {
//					singleton.rowStartNum = singleton.rowStartNum - 1;
//					dispose();
//					singleton.selectCtrl.menu();
//				}
//			}
//		});
//		add(backTBtn);
		
		JButton menuReturn = new JButton("메뉴로 돌아가기");
		menuReturn.setBounds(410, 240, 200, 25);
		menuReturn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				singleton.oldOrderList.clear();
				singleton.selectCtrl.menu();
			}
		});
		add(menuReturn);
		
		setLocation(600, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

}
