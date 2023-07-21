import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;






public class FlashCardBuilder extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private JTextArea question;
	private JTextArea answer;
	private ArrayList<FlashCard> cardList;
	
	private JFrame frame;
	private JLabel fontLabel;
//	public JSpinner fontSizeSpinner;
	private JButton fontColorButton;
	private JComboBox fontBox;

	
	
	public FlashCardBuilder() {
		
		
		//Interface for user
		frame = new JFrame("Flash Card");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setLayout(new FlowLayout());
		frame.setLocationRelativeTo(null);
		
		
		//Creating JPanel
		JPanel mainPanel = new JPanel();
		
		//Creating font
		Font greatFont = new Font("Helvetica Neue", Font.BOLD, 21);
		question = new JTextArea(6,20);
		question.setLineWrap(true);
		question.setWrapStyleWord(true);
		question.setFont(greatFont);
		
		//Question
		JScrollPane qJScrollPane = new JScrollPane(question);
		qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		//Answer
		answer = new JTextArea(6, 20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(greatFont);
		
		//JscrollPane
		JScrollPane aJScrollPane = new JScrollPane(answer);
		aJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		aJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		

		
		JButton nextButton = new JButton("Next Card");
		cardList = new ArrayList<FlashCard>();
		
		
		fontColorButton = new JButton("Color");
		fontColorButton.addActionListener(this);
		  
		  String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		  
		fontBox = new JComboBox(fonts);
		fontBox.addActionListener((ActionListener) this);
		fontBox.setSelectedItem("Arial");
		  
		
		
		
		//Labels
		JLabel qJLabel = new JLabel("Question");
//		qJLabel.setHorizontalAlignment(SwingConstants.CENTER); 
//        qJLabel.setVerticalAlignment(SwingConstants.CENTER);
//        this.setSize(100,100);
     
        
        JLabel aJLabel = new JLabel("Answer");
//        aJLabel.setHorizontalAlignment(SwingConstants.CENTER); 
//        aJLabel.setVerticalAlignment(SwingConstants.CENTER);
		
		JLabel fontLabel = new JLabel("Font: ");
		
		
		
		
		
		//Adding components to main panel
		mainPanel.add(qJLabel);
		mainPanel.add(qJScrollPane);
		mainPanel.add(aJLabel);
		mainPanel.add(aJScrollPane);
		mainPanel.add(nextButton);
	
		nextButton.addActionListener(new NextCard());
		
		//MenuBar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem newMenuItem = new JMenuItem("New");
		  
		JMenuItem saveMenuItem = new JMenuItem("Save"); 
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		
		menuBar.add(fileMenu);
		menuBar.add(fontLabel);
		menuBar.add(fontBox);
		menuBar.add(fontColorButton);
		
		
		
		
		

		
//		menuBar.add(fontLabel);
//		menuBar.add(fontSizeSpinner);
//		menuBar.add(fontColorButton);
//		menuBar.add(fontBox);
		

		
		
//		fontSizeSpinner = new JSpinner();
//		fontSizeSpinner.setPreferredSize(new Dimension(50,25));
//		fontSizeSpinner.setValue(20);
//		fontSizeSpinner.addChangeListener(new ChangeListener() {
//
//			   @Override
//			   public void stateChanged(ChangeEvent e) {
//			    
//			    answer.setFont(new Font(question.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue()));
//			    question.setFont(new Font(question.getFont().getFamily(),Font.PLAIN,(int) fontSizeSpinner.getValue()));
//			   }
			
//		});
	
		
		//EventListeners
		newMenuItem.addActionListener(new NewMenuItem());
		saveMenuItem.addActionListener(new SaveMenu());
		
		
		frame.setJMenuBar(menuBar);
		
		
		//Add to frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 600);
		frame.setVisible(true);
		
		
		
	}	  
		  
		  
	



	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new FlashCardBuilder();
				
			}
			
			
		});

	
	}
	
	class NextCard implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//creating flashCard
			FlashCard card = new FlashCard(question.getText(), answer.getText());
			cardList.add(card);
			clearCard();
		}

	}
	

	


	class NewMenuItem implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("New Menu Clicked");
	
		}
	
	}
	
	class SaveMenu implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			FlashCard card = new FlashCard(question.getText(), answer.getText());
			cardList.add(card);
			
			//Creating file dialog with file chooser
			JFileChooser fileSave = new JFileChooser();
			fileSave.showSaveDialog(frame);
			saveFile(fileSave.getSelectedFile());
			
			
			
			
		}

		
	}
	
	private void clearCard() {
		question.setText("");
		answer.setText("");
		question.requestFocus();
		
		}
	private void saveFile(File selectedFile) {
		
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
			
			Iterator<FlashCard> cardIterator = cardList.iterator();
			while (cardIterator.hasNext()) {
				FlashCard card = (FlashCard)cardIterator.next();
				writer.write(card.getQuestion() + "/");
				writer.write(card.getAnswer() + "\n");
				
			}
			
			writer.close();
				
				
			
			
			
		} catch (Exception e) {
			System.out.println("Couldn't write to file");
			e.printStackTrace();
		}
		
	}	
	
	
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==fontColorButton) {
			JColorChooser colorChooser = new JColorChooser();
				   
			Color color = colorChooser.showDialog(null, "Choose a color", Color.black);
				   
			question.setForeground(color);
			answer.setForeground(color);
			
		}
				  
		if(e.getSource()==fontBox) {
			question.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,question.getFont().getSize()));
			answer.setFont(new Font((String)fontBox.getSelectedItem(),Font.PLAIN,question.getFont().getSize()));
		}
		
	}
	
	
	
		
}	
