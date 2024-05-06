package DOAN;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class QuanLiNhanVien extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtMaNV;
	private JTextField txtHoTen;
	private JTextField txtNgaySinh;
	private JTextField txtDiaChi;
	private JTextField txtSdt;
	private JTextField txtChucVu;
	private JTextField txtLuong;
	private JLabel errorlb;
	private JLabel errordetails;

	JTable table = new JTable();

	Connection conn;
	java.sql.Statement stm;
	ResultSet rst;

	Vector vData = new Vector(); // Vector để lưu trữ dữ liệu
	Vector vTitle = new Vector(); // Vector để lưu trữ tiêu đề cột

	DefaultTableModel model;
	int selectedrow = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiNhanVien frame = new QuanLiNhanVien("Quản Lí Nhân Sự");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuanLiNhanVien(String s) {
		super(s);
		try {
			String url = "jdbc:mysql://localhost:3306/nhanvien";
			String username = "root";
			String password = "Woplaf22.";

			conn = DriverManager.getConnection(url, username, password);
			stm = conn.createStatement();

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 1322, 728);
			contentPane = new JPanel();
			contentPane.setBackground(Color.LIGHT_GRAY);
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
			lblQLNV.setBounds(365, 0, 511, 79);
			contentPane.add(lblQLNV);

			JLabel lblMaNV = new JLabel("Mã nhân viên: ");
			lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblMaNV.setBounds(24, 108, 142, 27);
			contentPane.add(lblMaNV);

			JLabel lbHoTen = new JLabel("     Họ và tên: ");
			lbHoTen.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lbHoTen.setBounds(24, 165, 142, 27);
			contentPane.add(lbHoTen);

			JLabel lblNgaySinh = new JLabel("     Ngày sinh: ");
			lblNgaySinh.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNgaySinh.setBounds(24, 222, 142, 27);
			contentPane.add(lblNgaySinh);

			JLabel lblDiaChi = new JLabel("         Địa chỉ: ");
			lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDiaChi.setBounds(705, 108, 132, 27);
			contentPane.add(lblDiaChi);

			JLabel lblSdt = new JLabel("Số điện thoại: ");
			lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSdt.setBounds(705, 165, 132, 27);
			contentPane.add(lblSdt);

			JLabel lblChucVu = new JLabel("       Chức vụ: ");
			lblChucVu.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblChucVu.setBounds(705, 222, 132, 27);
			contentPane.add(lblChucVu);

			JLabel lblLuong = new JLabel("         Lương: ");
			lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblLuong.setBounds(705, 279, 132, 27);
			contentPane.add(lblLuong);

			txtMaNV = new JTextField();
			txtMaNV.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtMaNV.setBounds(157, 108, 251, 27);
			contentPane.add(txtMaNV);
			txtMaNV.setColumns(10);

			txtHoTen = new JTextField();
			txtHoTen.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtHoTen.setColumns(10);
			txtHoTen.setBounds(157, 165, 251, 27);
			contentPane.add(txtHoTen);

			txtNgaySinh = new JTextField();
			txtNgaySinh.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtNgaySinh.setColumns(10);
			txtNgaySinh.setBounds(157, 222, 251, 27);
			contentPane.add(txtNgaySinh);

			txtDiaChi = new JTextField();
			txtDiaChi.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtDiaChi.setColumns(10);
			txtDiaChi.setBounds(835, 108, 251, 27);
			contentPane.add(txtDiaChi);

			txtSdt = new JTextField();
			txtSdt.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtSdt.setColumns(10);
			txtSdt.setBounds(835, 165, 251, 27);
			contentPane.add(txtSdt);

			txtChucVu = new JTextField();
			txtChucVu.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtChucVu.setColumns(10);
			txtChucVu.setBounds(835, 222, 251, 27);
			contentPane.add(txtChucVu);

			txtLuong = new JTextField();
			txtLuong.setFont(new Font("Tahoma", Font.BOLD, 10));
			txtLuong.setColumns(10);
			txtLuong.setBounds(835, 279, 251, 27);
			contentPane.add(txtLuong);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(24, 330, 1268, 322);
			contentPane.add(scrollPane);

			JPanel panel = new JPanel();
			scrollPane.setViewportView(panel);

			model = new DefaultTableModel(vData, vTitle);
			table = new JTable();
			table.setBounds(24, 330, 1268, 322);
			table.addMouseListener(this);// Gắng ống nghe khi ấn chuột vào hàng
			table.setModel(model);
			scrollPane.setViewportView(table);

			JButton btnThem = new JButton("Thêm");
			btnThem.addActionListener(this);
			btnThem.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnThem.setBounds(76, 279, 106, 27);
			contentPane.add(btnThem);

			JButton btnSua = new JButton("Sửa");
			btnSua.addActionListener(this);
			btnSua.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSua.setBounds(234, 282, 106, 27);
			contentPane.add(btnSua);

			JButton btnXoa = new JButton("Xóa");
			btnXoa.addActionListener(this);
			btnXoa.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnXoa.setBounds(392, 282, 106, 27);
			contentPane.add(btnXoa);

			JButton btnReset = new JButton("Reset");
			btnReset.addActionListener(this);
			btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnReset.setBounds(550, 282, 106, 27);
			contentPane.add(btnReset);

			JButton btnTimKiem = new JButton("Tìm Kiếm");
			btnTimKiem.addActionListener(this);
			btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnTimKiem.setBounds(453, 108, 106, 27);
			contentPane.add(btnTimKiem);

			JButton btnSxTheoMaNV = new JButton("Sắp xếp theo chức vụ nhân viên");
			btnSxTheoMaNV.setBackground(SystemColor.activeCaption);
			btnSxTheoMaNV.addActionListener(this);
			btnSxTheoMaNV.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSxTheoMaNV.setBounds(346, 654, 302, 27);
			contentPane.add(btnSxTheoMaNV);

			JButton btnSxTheoTen = new JButton("Sắp xếp theo lương nhân viên");
			btnSxTheoTen.setBackground(SystemColor.activeCaption);
			btnSxTheoTen.addActionListener(this);
			btnSxTheoTen.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSxTheoTen.setBounds(990, 654, 302, 27);
			contentPane.add(btnSxTheoTen);

			JButton btnSxTheoChucVu = new JButton("Sắp xếp theo mã nhân viên");
			btnSxTheoChucVu.setBackground(SystemColor.activeCaption);
			btnSxTheoChucVu.addActionListener(this);
			btnSxTheoChucVu.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSxTheoChucVu.setBounds(24, 654, 302, 27);
			contentPane.add(btnSxTheoChucVu);

			JButton btnSxTheoLuong = new JButton("Sắp xếp theo tên nhân viên");
			btnSxTheoLuong.setBackground(SystemColor.activeCaption);
			btnSxTheoLuong.addActionListener(this);
			btnSxTheoLuong.setFont(new Font("Tahoma", Font.BOLD, 15));
			btnSxTheoLuong.setBounds(668, 654, 302, 27);
			contentPane.add(btnSxTheoLuong);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void reloadData() {
		try {
			vData.clear();
			vTitle.clear();

			ResultSet rs = stm.executeQuery("select * from nhanvien");

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

	public void TimKiem() {
		if (txtMaNV.getText().equals("") == false) {
			try {
				vData.clear();
				vTitle.clear();

				ResultSet rs = stm.executeQuery("select * from nhanvien where ID = '" + txtMaNV.getText() + "'");

				ResultSetMetaData rsm = (ResultSetMetaData) rs.getMetaData();

				int num_column = rsm.getColumnCount();

				for (int i = 1; i <= num_column; i++) {
					vTitle.add(rsm.getColumnLabel(i));
				}

				while (rs.next()) {
					Vector st = new Vector(num_column);
					for (int i = 1; i <= num_column; i++) {
						st.add(rs.getString(i));
					}
					vData.add(st);
				}

				model.fireTableDataChanged();
				txtMaNV.setText("");
				txtHoTen.setText("");
				txtNgaySinh.setText("");
				txtDiaChi.setText("");
				txtSdt.setText("");
				txtChucVu.setText("");
				txtLuong.setText("");

				rs.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Bạn cần điền mã nhân viên cần tìm.");
		}
	}

	public void Them() {
		try {
			String id = txtMaNV.getText();
			String ht = txtHoTen.getText();
			String ns = txtNgaySinh.getText();
			String dc = txtDiaChi.getText();
			String sdt = txtSdt.getText();
			String cv = txtChucVu.getText();
			String l = txtLuong.getText();

			if (id.isEmpty() || ht.isEmpty() || ns.isEmpty() || dc.isEmpty() || sdt.isEmpty() || cv.isEmpty()
					|| l.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
				return;
			}

			String sql = "INSERT INTO nhanvien(ID, Ho_ten, Ngay_sinh, Dia_chi, Sdt, Chuc_vu, Luong) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, ht);
			pstmt.setString(3, ns);
			pstmt.setString(4, dc);
			pstmt.setString(5, sdt);
			pstmt.setString(6, cv);
			pstmt.setString(7, l);

			// Execute the SQL statement
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công.");
				reloadData(); // Refresh the table
			} else {
				JOptionPane.showMessageDialog(null, "Thêm nhân viên thất bại.");
			}

			// Close the PreparedStatement
			pstmt.close();

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
		}
	}

	public void Sua() {
		if (selectedrow >= 0) {
			try {
				String id = txtMaNV.getText();
				String ht = txtHoTen.getText();
				String ns = txtNgaySinh.getText();
				String dc = txtDiaChi.getText();
				String sdt = txtSdt.getText();
				String cv = txtChucVu.getText();
				String l = txtLuong.getText();

				

				String sql = "UPDATE nhanvien SET Ho_ten = ?, Ngay_sinh = ?, Dia_chi = ?, Sdt = ?, Chuc_vu = ?, Luong = ? WHERE ID = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ht);
				pstmt.setString(2, ns);
				pstmt.setString(3, dc);
				pstmt.setString(4, sdt);
				pstmt.setString(5, cv);
				pstmt.setString(6, l);
				pstmt.setString(7, id);

				int rowsAffected = pstmt.executeUpdate();

				if (rowsAffected > 0) {
					JOptionPane.showMessageDialog(null, "Sửa nhân viên thành công.");
					reloadData(); // Refresh the table
				} else {
					JOptionPane.showMessageDialog(null, "Sửa nhân viên thất bại.");
				}

				// Close the PreparedStatement
				pstmt.close();

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Lỗi: " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chọn hàng để sửa !");
		
		}
	}

	public void Xoa() {
		if (selectedrow >= 0) {
			int dialogResult = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa không?", "Xác nhận",
					JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				try {
					Vector st = (Vector) vData.elementAt(selectedrow);

					String sql = "Delete from nhanvien where ID = \"" + st.elementAt(0) + "\"";
					stm.executeUpdate(sql);

					vData.remove(selectedrow);

					model.fireTableDataChanged();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chọn hàng để xóa !");
		}
	}

	public void Reset() {
		reloadData();
		model.fireTableDataChanged();
	}
	
	public void SxTheoMaNV() {
	    try {
	        vData.clear();
	        vTitle.clear();

	        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien ORDER BY ID");

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

	public void SxTheoTenNV() {
	    try {
	        vData.clear();
	        vTitle.clear();

	        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien ORDER BY Ho_ten");

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

	public void SxTheoChucVu() {
	    try {
	        vData.clear();
	        vTitle.clear();

	        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien ORDER BY Chuc_vu");

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

	public void SxTheoLuong() {
	    try {
	        vData.clear();
	        vTitle.clear();

	        ResultSet rs = stm.executeQuery("SELECT * FROM nhanvien ORDER BY Luong");

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
		// Lấy chỉ mục của hàng được chọn
		int selectedRow = table.getSelectedRow();

		// Lấy giá trị của các ô từ hàng được chọn
		String ID = (String) table.getValueAt(selectedRow, 0);
		String ht = (String) table.getValueAt(selectedRow, 1);
		String ns = (String) table.getValueAt(selectedRow, 2);
		String sdt = (String) table.getValueAt(selectedRow, 3);
		String dc = (String) table.getValueAt(selectedRow, 4);
		String cv = (String) table.getValueAt(selectedRow, 5);
		String l = (String) table.getValueAt(selectedRow, 6);

		// Gán giá trị của các ô từ hàng được chọn cho các ô text tương ứng
		txtMaNV.setText(ID);
		txtHoTen.setText(ht);
		txtNgaySinh.setText(ns);
		txtSdt.setText(sdt);
		txtDiaChi.setText(dc);
		txtChucVu.setText(cv);
		txtLuong.setText(l);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		selectedrow = table.getSelectedRow();
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
		if (e.getActionCommand().equals("Xóa")) {
			Xoa();
		}
		if (e.getActionCommand().equals("Tìm Kiếm")) {
			TimKiem();
		}
		if (e.getActionCommand().equals("Thêm")) {
			Them();
		}
		if (e.getActionCommand().equals("Sửa")) {
			Sua();
		}
		if (e.getActionCommand().equals("Reset")) {
			Reset();
		}
		if (e.getActionCommand().equals("Sắp xếp theo mã nhân viên")) {
			SxTheoMaNV();
		}
		if (e.getActionCommand().equals("Sắp xếp theo tên nhân viên")) {
			SxTheoTenNV();
		}
		if (e.getActionCommand().equals("Sắp xếp theo chức vụ nhân viên")) {
			SxTheoChucVu();
		}
		if (e.getActionCommand().equals("Sắp xếp theo lương nhân viên")) {
			SxTheoLuong();
		}
	}
}
