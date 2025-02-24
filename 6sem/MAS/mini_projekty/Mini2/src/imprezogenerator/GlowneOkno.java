package imprezogenerator;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import imprezogenerator.TableModelImpreza;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
 
class GlowneOkno extends JFrame
{
	public static JFrame frame;
	private static final long serialVersionUID = 1L;
	
	JTextArea output,organ,impra;
	final JTable table = new JTable();
	final JTable tableOrganizatorzy = new JTable();
	TableModelImpreza tablemodelimpreza = null;
	TableModelOrganizator tablemodelorganizator = null;
	JScrollPane scrollPane = new JScrollPane(table);
	JScrollPane organizatorzy = new JScrollPane(tableOrganizatorzy);
    JButton przydziel_but,wroc_but;
    JPanel przyciski;
    Container cp;
    
//konstruktor ----------------//
    
	public GlowneOkno()
	{

	}
	
//tworzy menu w oknie-------------//	
	
	public JMenuBar createMenuBar() 
	{
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem,wroc;
        
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menuItem = new JMenuItem("Przydziel organizatora do imprezy");
        
        wroc = new JMenuItem("Glowne menu");
        
        menuBar.add(menu);
        menu.add(wroc);
        menu.add(menuItem);
        
//akcja po naciœniêciu przycisku w menu "Przydziel org do imp" w menu-------- // 
        
        menuItem.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						
							frame.setVisible(false);
					        frame = new JFrame("Imprezogenerator2");
					        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					        
					        GlowneOkno okno = new GlowneOkno();
					        
					        frame.setJMenuBar(okno.createMenuBar());
					        frame.setContentPane(okno.createContentPane2());
					        frame.setSize(700, 500);
					        frame.setVisible(true);
					        frame.setResizable(false);
					}
				}
		);
        
        return menuBar;
    }
	
//tworzy panel do 1 okna  ----------------------------------//	
	
	public Container createContentPane() 
	{
		JPanel contentPane = new JPanel(new FlowLayout());
		
	    contentPane.setOpaque(true);
	    organ = new JTextArea("WITAMY W PROGRAMIE", 18, 20);
	
	    contentPane.add(organ);
	
	    return contentPane;
    }
	
//tworzy panel do 2 okna  ----------------------------------//	
	
	public Container createContentPane2() 
	{
		JPanel contentPane = new JPanel(new GridLayout(2,1));
        contentPane.setOpaque(true);
    
        
		tablemodelimpreza = new TableModelImpreza(Baza.getImprezy());
		table.setModel(tablemodelimpreza);
		table.setFocusable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setRowHeight(20);

		tablemodelorganizator = new TableModelOrganizator(Baza.getOrganizator());
		tableOrganizatorzy.setModel(tablemodelorganizator);
		tableOrganizatorzy.setFocusable(false);
		tableOrganizatorzy.getTableHeader().setReorderingAllowed(false);
		tableOrganizatorzy.getTableHeader().setResizingAllowed(false);
		tableOrganizatorzy.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		tableOrganizatorzy.setRowHeight(20);
		tableOrganizatorzy.setBackground(Color.LIGHT_GRAY);
		scrollPane.getViewport().setBackground(Color.WHITE);
		organizatorzy.getViewport().setBackground(Color.LIGHT_GRAY);
		przyciski = new JPanel(new FlowLayout());

	     impra = new JTextArea("Imprezy", 18, 20);
	     przydziel_but = new JButton("Przydziel");
	
//przypisywanie organizatora do wybranej imprezy =================================================//
	     
	     przydziel_but.addActionListener(
					new ActionListener()
					{
						public void actionPerformed(ActionEvent e) 
						{
							if(table.getSelectedRow() < 0)
								JOptionPane.showMessageDialog(null, "MUSISZ WYBRAÆ JAK¥Œ IMPREZE", "B£¥D", 0);
							else
							{
								if(tableOrganizatorzy.getSelectedRow() < 0)
									JOptionPane.showMessageDialog(null, "MUSISZ WYBRAÆ JAKIEGOS ORGANIZATORA", "B£¥D", 0);
								else
								{
									Impreza impreza = Baza.getImpreza(Integer.parseInt("" + table.getValueAt(table.getSelectedRow(), 0)));
									impreza.setID_ORGANIZATOR(Integer.parseInt("" + tableOrganizatorzy.getValueAt(tableOrganizatorzy.getSelectedRow(), 0)));
									
									Baza.dodajImpreze(impreza);
									
									System.out.println("Po klikniêciu przydziel "+table.getValueAt(table.getSelectedRow(), 0));
									System.out.println("Po klikniêciu przydziel but "+tableOrganizatorzy.getValueAt(tableOrganizatorzy.getSelectedRow(), 0));
								}
							}
						}
					}
			);
	     
	    przyciski.add(przydziel_but); 
        contentPane.add(scrollPane);
        contentPane.add(organizatorzy);
        contentPane.add(przyciski);
        return contentPane;
    }

//Poka¿ GUI  ----------------------------------//
	
	static void createAndShowGUI() {
        frame = new JFrame("Imprezogenerator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GlowneOkno okno = new GlowneOkno();
        
        frame.setJMenuBar(okno.createMenuBar());
        frame.setContentPane(okno.createContentPane());

        frame.setSize(600, 400);
        frame.setVisible(true);
    }
	
//MAIN wywo³anie GUI ----------------------------------//	
	
	public static void main(String[] args) 
	{
        javax.swing.SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
            }
        });
    }
	
}
    

    