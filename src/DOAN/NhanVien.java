package DOAN;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NhanVien extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private static int employeeID = 2;

	Connection conn;
	private JLabel lblHoTen;
	private JLabel lblNs;
	private JLabel lblDc;
	private JLabel lblSdt;
	private JLabel lblCv;
	private JLabel lblL;

	private String hoTen;
	private String ngaySinh;
	private String diaChi;
	private String soDienThoai;
	private String chucVu;
	private double luong;
	private  int id ;
	private JTextField txtId;
	private JTextField txtHt;
	private JTextField txtNs;
	private JTextField txtDc;
	private JTextField txtSdt;
	private JTextField txtCv;
	private JTextField txtL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NhanVien frame = new NhanVien(employeeID);
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
	public NhanVien(int employeeID) {
		this.employeeID = employeeID;
		connectToDatabase(); // Kết nối đến cơ sở dữ liệu

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTilte = new JLabel("Thông tin nhân viên");
		lblTilte.setHorizontalAlignment(SwingConstants.CENTER);
		lblTilte.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblTilte.setBounds(76, 10, 361, 42);
		contentPane.add(lblTilte);

		JLabel lblIDLabel = new JLabel("ID:");
		lblIDLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblIDLabel.setBounds(32, 58, 129, 28);
		contentPane.add(lblIDLabel);

		lblHoTen = new JLabel("Họ và tên: ");
		lblHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblHoTen.setBounds(32, 119, 129, 28);
		contentPane.add(lblHoTen);

		lblNs = new JLabel("Ngày sinh: ");
		lblNs.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNs.setBounds(32, 180, 129, 28);
		contentPane.add(lblNs);

		lblDc = new JLabel("Địa chỉ: ");
		lblDc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblDc.setBounds(32, 241, 129, 28);
		contentPane.add(lblDc);

		lblSdt = new JLabel("Số điện thoại: ");
		lblSdt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSdt.setBounds(32, 302, 129, 28);
		contentPane.add(lblSdt);

		lblCv = new JLabel("Chức vụ: ");
		lblCv.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblCv.setBounds(32, 363, 129, 28);
		contentPane.add(lblCv);

		lblL = new JLabel("Lương: ");
		lblL.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblL.setBounds(32, 424, 129, 28);
		contentPane.add(lblL);

		JButton btnLu = new JButton("Lưu");
		btnLu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sua();
			}
		});
		btnLu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLu.setBounds(304, 488, 176, 42);
		contentPane.add(btnLu);

		txtId = new JTextField();
		txtId.setBounds(186, 62, 276, 24);
		contentPane.add(txtId);
		txtId.setColumns(10);

		txtHt = new JTextField();
		txtHt.setColumns(10);
		txtHt.setBounds(186, 119, 276, 24);
		contentPane.add(txtHt);

		txtNs = new JTextField();
		txtNs.setColumns(10);
		txtNs.setBounds(186, 180, 276, 24);
		contentPane.add(txtNs);

		txtDc = new JTextField();
		txtDc.setColumns(10);
		txtDc.setBounds(186, 241, 276, 24);
		contentPane.add(txtDc);

		txtSdt = new JTextField();
		txtSdt.setColumns(10);
		txtSdt.setBounds(186, 302, 276, 24);
		contentPane.add(txtSdt);

		txtCv = new JTextField();
		txtCv.setColumns(10);
		txtCv.setBounds(186, 363, 276, 24);
		contentPane.add(txtCv);

		txtL = new JTextField();
		txtL.setColumns(10);
		txtL.setBounds(186, 424, 276, 24);
		contentPane.add(txtL);

		loadEmployeeInfo(); // Tải thông tin của nhân viên với ID đã nhận
	}

	// Kết nối CSDL
	private void connectToDatabase() {
		try {
			String url = "jdbc:mysql://localhost:3306/nhanvien";
			String username = "root";
			String password = "Woplaf22.";

			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadEmployeeInfo() {
		try {
			String sql = "SELECT * FROM nhanvien WHERE ID = ?";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, employeeID);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				id = rs.getInt("ID");
				hoTen = rs.getString("hoTen");
				ngaySinh = rs.getString("ngaySinh");
				diaChi = rs.getString("diaChi");
				soDienThoai = rs.getString("soDienThoai");
				chucVu = rs.getString("chucVu");
				luong = rs.getDouble("luong");

				// Hiển thị thông tin lên các JTextField
				txtId.setText(String.valueOf(id));
				txtHt.setText(hoTen);
				txtNs.setText(ngaySinh);
				txtDc.setText(diaChi);
				txtSdt.setText(soDienThoai);
				txtCv.setText(chucVu);
				txtL.setText(String.valueOf(luong));
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên có ID = " + employeeID);
			}

			rs.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Cập nhật thông tin nhân viên
	public void Sua() {
		try {
			String sql = "UPDATE nhanvien SET hoTen = ?, ngaySinh = ?, diaChi = ?, soDienThoai = ?, chucVu = ?, luong = ? WHERE ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hoTen);
			pstmt.setString(2, ngaySinh);
			pstmt.setString(3, diaChi);
			pstmt.setString(4, soDienThoai);
			pstmt.setString(5, chucVu);
			pstmt.setDouble(6, luong);
			pstmt.setInt(7, id);

			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(null, "Lưu thông tin nhân viên thành công.");
			} else {
				JOptionPane.showMessageDialog(null, "Lưu thông tin nhân viên thất bại.");
			}

			pstmt.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
		}
	}
}
