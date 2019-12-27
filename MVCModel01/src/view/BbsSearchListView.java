package view;

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

import dto.BbsDto;
import javabean.BbsDao;

public class BbsSearchListView extends JFrame implements MouseListener {

	private JTable jtable;
	private JScrollPane jscrPane;

	private JButton writeBtn;

	String columnNames[] = { "번호", "제목", "작성자" };

	Object rowData[][];

	DefaultTableModel model; // table의 넓이를 설정하기 위한 값. view

	List<BbsDto> list = null;

	public BbsSearchListView() {

		setTitle("Search View");
		setBounds(100, 100, 640, 480);

		setLayout(null);

		JLabel label = new JLabel();
		label.setBounds(10, 10, 120, 15);
		add(label);
		// dao를 통해서 list를 취득
		BbsDao dao = BbsDao.getInstance();
		list = dao.getBbsList();
		// jtable에 row를 생성
		rowData = new Object[list.size()][3];
		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < list.size(); i++) {
			BbsDto dto = list.get(i);
			rowData[i][0] = i + 1; // 글번호
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
		jscrPane.setBounds(10, 50, 600, 300);
		add(jscrPane);

		writeBtn = new JButton("글쓰기");
		writeBtn.setBounds(10, 10, 100, 20);
		writeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WriteAddPageView addWrite = new WriteAddPageView();
				dispose();
			}
		});
		add(writeBtn);



		JTextField searchTextf = new JTextField(50);
		searchTextf.setBounds(350, 10, 140, 20);
		searchTextf.setText("");
		add(searchTextf);

		JButton searchBtn = new JButton("검색");
		searchBtn.setBounds(510, 10, 100, 20);
		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "검색을 시작합니다.");
				dispose();
				BbsSearchListView searchView = new BbsSearchListView();
			}
		});
		add(searchBtn);

		setLocation(600, 0);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = jtable.getSelectedRow();
		BbsDetailOne detail = new BbsDetailOne(list.get(row).getSeq());
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
