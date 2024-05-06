package DOAN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DangNhapAdmin extends JFrame {

	private JPanel contentPane;
	private JPasswordField jfPass;
	private JTextField txtdn;
	
	Connection conn;
	Statement stm;
	ResultSet rst;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhapAdmin frame = new DangNhapAdmin();
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
	public DangNhapAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 0, 439, 449);
		contentPane.add(panel);
		
		JLabel company = new JLabel("T&Q COMPANY");
		company.setForeground(Color.WHITE);
		company.setFont(new Font("Times New Roman", Font.BOLD, 40));
		company.setBounds(50, 250, 317, 69);
		panel.add(company);
		
		JLabel lblNewLabel_2_1 = new JLabel("TQ");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("VNI-Jamai", Font.BOLD, 99));
		lblNewLabel_2_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_2_1.setBounds(92, 90, 232, 146);
		panel.add(lblNewLabel_2_1);
		
		JButton btndn = new JButton("Login");
		btndn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//ket noi voi csdl
					String url = "jdbc:mysql://localhost:3306/dangnhap";
					String username = "root";
					String password = "Woplaf22.";
					
					Connection conn = DriverManager.getConnection(url, username, password);

					String sql = ("select * from dangnhap.account where USERNAME =? and PASS=?");
					PreparedStatement ps = conn.prepareCall(sql);
					ps.setString(1, txtdn.getText());
					ps.setString(2, jfPass.getText());
					rst = ps.executeQuery(); 

					
					if (txtdn.getText().equals("") || jfPass.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Bạn chưa nhập username hoặc pass.");

					} else if (rst.next()) {
						// JOptionPane.showMessageDialog(null, "Đăng nhập thành công.");
						TrangChuAdmin tcam = new TrangChuAdmin();
						tcam.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Đăng nhập thất bại.");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}	
		});
		btndn.setForeground(Color.WHITE);
		btndn.setFont(new Font("Tahoma", Font.BOLD, 20));
		btndn.setBackground(SystemColor.textHighlight);
		btndn.setBounds(455, 293, 127, 40);
		contentPane.add(btndn);
		
		jfPass = new JPasswordField();
		jfPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jfPass.setBounds(457, 221, 402, 40);
		contentPane.add(jfPass);
		
		JLabel lblPassWord = new JLabel("Password");
		lblPassWord.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassWord.setBounds(457, 184, 187, 39);
		contentPane.add(lblPassWord);
		
		txtdn = new JTextField();
		txtdn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtdn.setColumns(10);
		txtdn.setBounds(455, 134, 402, 40);
		contentPane.add(txtdn);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUserName.setBounds(457, 96, 187, 39);
		contentPane.add(lblUserName);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(SystemColor.textHighlight);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblLogin.setBackground(Color.WHITE);
		lblLogin.setBounds(489, 40, 334, 57);
		contentPane.add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("ADMIN");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("VNI-Bandit", Font.BOLD, 80));
		lblNewLabel.setBounds(566, 329, 293, 80);
		contentPane.add(lblNewLabel);
	}
}
