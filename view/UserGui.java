package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import controller.ManageReservation;
import controller.ManageRoom;
import controller.ManageUsers;
import controller.Utils;
import model.Reservation;
import model.Room;
import model.User;

public class UserGui {
	
	public UserGui() {
		
		JFrame frame1 = new JFrame("Receptioner");
		JPanel mainPanel = new JPanel();
		JPanel resPanel = new JPanel();
		JPanel newResPanel = new JPanel();
		
		frame1.setSize(1000,600);
	    //frame1.setVisible(true);
	    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    mainPanel.setLayout(new GridLayout(1, 2));
		
		frame1.setContentPane(mainPanel);
		
		resPanel.setLayout(new GridLayout(2, 1));
		resPanel.add(new JLabel("Reservations"));
		
		//TODO: add jtable
		final List<Reservation> allRes = ManageReservation.findAll();
		
		
		JTable tableRes = new JTable(new AbstractTableModel() {
	    	private String[] columnNames = new String[] {"client", "room", "dateStart", "nrDays", "price"};

			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return columnNames.length;
			}

			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return allRes.size();
			}

			@Override
			public Object getValueAt(int row, int col) {
				Reservation r = allRes.get(row);
				if(col == 0) {
					return r.getClient();
				}else if(col == 1) {
					return r.getRoom().getRoomNumber();
				}else if(col == 2) {
					return Utils.dateToString(r.getDateStart());
				}else if(col == 3) {
					return r.getNrDays();
				}else if(col == 4) {
					return r.getPrice();
				}
				return null;
			}
			
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
			
	    });
		
		tableRes.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					int row = tableRes.getSelectedRow();
					String[] buttons = {"Invoice", "Cancel reservation", "Back"};
					int returnValue = JOptionPane.showOptionDialog(null, "Reservation option", "Reservation option", 
							JOptionPane.DEFAULT_OPTION, 0, null, buttons, buttons[2]);
					if(returnValue == 0) {
						JOptionPane.showMessageDialog(null, allRes.get(row).buildInvoice(), 
								"Invoice", JOptionPane.INFORMATION_MESSAGE);
					}else if(returnValue == 1) {
						ManageReservation.delete(allRes.get(row).getId());
						allRes.clear();
						allRes.addAll(ManageReservation.findAll());
						tableRes.repaint();
					}
				}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
	    	
	    });
		resPanel.add(tableRes);
		
		
		newResPanel.setLayout(new GridLayout(7, 2));
		newResPanel.add(new JLabel("New reservation"));
		newResPanel.add(new JLabel(""));
		
		JTextField tfClient = new JTextField();
		JTextField tfDate = new JTextField(Utils.dateToString(new Date()));
		JTextField tfNrDays = new JTextField("1");
		JTextField tfRoom = new JTextField();
		JTextField tfPrice = new JTextField();
		
		newResPanel.add(new JLabel("Client"));
		newResPanel.add(tfClient);
		newResPanel.add(new JLabel("Date start"));
		newResPanel.add(tfDate);
		newResPanel.add(new JLabel("Nr. days"));
		newResPanel.add(tfNrDays);
		newResPanel.add(new JLabel("Room"));
		newResPanel.add(tfRoom);
		newResPanel.add(new JLabel("Price"));
		newResPanel.add(tfPrice);
		tfPrice.setEditable(false);
		
		JButton btnCalculatePrice= new JButton("Calculate Price");
		JButton btnReserve= new JButton("Reserve");
		btnReserve.setEnabled(false);
		
		newResPanel.add(btnCalculatePrice);
		newResPanel.add(btnReserve);
		
		tfClient.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
		});
		tfDate.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
		});
		tfNrDays.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
		});
		tfRoom.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				btnReserve.setEnabled(false);
				
			}
		});
		
		btnReserve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room room = ManageRoom.find(tfRoom.getText());
				if(room == null) {
					JOptionPane.showMessageDialog(null, "Invalid room number", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}else {
					int nrDays = Integer.valueOf(tfNrDays.getText());
					double price = Double.valueOf(tfPrice.getText());
					String client = tfClient.getText();
					Date dateStart = Utils.dateFromString(tfDate.getText());
					ManageReservation.insert(room.getId(), dateStart, nrDays, client, price);
					btnReserve.setEnabled(false);
					allRes.clear();
					allRes.addAll(ManageReservation.findAll());
					tableRes.repaint();
				}
				
			}
	    });
		
		btnCalculatePrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room room = ManageRoom.find(tfRoom.getText());
				if(room == null) {
					JOptionPane.showMessageDialog(null, "Invalid room number", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}else {
					int nrDays = Integer.valueOf(tfNrDays.getText());
					double price = room.getBasePrice() * nrDays;
					String client = tfClient.getText();
					int nrRes = ManageReservation.countByClient(client);
					if(nrRes > 3) {
						price = price * 0.9;
					}
					tfPrice.setText("" + price);
					btnReserve.setEnabled(true);
				}
				
			}
	    });
		
		
		
		mainPanel.add(resPanel);
		mainPanel.add(newResPanel);
	    frame1.setVisible(true);
	}

}
