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

import controller.BbsController;
import dao.BbsDao;
import dto.BbsDto;
import dto.MemberDto;
import singleton.Singleton;

public class BbsListView extends JFrame implements MouseListener {

	private JTable jtable;
	private JScrollPane jscrPane;

	private JButton writeBtn;

	String columnNames[] = { "번호", "제목", "작성자" };

	Object rowData[][];

	DefaultTableModel model; // table의 넓이를 설정하기 위한 값. view

	List<BbsDto> list = null;
	
	

	public BbsListView() {

		setTitle("bbs window");
		setBounds(100, 100, 640, 330);

		setLayout(null);

		JLabel label = new JLabel();
		label.setBounds(10, 10, 120, 15);
		add(label);
		
		Singleton s = Singleton.getInstance();
		list = s.bbsCtrl.viewListSelect(s.selectedIndex, s.text, s.rowEndNum, s.rowStartNum, s.rowSetNum);

		// jtable에 row를 생성
		rowData = new Object[list.size()][3];
		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < list.size(); i++) {
			BbsDto dto = list.get(i);
			rowData[i][0] = dto.getRowNum(); // 글번호
			rowData[i][1] = dto.getTitle(); // 글제목
			rowData[i][2] = dto.getId(); // 작성자
		}
		// 테이블 관련
		// 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0);
		model.setDataVector(rowData, columnNames); // 2차원 배열을 사용했음. 이게 뭥미

		jtable = new JTable(model);
		jtable.addMouseListener(this);
		// column의 폭을 설정하는 방법
		jtable.getColumnModel().getColumn(0).setMaxWidth(50); // 번호
		jtable.getColumnModel().getColumn(1).setMaxWidth(500); // 제목
		jtable.getColumnModel().getColumn(2).setMaxWidth(200); // 작성자

		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 180);
		add(jscrPane);

		writeBtn = new JButton("글쓰기");
		writeBtn.setBounds(10, 10, 100, 22);
		writeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.bbsCtrl.WriteAddPageView();
				dispose();
			}
		});
		add(writeBtn);

		JButton returnBtn = new JButton("목록으로");
		returnBtn.setBounds(110, 10, 100, 22);
		returnBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.rowStartNum = 0;
				s.selectedIndex = -1;
				dispose();
				s.bbsCtrl.bbsView();
			}
		});
		add(returnBtn);

		Choice searchChoice = new Choice();
		searchChoice.add("ID");
		searchChoice.add("Title");
		searchChoice.add("Content");
		searchChoice.setBounds(310, 10, 50, 20);
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
				boolean check = false;
				if (!searchTextf.getText().trim().equals("")) {
					s.selectedIndex = searchChoice.getSelectedIndex();
					s.text = searchTextf.getText();
					dispose();
					s.bbsCtrl.bbsView();
				} else {
					JOptionPane.showMessageDialog(null, "검색어를 입력 하지 않았습니다.");
				}
			}
		});
		add(searchBtn);

		JButton nextBtn = new JButton("다음페이지");
		nextBtn.setBounds(510, 250, 100, 22);
		nextBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.selectedIndex = -1;
				s.rowEndNum = list.size();
				if (s.rowEndNum == s.bbsCtrl.rowNum20(s.rowStartNum)) {
					s.rowStartNum = s.rowStartNum + 1;
					dispose();
					s.bbsCtrl.bbsView();
				} else if (s.rowEndNum != s.bbsCtrl.rowNum20(s.rowStartNum)) {
					JOptionPane.showMessageDialog(null, "마지막 페이지 입니다.");
				}

			}
		});
		add(nextBtn);

		JButton nextTBtn = new JButton("이전페이지");
		nextTBtn.setBounds(410, 250, 100, 22);
		nextTBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				s.selectedIndex = -1;
				if (s.rowStartNum <= 0) {
					JOptionPane.showMessageDialog(null, "첫 페이지 입니다.");
				} else {
					s.rowStartNum = s.rowStartNum - 1;
					dispose();
					s.bbsCtrl.bbsView();
				}
			}
		});
		add(nextTBtn);

		JButton logoutBtn = new JButton("로그아웃");
		logoutBtn.setBounds(10, 250, 100, 22);
		logoutBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
				s.rowStartNum = 0;
				s.selectedIndex =-1;
				s.memCtrl.login();
			}
		});
		add(logoutBtn);

		setLocation(400, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Singleton s = Singleton.getInstance();
		int row = jtable.getSelectedRow();
		s.bbsCtrl.readCount(list.get(row).getSeq());
		s.bbsCtrl.bbsDetailOne(list.get(row).getSeq());
		dispose();

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
