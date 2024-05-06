package DOAN;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class DangKi extends JFrame {

	private JPanel contentPane;
	private JTextField txtUser;
	private JTextField txtGmail;
	private JPasswordField txtPass;
	private JPasswordField txtComfirm;

	String url = "jdbc:mysql://localhost:3306/nhanvien";
	String username = "root";
	String password = "Woplaf22.";
	Statement stm;
	ResultSet rst;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangKi frame = new DangKi();
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
	public DangKi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 486);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUser.setBounds(643, 102, 216, 28);
		contentPane.add(txtUser);
		txtUser.setColumns(10);

		txtGmail = new JTextField();
		txtGmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtGmail.setColumns(10);
		txtGmail.setBounds(643, 160, 216, 28);
		contentPane.add(txtGmail);

		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPass.setBounds(643, 218, 216, 28);
		contentPane.add(txtPass);

		txtComfirm = new JPasswordField();
		txtComfirm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtComfirm.setBounds(643, 276, 216, 28);
		contentPane.add(txtComfirm);

		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(456, 102, 164, 28);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Gmail");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(456, 160, 106, 28);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Mật khẩu");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(456, 218, 106, 28);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(456, 276, 177, 28);
		contentPane.add(lblNewLabel_1_3);

		JLabel lblNewLabel_1_3_1 = new JLabel("SIGN UP");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel_1_3_1.setBounds(531, 38, 263, 48);
		contentPane.add(lblNewLabel_1_3_1);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 255));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DangNhap  dn = new DangNhap();
				dn.setVisible(true);		//hiển thị manHinhDangNhap
				this.dispose();		//thoát khỏi manHinhDangNhap
			}

			private void dispose() {
				// TODO Auto-generated method stub
				
			}

		});
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnLogin.setBounds(602, 389, 78, 21);
		contentPane.add(btnLogin);

		JButton btnDangKi = new JButton("Sign up");
        btnDangKi.setForeground(new Color(255, 255, 255));
        btnDangKi.setBackground(new Color(50, 205, 50));
        btnDangKi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int dk = JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng kí ", "Confirm",
                        JOptionPane.YES_NO_OPTION);
                if (dk != JOptionPane.YES_OPTION) {
                    return;
                }
                // Truy vấn vào csdl
                try {
                    Connection conn = DriverManager.getConnection(url, username, password);
                    String sql = "INSERT INTO account (USERNAME, ID, PASS, CONFIRM) VALUES (?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    // Thêm trường ID vào insert dữ liệu
                    // Đoạn code sau là giả định, bạn cần thay thế nó bằng logic lấy ID phù hợp
                    ps.setInt(2, 1); // Giả sử ID là 1
                    ps.setString(1, txtUser.getText());
                    ps.setString(3, txtPass.getText());
                    ps.setString(4, txtComfirm.getText());
                    int n = ps.executeUpdate();
                    if (txtUser.getText().equals("") || txtPass.getText().equals("") || txtComfirm.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Không để thông tin trống");
                    } else if (n != 0) {
                        JOptionPane.showMessageDialog(null, "Đăng kí thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Đăng kí thất bại");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
		btnDangKi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDangKi.setBounds(456, 331, 149, 35);
		contentPane.add(btnDangKi);
		
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
		
		JLabel lblNewLabel = new JLabel("I have an account");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(456, 383, 149, 35);
		contentPane.add(lblNewLabel);
	}
}
