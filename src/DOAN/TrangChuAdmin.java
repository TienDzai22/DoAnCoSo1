package DOAN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class TrangChuAdmin extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;

	Connection conn;
	java.sql.Statement stm;
	ResultSet rst;

	JTable table = new JTable();
	DefaultTableModel model;
	Vector vData = new Vector(); // Vector để lưu trữ dữ liệu
	Vector vTitle = new Vector(); // Vector để lưu trữ tiêu đề cột

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangChuAdmin frame = new TrangChuAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrangChuAdmin() {
		super();
		try {
			String url = "jdbc:mysql://localhost:3306/nhanvien";
			String username = "root";
			String password = "Woplaf22.";

			conn = DriverManager.getConnection(url, username, password);
			stm = conn.createStatement();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 883, 486);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);

			reloadData();// Nạp dữ liệu vào 2 vector: vTitle(tên cột) và vData(các hàng dữ liệu) chuẩn bị
			// tạo Jtable
			model = new DefaultTableModel(vData, vTitle);// Tạo bảng hiển thị thông tin lên cửa sổ
			this.setLocationRelativeTo(null);

			JLabel lblQLNV = new JLabel("QUẢN LÍ NHÂN VIÊN");
			lblQLNV.setHorizontalAlignment(SwingConstants.CENTER);
			lblQLNV.setFont(new Font("Tahoma", Font.BOLD, 40));
			lblQLNV.setBounds(168, 0, 511, 79);
			contentPane.add(lblQLNV);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(308, 89, 551, 350);
			contentPane.add(scrollPane);

			model = new DefaultTableModel(vData, vTitle);
			table = new JTable();
			table.setBounds(24, 330, 1268, 322);
			table.addMouseListener(this);// Gắng ống nghe khi ấn chuột vào hàng
			table.setModel(model);
			scrollPane.setViewportView(table);

			JButton btnNewButton = new JButton("Tài khoản của nhân viên");
			btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNewButton.setBounds(10, 89, 274, 70);
			contentPane.add(btnNewButton);

			JButton btnNewButton_1 = new JButton("Chỉnh sửa thông tin");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					QuanLiNhanVien qlnv = new QuanLiNhanVien("Quản Lí Nhân Viên");
					qlnv.setVisible(true);
					dispose();
				}
			});
			btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNewButton_1.setBounds(10, 186, 274, 70);
			contentPane.add(btnNewButton_1);

			JButton btnNewButton_2 = new JButton("Thông tin đồ án");
			btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNewButton_2.setBounds(10, 279, 274, 70);
			contentPane.add(btnNewButton_2);

			JButton btnNewButton_3 = new JButton("Đăng xuất");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DangNhapAdmin dna = new DangNhapAdmin();
					dna.setVisible(true);
					dispose();
				}
			});
			btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btnNewButton_3.setBounds(10, 369, 274, 70);
			contentPane.add(btnNewButton_3);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void reloadData() {
		try {
			vData.clear();
			vTitle.clear();

			ResultSet rs = stm.executeQuery("select ID, USERNAME, PASS from nhanvien.account");

			int num_column = rs.getMetaData().getColumnCount();

			for (int i = 1; i <= num_column; i++) {
				vTitle.add(rs.getMetaData().getColumnLabel(i));
			}

			while (rs.next()) {
				Vector<String> row = new Vector<>();
				for (int i = 1; i <= num_column; i++) {
					row.add(rs.getString(i));
				}
				vData.add(row);
			}

			model = new DefaultTableModel(vData, vTitle);
			table.setModel(model);

			rs.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}


