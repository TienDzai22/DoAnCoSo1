package DOAN;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class DangNhap extends JFrame {

	JPanel contentPane;
	JPasswordField jfPass;
	JTextField txtdn;

	Connection conn;
	Statement stm;
	ResultSet rst;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhap frame = new DangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DangNhap() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(SystemColor.textHighlight);
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblLogin.setBounds(483, 37, 334, 57);
		contentPane.add(lblLogin);

		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserName.setBounds(451, 93, 187, 39);
		contentPane.add(lblUserName);

		JLabel lblPassWord = new JLabel("Password");
		lblPassWord.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassWord.setBounds(451, 181, 187, 39);
		contentPane.add(lblPassWord);

		jfPass = new JPasswordField();
		jfPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jfPass.setBounds(451, 218, 402, 40);
		contentPane.add(jfPass);

		txtdn = new JTextField();
		txtdn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtdn.setColumns(10);
		txtdn.setBounds(449, 131, 402, 40);
		contentPane.add(txtdn);

		JButton btnThmAccount = new JButton("Sign Up");
		btnThmAccount.setForeground(Color.WHITE);
		btnThmAccount.setBackground(new Color(50, 205, 50));
		btnThmAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangKi chuyen = new DangKi();
				chuyen.setVisible(true);
				this.dispose();
			}

			private void dispose() {
				// TODO Auto-generated method stub

			}

		});
		btnThmAccount.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnThmAccount.setBounds(645, 367, 127, 40);
		contentPane.add(btnThmAccount);

		JButton btndn = new JButton("Login");
		btndn.setForeground(Color.WHITE);
		btndn.setBackground(SystemColor.textHighlight);
		btndn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    try {
			        String url = "jdbc:mysql://localhost:3306/nhanvien";
			        String username = "root";
			        String password = "Woplaf22.";

			        Connection conn = DriverManager.getConnection(url, username, password);

			        String sql = "SELECT * FROM account WHERE USERNAME = ? AND PASS = ?";
			        PreparedStatement ps = conn.prepareStatement(sql);
			        ps.setString(1, txtdn.getText());
			        ps.setString(2, jfPass.getText());
			        ResultSet rs = ps.executeQuery();

			        if (txtdn.getText().equals("") || jfPass.getText().equals("")) {
			            JOptionPane.showMessageDialog(null, "Bạn chưa nhập username hoặc pass.");
			        } else if (rs.next()) {
			            int employeeID = rs.getInt("ID");
			            NhanVien nv = new NhanVien(employeeID);
			            nv.setVisible(true);
			            DangNhap.this.dispose();
			        } else {
			            JOptionPane.showMessageDialog(null, "Đăng nhập thất bại.");
			        }
			        rs.close();
			        ps.close();
			        conn.close();

			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});

		btndn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btndn.setBounds(449, 290, 127, 40);
		contentPane.add(btndn);

		JLabel left = new JLabel("I don't have a account");
		left.setFont(new Font("Tahoma", Font.BOLD, 15));
		left.setBounds(451, 368, 235, 39);
		contentPane.add(left);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 439, 449);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel company = new JLabel("T&Q COMPANY");
		company.setForeground(Color.WHITE);
		company.setFont(new Font("Times New Roman", Font.BOLD, 40));
		company.setBounds(50, 250, 317, 69);
		panel.add(company);

		JLabel lblNewLabel_2 = new JLabel("TQ");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(new Color(240, 240, 240));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("VNI-Jamai", Font.BOLD, 99));
		lblNewLabel_2.setBounds(92, 90, 232, 146);
		panel.add(lblNewLabel_2);

	}
}
