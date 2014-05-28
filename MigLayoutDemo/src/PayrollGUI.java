

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import net.miginfocom.swing.MigLayout;


public class PayrollGUI {

	private JFrame frame;
	
	private JPanel panel;
	
	private JButton close;
	private JButton calculateCheck;
	private JButton saveCheck;
	
	private Font font;


	
	public PayrollGUI () {
		
		frame = new JFrame ();
		frame.setLayout (new MigLayout ());
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

		panel = new JPanel ();
		
		font = new Font ("Courier", Font.BOLD, 14);
		
		close = new JButton ("Close");
		close.setFont (font);
		calculateCheck = new JButton ("Calculate Check");
		calculateCheck.setFont (font);
		saveCheck = new JButton ("Save Check");		
		saveCheck.setFont (font);
		
		panel.setLayout (new MigLayout ());

		panel.add (new PayCheckInfo (), "wrap");
		panel.add (new IncomeInfo ());
		panel.add (new TaxInfo (), "wrap, cell 0 1");
		panel.add (calculateCheck, "split 3, gaptop 15px");
		panel.add (saveCheck, "gapbefore 20px");
		panel.add (close, "gapbefore 20px");

		frame.add (panel);
		frame.pack ();
		frame.setLocationRelativeTo (null);
		frame.setVisible (true);

	}

	
	
	@SuppressWarnings ("serial")
	private class IncomeInfo extends JPanel {

		private JLabel lblIncome;
		private JLabel lblRates;
		private JLabel lblHours;
		private JLabel lblAmount;
		private JLabel lblYTD;
		private JLabel lblSalary;
		private JLabel lblHourlyRate;
		private JLabel lblOvertime;
		private JLabel lblVacation;
		private JLabel lblBonus;
		private JLabel lblDeductions;
		private JLabel lblDeductionsAMT;
		private JLabel lblDeductionsYTD;
		private JLabel lblMedical;
		private JLabel lbl401k;
		private JLabel lblBonusAmount;
		private JLabel lblBonusYTD;
		private JLabel lblMedicalAMT;
		private JLabel lblMedicalYTD;
		private JLabel lbl401AMT;
		private JLabel lbl401YTD;
		private JLabel lblVacationAmount;
		private JLabel lblVacationYTD;
		private JLabel lblHourlyAmount;
		private JLabel lblHourlyYTD;
		private JLabel lblOvertimeAmount;
		private JLabel lblOvertimeYTD;
		private JLabel lblSalaryAmount;
		private JLabel lblSalaryYTD;
		
		private JTextField tfSalaryRate;
		private JTextField tfSalaryHours;
		private JTextField tfOvertimeRate;
		private JTextField tfOvertimeHours;
		private JTextField tfHourlyRate;
		private JTextField tfHourlyHours;
		private JTextField tfVacationRate;
		private JTextField tfVacationHours;
		private JTextField tfBonusRate;
		private JTextField tfBonusHours;
		
		private IncomeInfo () {
			
			setLayout (new MigLayout ("", "[]25[]"));

			setBackground (Color.WHITE);
			setBorder (BorderFactory.createTitledBorder 
					(BorderFactory.createEtchedBorder (Color.BLACK, Color.GRAY),
					"Income and Deductions"));
			initComponents ();

		}

		
		
		private void initComponents () {

			lblIncome = new JLabel ("Incomes");
			lblRates = new JLabel ("Rates");
			lblHours = new JLabel ("Hours");
			lblAmount = new JLabel ("Amount");
			lblYTD = new JLabel ("YTD");
			lblSalary = new JLabel ("Salary: ");
			lblHourlyRate = new JLabel ("Hourly: ");
			lblOvertime = new JLabel ("Overtime Hourly: ");
			lblVacation = new JLabel ("Vacation: ");
			lblBonus = new JLabel ("Bonus: ");
			lblDeductions = new JLabel ("Deductions");
			lblDeductionsAMT = new JLabel ("Amount");
			lblDeductionsYTD = new JLabel ("YTD");
			lblMedical = new JLabel ("Medical: ");
			lbl401k = new JLabel ("401k: ");
			lblMedicalAMT = new JLabel ("$0.00");
			lblMedicalYTD = new JLabel ("$0.00");
			lbl401AMT = new JLabel ("$0.00");
			lbl401YTD = new JLabel ("$0.00");
			lblSalaryAmount = new JLabel ("$0.00");
			lblSalaryYTD = new JLabel ("$0.00");
			lblOvertimeAmount = new JLabel ("$0.00");
			lblOvertimeYTD = new JLabel ("$0.00");
			lblHourlyAmount = new JLabel ("$0.00");
			lblHourlyYTD = new JLabel ("$0.00");
			lblVacationAmount = new JLabel ("$0.00");
			lblVacationYTD = new JLabel ("$0.00");
			lblBonusAmount = new JLabel ("$0.00");
			lblBonusYTD = new JLabel ("$0.00");
			
			tfSalaryRate = new JTextField ();
			tfSalaryHours = new JTextField ();
			tfOvertimeRate = new JTextField ();
			tfOvertimeHours = new JTextField ();
			tfHourlyRate = new JTextField ();
			tfHourlyHours = new JTextField ();
			tfVacationRate = new JTextField ();
			tfVacationHours = new JTextField ();
			tfBonusRate = new JTextField ();
			tfBonusHours = new JTextField ();

			font = new Font ("Courier", Font.BOLD, 14);
			
			lblSalary.setFont (font);
			lblHourlyRate.setFont (font);
			lblOvertime.setFont (font);
			lblVacation.setFont (font);
			lblBonus.setFont (font);
			lblIncome.setFont (font);
			lblRates.setFont (font);
			lblHours.setFont (font);
			lblAmount.setFont (font);
			lblYTD.setFont (font);
			lblMedicalAMT.setFont (font);
			lblMedicalYTD.setFont (font);
			lbl401AMT.setFont (font);
			lbl401YTD.setFont (font);
			lblSalaryAmount.setFont (font);
			lblSalaryYTD.setFont (font);
			lblOvertimeAmount.setFont (font);
			lblOvertimeYTD.setFont (font);
			lblHourlyAmount.setFont (font);
			lblHourlyYTD.setFont (font);
			lblVacationAmount.setFont (font);
			lblVacationYTD.setFont (font);
			lblBonusAmount.setFont (font);
			lblBonusYTD.setFont (font);
			
			
			add (lblIncome);
			add (lblRates);
			add (lblHours);
			add (lblAmount);
			add (lblYTD, "wrap");

			add (lblSalary);
			tfSalaryRate.setColumns (5);
			add (tfSalaryRate);
			tfSalaryHours.setColumns (5);
			add (tfSalaryHours);
			add (lblSalaryAmount);
			add (lblSalaryYTD, "wrap");

			add (lblHourlyRate);
			tfHourlyRate.setColumns (5);
			add (tfHourlyRate);
			tfHourlyHours.setColumns (5);
			add (tfHourlyHours);
			add (lblHourlyAmount);
			add (lblHourlyYTD, "wrap");

			add (lblOvertime);
			tfOvertimeRate.setColumns (5);
			add (tfOvertimeRate);
			tfOvertimeHours.setColumns (5);
			add (tfOvertimeHours);
			add (lblOvertimeAmount);
			add (lblOvertimeYTD, "wrap");

			add (lblVacation);
			tfVacationRate.setColumns (5);
			add (tfVacationRate);
			tfVacationHours.setColumns (5);
			add (tfVacationHours);
			add (lblVacationAmount);
			add (lblVacationYTD, "wrap");

			add (lblBonus);
			tfBonusRate.setColumns (5);
			add (tfBonusRate);
			tfBonusHours.setColumns (5);
			add (tfBonusHours);
			add (lblBonusAmount);
			add (lblBonusYTD, "wrap");

			add (new JLabel (), "wrap 20px");
			add (lblDeductions);
			add (lblDeductionsAMT);
			add (lblDeductionsYTD, "wrap");

			add (lblMedical);
			add (lblMedicalAMT);
			add (lblMedicalYTD, "wrap");

			add (lbl401k);
			add (lbl401AMT);
			add (lbl401YTD, "wrap");

		}

	}

	
	
	@SuppressWarnings ("serial")
	private class PayCheckInfo extends JPanel {

		private JLabel lblEmployeeName;
		private JLabel lblSSN;
		private JLabel lblCheckNumber;
		private JLabel lblPayStart;
		private JLabel lblPayDate;
		private JLabel lblPayEnd;
		private JLabel lblSSNNum;
		
		private JComboBox cbEmployeeName;
		private JComboBox cbPayStart;
		private JComboBox cbPayDate;
		private JComboBox cbPayEnd;
		
		private JTextField tfCheckNum;	
		
		
		
		private PayCheckInfo () {

			setLayout (new MigLayout ("", "[]25[]"));
			setBackground (Color.WHITE);
			setBorder (BorderFactory.createTitledBorder
					(BorderFactory.createEtchedBorder (Color.GRAY, Color.BLACK),
					"Pay Check Information"));
			
			initComponents ();
		}
		
		
		
		private void initComponents (){

			font = new Font ("Courier", Font.BOLD, 14);
			
			lblEmployeeName = new JLabel ("Employee Name: ");
			lblSSN = new JLabel ("SSN: ");
			lblCheckNumber = new JLabel ("Check Number: ");
			lblPayStart = new JLabel ("Pay Start Date: ");
			lblPayDate = new JLabel ("Pay Date: ");
			lblPayEnd = new JLabel ("Pay End Date: ");
			lblSSNNum = new JLabel ("This is ssn");	
			
			
			lblEmployeeName.setFont (font);
			lblSSN.setFont (font);
			lblCheckNumber.setFont (font);
			lblPayStart.setFont (font);
			lblPayDate.setFont (font);
			lblPayEnd.setFont (font);
			lblSSNNum.setFont (font);
			
			
			cbEmployeeName = new JComboBox ();
			cbEmployeeName.setPreferredSize (new Dimension (150, 10));
			
			cbPayStart = new JComboBox ();
			cbPayStart.setPreferredSize (new Dimension (125, 10));
			
			cbPayDate = new JComboBox ();
			cbPayDate.setPreferredSize (new Dimension (125, 10));
			
			cbPayEnd = new JComboBox ();
			cbPayEnd.setPreferredSize (new Dimension (125, 10));
			
			tfCheckNum = new JTextField ();
			tfCheckNum.setColumns (11);
			
			add (lblEmployeeName);
			add (cbEmployeeName);
			add (lblCheckNumber);
			add (tfCheckNum);
			add (lblPayDate);
			add (cbPayDate, "wrap");
			
			add (lblSSN);
			add (lblSSNNum);
			
			add (lblPayStart);
			add (cbPayStart);
			add (lblPayEnd);
			add (cbPayEnd);
		}
				
	}
	
	
	
	@SuppressWarnings ("serial")
	private class TaxInfo extends JPanel {
		
		private JLabel lblTaxDed;
		private JLabel lblAMT;
		private JLabel lblYTD;
		private JLabel lblSS;
		private JLabel lblFed;
		private JLabel lblMedi;
		private JLabel lblState;
		private JLabel lblSSAMT;
		private JLabel lblSSYTD;
		private JLabel lblFedAMT;
		private JLabel lblFedYTD;
		private JLabel lblMediAMT;
		private JLabel lblMediYTD;
		private JLabel lblStateAMT;
		private JLabel lblStateYTD;
		
		
		
		private TaxInfo () {
			
			setLayout (new MigLayout ("width 427, height 246", "[]25[]"));

			setBackground (Color.WHITE);
			setBorder (BorderFactory.createTitledBorder
					(BorderFactory.createEtchedBorder (Color.BLACK, Color.GRAY),
					"Tax Deductions"));
			
			initComponents ();
		}
		
		
		
		private void initComponents (){

			lblTaxDed = new JLabel ("Tax Deductions");
			lblAMT = new JLabel ("AMT");
			lblYTD = new JLabel ("YTD");
			lblSS = new JLabel ("Employee Social Security");
			lblFed = new JLabel ("Federal Tax");
			lblMedi = new JLabel ("Emplyeee Medicare");
			lblState = new JLabel ("State Tax");
			lblSSAMT = new JLabel ("$0.00");
			lblSSYTD = new JLabel ("$0.00");
			lblFedAMT = new JLabel ("$0.00");
			lblFedYTD = new JLabel ("$0.00");
			lblMediAMT= new JLabel ("$0.00");
			lblMediYTD = new JLabel ("$0.00");
			lblStateAMT = new JLabel ("$0.00");
			lblStateYTD = new JLabel ("$0.00");
			
			font = new Font ("Courier", Font.BOLD, 14);
			
			lblTaxDed.setFont (font);
			lblAMT.setFont (font);
			lblYTD.setFont (font);
			lblSS.setFont (font);
			lblFed.setFont (font);
			lblMedi.setFont (font);
			lblState.setFont (font);
			lblSSAMT.setFont (font);
			lblSSYTD.setFont (font);
			lblFedAMT.setFont (font);
			lblFedYTD.setFont (font);
			lblMediAMT.setFont (font);
			lblMediYTD.setFont (font);
			lblStateAMT.setFont (font);
			lblStateYTD.setFont (font);			
			
			add (lblTaxDed);
			add (lblAMT);
			add (lblYTD, "wrap");
			add (lblSS);
			add (lblSSAMT);
			add (lblSSYTD, "wrap");
			add (lblFed);
			add (lblFedAMT);
			add (lblFedYTD, "wrap");
			add (lblMedi);
			add (lblMediAMT);
			add (lblMediYTD, "wrap");
			add (lblState);
			add (lblStateAMT);
			add (lblStateYTD, "wrap");
			
		}
		
	}
	
	
	
	public static void main (String[] args){
		
		 SwingUtilities.invokeLater (new Runnable () {
		      public void run () {
		        new PayrollGUI ();
		      }
		    });
		  }

	}

