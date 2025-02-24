import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


import layout.TableLayout;

@SuppressWarnings("serial")
public class GUI extends JFrame {	
	
	private final static String sortNames[]=
		{"Sort by name", "Sort by surname", "Sort by date of birth"};
    private final static double buttonSize[][]=
    	{{0.25,0.25,0.25,0.25},
 			{0.2,0.2,0.2,0.1,0.1,0.1,0.1}};
    
	private static JTextField dField = new JTextField();
	private static JTextField fField = new JTextField();
	private static JComboBox cBox = new JComboBox(sortNames);
	private static JTable listTable = new JTable(new ListSetTableModel()); 
	private static JScrollPane scrollPane = new JScrollPane(listTable);
	
	public GUI(){
		super();
		setTitle("Persons");
		setLocationRelativeTo(null); 
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	    //setSize(250, 450);
		setSize(250, 250);
	    setResizable(false);
        TableLayout layout=new TableLayout(buttonSize);
        setLayout(layout);
        listTable.setFillsViewportHeight(true);
        cBox.addActionListener(new RepaintAction());
        fField.addActionListener(new RepaintAndUpdateAction());
        dField.addActionListener(new RepaintAction());
        fField.setText("d:\\list.txt");
		add(scrollPane, "0 0 3 3");
		add(cBox,"0 5 3 5");
		add(fField,"0 6 3 6");
		add(dField,"0 4 3 4");
	}
	
	public static void repaintAndUpdate()
	{
		ListSetTableModel.update();
		scrollPane.repaint();
	}
	
	public static void repaintOnly()
	{
		scrollPane.repaint();
	}
	
	public static String getFilePath()
	{
		return fField.getText();
	}
	
	public static String getDate()
	{
		return dField.getText();
	}
	
	public static int getSortType()
	{
		return cBox.getSelectedIndex()+1;
	}
}
