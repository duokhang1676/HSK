package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.ColorConsts;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Panel;
import java.awt.Label;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Dimension;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtPassword;
	private JButton btnSignUp;
	
	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("Login\r\n");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnLogin = new JButton("Login\r\n");
		btnLogin.setBackground(new Color(3, 120, 154));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBounds(383, 376, 170, 45);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (true) {
					setVisible(false);
					new RootFrame().setVisible(true);
				}
			}
		});
		contentPane.setBackground(Color.decode(ColorConsts.ForegroundColor));
		contentPane.setLayout(null);
		contentPane.add(btnLogin);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtUserName.setToolTipText("");
		txtUserName.setForeground(new Color(0, 0, 0));
		txtUserName.setBounds(383, 244, 269, 44);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		txtPassword = new JTextField();
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtPassword.setForeground(new Color(0, 0, 0));
		txtPassword.setColumns(10);
		txtPassword.setBounds(383, 322, 269, 44);
		contentPane.add(txtPassword);
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.setForeground(new Color(255, 255, 255));
		btnSignUp.setBackground(new Color(3, 120, 154));
		btnSignUp.setBounds(383, 434, 170, 45);
		contentPane.add(btnSignUp);
		
		Panel colorPanel = new Panel();
		colorPanel.setBackground(Color.decode(ColorConsts.PrimaryColor));
		colorPanel.setBounds(10, 10, 342, 643);
		contentPane.add(colorPanel);
		colorPanel.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Welcome!!!");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblWelcome.setBounds(70, 214, 189, 69);
		colorPanel.add(lblWelcome);
		
		JLabel lblUserIcon = new JLabel("");
		lblUserIcon.setIcon(new ImageIcon("icon\\ic_userIcon.png"));
		lblUserIcon.setBounds(113, 129, 98, 104);
		colorPanel.add(lblUserIcon);
		
		JLabel lbUsername = new JLabel("User name");
		lbUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbUsername.setSize(new Dimension(9, 0));
		lbUsername.setBounds(383, 204, 101, 40);
		contentPane.add(lbUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setSize(new Dimension(9, 0));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(383, 284, 101, 40);
		contentPane.add(lblPassword);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("img\\img_logoImg.png"));
		lblLogo.setBounds(383, 61, 269, 133);
		contentPane.add(lblLogo);
	}
}
