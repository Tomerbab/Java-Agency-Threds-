import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.awt.Toolkit;
//import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MyGui extends JFrame {

	private JPanel contentPane;
	private JTextField txtWelcomeToThe;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyGui frame = new MyGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//	public void close() {
//		WindowEvent closeWindow = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
//		Toolkit
//	}

	/**
	 * Create the frame.
	 */
	public MyGui() {
		setForeground(new Color(0, 0, 0));
		setAlwaysOnTop(true);
		setTitle("Welcome to the Agency System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JFrame frame = new JFrame();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel5 = new JLabel("Operators Working Time:");
		lblNewLabel5.setBounds(10, 35, 200, 26); 
		contentPane.add(lblNewLabel5);
		
		JLabel lblNewLabel_1 = new JLabel("Number of Executives:");
		lblNewLabel_1.setBounds(10, 86, 200, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Number of Operations for the day:");
		lblNewLabel_2.setBounds(10, 138, 220, 26);
		contentPane.add(lblNewLabel_2);
		
		JSpinner spinnerOpT = new JSpinner();
		spinnerOpT.setModel(new SpinnerNumberModel(new Double(1), null, null, new Double(1)));
		spinnerOpT.setBounds(315, 38, 30, 20);
		contentPane.add(spinnerOpT);
		
		JSpinner spinnerEx = new JSpinner();
		spinnerEx.setBounds(315, 89, 30, 20);
		contentPane.add(spinnerEx);
		
		JSpinner spinnerOp = new JSpinner();
		spinnerOp.setModel(new SpinnerNumberModel(new Integer(10), null, null, new Integer(1)));
		spinnerOp.setBounds(315, 141, 30, 20);
		contentPane.add(spinnerOp);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double operationTime = (double)spinnerOpT.getValue();
				int numOfExecutives = (int)spinnerEx.getValue();
				int numOfOperations = (int)spinnerOp.getValue();
				if(numOfExecutives > 8) {
					JOptionPane.showMessageDialog(null,"Invalid Input! The number of executives has been updated.","Error",JOptionPane.ERROR_MESSAGE);
					numOfExecutives = 8;	
				}
				Agency a = new Agency();
				a.startDay("\\C:\\Users\\tomerbab\\Desktop\\calls.txt", numOfOperations, numOfExecutives,operationTime);
				//a.startDay("\\Users\\Moshe\\OneDrive\\Desktop\\calls.txt",numOfOperations,numOfExecutives);	
			}
		});
		btnNewButton.setBounds(71, 231, 91, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(277, 231, 91, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Welcome to FCBarcelona Agency");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(91, 11, 239, 20);
		contentPane.add(lblNewLabel);
		
	}
}
