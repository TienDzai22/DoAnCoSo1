package DOAN;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LuaChon extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LuaChon frame = new LuaChon();
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
	public LuaChon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 600);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Welcome");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("VNI-Jamai", Font.BOLD, 80));
		title.setBounds(73, 32, 356, 68);
		contentPane.add(title);
		
		JLabel loginWith = new JLabel("Login with");
		loginWith.setForeground(Color.WHITE);
		loginWith.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		loginWith.setBounds(27, 278, 196, 89);
		contentPane.add(loginWith);
		
		JButton btnEmployee = new JButton("Employee");
		btnEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        DangNhap dangNhapFrame = new DangNhap();
		        dangNhapFrame.setVisible(true);
		        dispose(); // Close the current frame if needed
		    }
		});
		btnEmployee.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnEmployee.setBounds(233, 398, 224, 41);
		contentPane.add(btnEmployee);
		
		JButton btnAdmin = new JButton("Admin");
		btnAdmin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DangNhapAdmin dangNhapAdimFrame = new DangNhapAdmin();
		        dangNhapAdimFrame.setVisible(true);
		        dispose(); // Close the current frame if needed
		    }
		});
		btnAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAdmin.setBounds(233, 307, 224, 41);
		contentPane.add(btnAdmin);
		
		JLabel lblNewLabel = new JLabel("to");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("VNI-Jamai", Font.BOLD, 30));
		lblNewLabel.setBounds(228, 114, 47, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("T&Q Company");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("VNI-Jamai", Font.BOLD, 49));
		lblNewLabel_1.setBounds(73, 169, 356, 68);
		contentPane.add(lblNewLabel_1);
	}
}
