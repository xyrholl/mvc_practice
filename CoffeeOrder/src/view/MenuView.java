package view;

import java.awt.Choice;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto.CoffeeDto;
import dto.OrderDto;
import singleton.Singleton;

public class MenuView extends JFrame implements MouseListener {

	private JTable jtable1;
	private JTable jtable2;
	private JScrollPane jscrPane1;
	private JScrollPane jscrPane2;

	String menuNames[] = { "Espresso Beverages", "Shot", "Tall", "Grande" };
	String orderMenuRow[] = { "Espresso Beverages", "시럽", "샷추가 or 휘핑추가", "크기", "잔", "가격" };

	Object menuRowData[][];
	Object orderRowData[][];

	DefaultTableModel model;
	DefaultTableModel model2;

	List<CoffeeDto> menuList = null;
	List<OrderDto> orderList = null;

	public MenuView() {

		setTitle("메뉴화면");
		setSize(650, 550);

		setLayout(null);

		JLabel label = new JLabel();
		label.setBounds(10, 10, 120, 15);
		add(label);

		Singleton singleton = Singleton.getInstance();
		menuList = singleton.selectCtrl.getBbsList(singleton.selectedIndex, singleton.text, singleton.rowEndNum,
				singleton.rowStartNum, singleton.rowSetNum);

		// jtable에 row를 생성
		menuRowData = new Object[menuList.size()][4];
		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < menuList.size(); i++) {
			CoffeeDto dto = menuList.get(i);
			menuRowData[i][0] = dto.getName(); // 종류
			menuRowData[i][1] = dto.getSize_short(); // szie 1
			menuRowData[i][2] = dto.getSize_tall(); // size 2
			menuRowData[i][3] = dto.getSize_grande(); // size 3
		}
		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(menuNames, 0);
		model.setDataVector(menuRowData, menuNames); // 2차원 배열을 사용했음. 이게 뭥미

		jtable1 = new JTable(model);
		jtable1.addMouseListener(this);
		// column의 폭을 설정하는 방법
		jtable1.getColumnModel().getColumn(0).setMaxWidth(300); // 메뉴
		jtable1.getColumnModel().getColumn(1).setMaxWidth(150); // size1
		jtable1.getColumnModel().getColumn(2).setMaxWidth(150); // size2
		jtable1.getColumnModel().getColumn(3).setMaxWidth(150); // size3

		jscrPane1 = new JScrollPane(jtable1);
		jscrPane1.setBounds(10, 50, 600, 183);
		add(jscrPane1);

		JLabel menuTitle = new JLabel("가격표");
		menuTitle.setBounds(40, 10, 100, 22);
		add(menuTitle);

		JButton returnBtn = new JButton("목록으로");
		returnBtn.setBounds(110, 10, 100, 22);
		returnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.selectedIndex = -1;
				singleton.selectCtrl.menu();
				dispose();
			}
		});
		add(returnBtn);

		Choice searchChoice = new Choice();
		searchChoice.add("None");
		searchChoice.add("Americano");
		searchChoice.add("Latte");
		searchChoice.add("Mocca");
		searchChoice.setBounds(270, 10, 90, 20);
		add(searchChoice);

		JTextField searchTextf = new JTextField(50);
		searchTextf.setBounds(365, 10, 140, 22);
		searchTextf.setText("");
		add(searchTextf);

		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(510, 10, 100, 22);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.selectedIndex = searchChoice.getSelectedIndex();
				singleton.text = searchTextf.getText();
				if (singleton.selectedIndex == 0 && searchTextf.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(null, "검색어를 입력해 주세요.");
				}else {
					dispose();
					singleton.selectCtrl.menu();
				}
			}
		});
		add(searchBtn);

		JButton nextBtn = new JButton("다음페이지");
		nextBtn.setBounds(510, 245, 100, 22);
		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.selectedIndex = -1;
				singleton.rowEndNum = menuList.size();
				if (singleton.rowEndNum % 10 == 0) {
					JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.");
				} else if (singleton.rowEndNum == singleton.selectCtrl.rowNum20(singleton.rowStartNum)) {
					singleton.rowStartNum = singleton.rowStartNum + 1;
					dispose();
					singleton.selectCtrl.menu();
				} else if (singleton.rowEndNum != singleton.selectCtrl.rowNum20(singleton.rowStartNum)) {
					JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.");
				}

			}
		});
		add(nextBtn);

		JButton backTBtn = new JButton("이전페이지");
		backTBtn.setBounds(410, 245, 100, 22);
		backTBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.selectedIndex = -1;
				if (singleton.rowStartNum <= 0) {
					JOptionPane.showMessageDialog(null, "첫 페이지 입니다.");
				} else {
					singleton.rowStartNum = singleton.rowStartNum - 1;
					dispose();
					singleton.selectCtrl.menu();
				}
			}
		});
		add(backTBtn);
		
		JLabel orderMenu = new JLabel("주문내역");
		orderMenu.setBounds(40, 270, 100, 22);
		add(orderMenu);
		orderList = singleton.orderList;

		if (orderList != null) {
			orderRowData = new Object[orderList.size()][6];
			// list에서 테이블로 데이터를 삽입하기 위한 처리
			for (int i = 0; i < orderList.size(); i++) {
				OrderDto orderDto = orderList.get(i);
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
		jtable2.addMouseListener(this);
		// column의 폭을 설정하는 방법
		jtable2.getColumnModel().getColumn(0).setMaxWidth(300); // 메뉴
		jtable2.getColumnModel().getColumn(1).setMaxWidth(150); // 시럽
		jtable2.getColumnModel().getColumn(2).setMaxWidth(150); // 샷추가
		jtable2.getColumnModel().getColumn(3).setMaxWidth(150); // 휘핑
		jtable2.getColumnModel().getColumn(4).setMaxWidth(150); // 크기
		jtable2.getColumnModel().getColumn(5).setMaxWidth(150); // 잔수

		jscrPane2 = new JScrollPane(jtable2);
		jscrPane2.setBounds(10, 300, 600, 140);
		add(jscrPane2);

		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(10, 450, 100, 22);
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				singleton.memCtrl.login();
				singleton.orderList.clear();
				dispose();
			}
		});
		add(logoutBtn);

		JButton memberBtn = new JButton("이전 주문 내역");
		memberBtn.setBounds(120, 450, 150, 22);
		memberBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				singleton.orderCtrl.oldOrderView();
			}
		});
		add(memberBtn);
		
		JButton orderBtn = new JButton("주문하기");
		orderBtn.setBounds(510, 450, 100, 22);
		orderBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				singleton.orderCtrl.orderView();
			}
		});
		add(orderBtn);

		JButton orderResetBtn = new JButton("주문내역 비우기");
		orderResetBtn.setBounds(350, 450, 150, 22);
		orderResetBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				orderList.clear();
				dispose();
				singleton.selectCtrl.menu();
			}
		});
		add(orderResetBtn);

		setLocation(600, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Singleton singleton = Singleton.getInstance();
		if (e.getSource() == jtable1) {
			int rowMenu = jtable1.getSelectedRow();
			singleton.selectCtrl.selectMenu(null, menuList.get(rowMenu));
			dispose();
		} else if (e.getSource() == jtable2) {
			int rowOrder = jtable2.getSelectedRow();
			CoffeeDto coffeeDto = singleton.selectCtrl.selectOne(orderList.get(rowOrder).getCoffeeName());
			singleton.selectCtrl.selectMenu(orderList.get(rowOrder), coffeeDto);
			dispose();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
