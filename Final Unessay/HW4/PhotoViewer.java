package HW4;
/**
 * Homework 4
 * Gaurav Ravichandran, gtr5ew
 *
 * Sources: Lectures, Big Java Book, Stack Overflow, Java/Oracle API, KodeJava
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;

public class PhotoViewer extends JFrame {
	//fields
	/**
	 * Library of Photos used in the GUI
	 */
	private PhotoContainer imageLibrary;
	/**
	 * Title (filename) of the Photo
	 */
	private JLabel photoTitle;
	/**
	 * Image being displayed currently
	 */
	private JLabel image;
	/**
	 * Caption of the Image
	 */
	private JLabel caption;
	/**
	 * Radio Button for rating of 1
	 */
	private JRadioButton one;
	/**
	 * Radio Button for rating of 2
	 */
	private JRadioButton two;
	/**
	 * Radio Button for rating of 3
	 */
	private JRadioButton three;
	/**
	 * Radio Button for rating of 4
	 */
	private JRadioButton four;
	/**
	 * Radio Button for rating of 5
	 */
	private JRadioButton five;
	/**
	 * Group of RadioButtons
	 */
	private ButtonGroup radGroup;
	/**
	 * Button to cycle to the next Photo
	 */
	private JButton next;
	/**
	 * Button to cycle to the previous Photo
	 */
	private JButton previous;
	/**
	 * Layout style of the various panels in the GUI
	 */
	private FlowLayout layout = new FlowLayout();
	/**
	 * Index of the currently displayed Photo in array of photos
	 * initialized to the index of the first Photo
	 */
	public int disIndex = 0;
	
	//Directory (Pathway)
	public static String dir = "images//";
	
	//Photos
	private Photo p1;
	private Photo p2;
	private Photo p3;
	private Photo p4;
	private Photo p5;
	
	//Constructor (Loads imageData into Photos and then loads Photos into imageLibrary)
	public PhotoViewer() {
		//Instantiate the Photos
		p1 = new Photo("DRose.jpg", "Layup", "2011-11-14", 2);
		p2 = new Photo("Kobe.jpg", "360", "2008-01-28", 5);
		p3 = new Photo("LBJ.jpg", "Steal and throw-down", "2014-03-30", 2);
		p4 = new Photo("MJ.jpg", "The GOAT", "1996-05-13", 5);
		p5 = new Photo("VC.jpg", "Greatest Dunker", "1985-04-21", 4);
		
		//Load the imageData into the Photos
		p1.loadImageData(dir + p1.getFilename());
		p2.loadImageData(dir + p2.getFilename());
		p3.loadImageData(dir + p3.getFilename());
		p4.loadImageData(dir + p4.getFilename());
		p5.loadImageData(dir + p5.getFilename());
		
		//Instantiate the imageLibrary
		imageLibrary = new Library("Basketball Moments", 1);
		
		Photo[] photos_list = {p1, p2, p3, p4, p5};
		
		//Add Photos to the imageLibrary
		for (Photo p : photos_list) {
			imageLibrary.addPhoto(p);
		}
	}
	
	//Main Method (runs and opens the GUI)
	public static void main (String[] args) {
		//Instantiate a PhotoViewer with pre-loaded Photos
		PhotoViewer myViewer = new PhotoViewer();
		
		//Runs the GUI
		javax.swing.SwingUtilities.invokeLater(() -> myViewer.createAndShowGUI());
	}
	
	//getters
	/**
	 * Returns the PhotoContainer
	 * @param - no parameters needed
	 * @return - the PhotoContainer associated with the GUI
	 */
	public PhotoContainer getImageLibrary() {
		return imageLibrary;
	}
	
	//mutators
	/**
	 * Sets the PhotoContainer to the specified new PhotoContainer
	 * @param imageLibrary - the new PhotoContainer specified by the user
	 * @return - nothing returned; void method
	 */
	public void setImageLibrary(PhotoContainer imageLibrary) {
		this.imageLibrary = imageLibrary;
	}
	
	// GUI set-up methods
	/**
	 * Sets up each individual panel, which are then added to the pane which is added to the PhotoViewer JFrame.
	 * @param pane - pane to be added to the PhotoViewer frame
	 */
	public void addComponentsToPane(Container pane) {
		// Panel 1
		// Panel set-up
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Instantiate and add the photoTitle and caption JLabels to Panel 1
		photoTitle = new JLabel(imageLibrary.getPhotos().get(disIndex).getFilename());
		caption = new JLabel("'" + imageLibrary.getPhotos().get(disIndex).getCaption() + "'");
		panel1.add(photoTitle);
		panel1.add(caption);
		//Sets Alignment of photoTitle and caption JLabels
		photoTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		caption.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		//Panel 2
		// Panel set-up
		JPanel panel2 = new JPanel();
		panel2.setLayout(layout);
		layout.setAlignment(FlowLayout.CENTER);
		panel2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//ButtonListener for next and previous JButtons
		class ButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent a) {
				//previous button action block
				if (a.getActionCommand().equals("clickp")) {
					if (disIndex == 0) { //if user clicks "previous" when current Photo index (disIndex) is 0
						//resets disIndex, photoTitle, caption, image, and rating radiobuttons
						disIndex = imageLibrary.getPhotos().size() - 1;
						photoTitle.setText(imageLibrary.getPhotos().get(disIndex).getFilename());
						caption.setText(imageLibrary.getPhotos().get(disIndex).getCaption());
						try {
							ImageIcon newdisplay = new ImageIcon(imageLibrary.getPhotos().get(disIndex).getImageData().getScaledInstance(600, 420, Image.SCALE_DEFAULT));
							image.setIcon(newdisplay);
						} catch (Exception e) {
							image.setText("Error loading image");
						}
						//Sets selected Rating Radio Button based on current Rating
						if (imageLibrary.getPhotos().get(disIndex).getRating() == 1) {
							radGroup.setSelected(one.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 2) {
							radGroup.setSelected(two.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 3) {
							radGroup.setSelected(three.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 4) {
							radGroup.setSelected(four.getModel(), true);
						}
						else {
							radGroup.setSelected(five.getModel(), true);
						}
						revalidate(); //Force updates the GUI after the changes are made
					}
					else {
						disIndex--;
						photoTitle.setText(imageLibrary.getPhotos().get(disIndex).getFilename());
						caption.setText(imageLibrary.getPhotos().get(disIndex).getCaption());
						try {
							ImageIcon newdisplay = new ImageIcon(imageLibrary.getPhotos().get(disIndex).getImageData().getScaledInstance(600, 420, Image.SCALE_DEFAULT));
							image.setIcon(newdisplay);
						} catch (Exception e) {
							image.setText("Error loading image");
						}
						//Sets selected Rating Radio Button based on current Rating
						if (imageLibrary.getPhotos().get(disIndex).getRating() == 1) {
							radGroup.setSelected(one.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 2) {
							radGroup.setSelected(two.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 3) {
							radGroup.setSelected(three.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 4) {
							radGroup.setSelected(four.getModel(), true);
						}
						else {
							radGroup.setSelected(five.getModel(), true);
						}
						revalidate();
					}
				}
				//next button action block
				else if (a.getActionCommand().equals("clickn")) {
					if (disIndex == imageLibrary.getPhotos().size() - 1) {
						disIndex = 0;
						photoTitle.setText(imageLibrary.getPhotos().get(disIndex).getFilename());
						caption.setText(imageLibrary.getPhotos().get(disIndex).getCaption());
						try {
							ImageIcon newdisplay = new ImageIcon(imageLibrary.getPhotos().get(disIndex).getImageData().getScaledInstance(600, 420, Image.SCALE_DEFAULT));
							image.setIcon(newdisplay);
						} catch (Exception e) {
							image.setText("Error loading image");
						}
						//Sets selected Rating Radio Button based on current rating
						if (imageLibrary.getPhotos().get(disIndex).getRating() == 1) {
							radGroup.setSelected(one.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 2) {
							radGroup.setSelected(two.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 3) {
							radGroup.setSelected(three.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 4) {
							radGroup.setSelected(four.getModel(), true);
						}
						else {
							radGroup.setSelected(five.getModel(), true);
						}
						revalidate();
					}
					else {
						disIndex++;
						photoTitle.setText(imageLibrary.getPhotos().get(disIndex).getFilename());
						caption.setText(imageLibrary.getPhotos().get(disIndex).getCaption());
						try {
							ImageIcon newdisplay = new ImageIcon(imageLibrary.getPhotos().get(disIndex).getImageData().getScaledInstance(600, 420, Image.SCALE_DEFAULT));
							image.setIcon(newdisplay);
						} catch (Exception e) {
							image.setText("Error loading image");
						}
						//Sets selected Rating Radio Button based on current Rating
						if (imageLibrary.getPhotos().get(disIndex).getRating() == 1) {
							radGroup.setSelected(one.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 2) {
							radGroup.setSelected(two.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 3) {
							radGroup.setSelected(three.getModel(), true);
						}
						else if (imageLibrary.getPhotos().get(disIndex).getRating() == 4) {
							radGroup.setSelected(four.getModel(), true);
						}
						else {
							radGroup.setSelected(five.getModel(), true);
						}
						revalidate();
					}
				}
			}	
		}
		
		//Instantiates and adds functionality to previous JButton
		previous = new JButton("Previous");
		previous.setActionCommand("clickp");
		previous.addActionListener(new ButtonListener());
		//Instantiate and add ImageIcon to image JLabel in order to display image in the GUI
		image = new JLabel();
		try {
			ImageIcon display = new ImageIcon(imageLibrary.getPhotos().get(disIndex).getImageData().getScaledInstance(600, 420, Image.SCALE_DEFAULT));
			image.setIcon(display);
		} catch (Exception e) {
			image.setText("Error loading image");
		}
		//Instantiates and adds functionality to next JButton
		next = new JButton("Next");
		next.setActionCommand("clickn");
		next.addActionListener(new ButtonListener());
		//Adds above components to Panel2
		panel2.add(previous);
		panel2.add(image);
		panel2.add(next);
		
		//Panel 3
		//Panel set-up
		JPanel panel3 = new JPanel();
		panel3.setLayout(layout);
		layout.setAlignment(FlowLayout.CENTER);
		panel3.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		//Instantiate ButtonGroup and rating JRadioButtons
		radGroup = new ButtonGroup();
		one = new JRadioButton("1");
		two = new JRadioButton("2");
		three = new JRadioButton("3");
		four = new JRadioButton("4");
		five = new JRadioButton("5");
		
		//Sets Location of JRadioButton labels
		//Cited Source: Label API from KodeJava
		one.setVerticalTextPosition(SwingConstants.BOTTOM);
		one.setHorizontalTextPosition(SwingConstants.CENTER);
		two.setVerticalTextPosition(SwingConstants.BOTTOM);
		two.setHorizontalTextPosition(SwingConstants.CENTER);
		three.setVerticalTextPosition(SwingConstants.BOTTOM);
		three.setHorizontalTextPosition(SwingConstants.CENTER);
		four.setVerticalTextPosition(SwingConstants.BOTTOM);
		four.setHorizontalTextPosition(SwingConstants.CENTER);
		five.setVerticalTextPosition(SwingConstants.BOTTOM);
		five.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//Adds JRadioButtons to ButtonGroup, thus only allowing user to only have one Radio Button selected at a time
		radGroup.add(one);
		radGroup.add(two);
		radGroup.add(three);
		radGroup.add(four);
		radGroup.add(five);
		
		//Initializes the selected RadioButton for the first Photo in the GUI
		if (imageLibrary.getPhotos().get(disIndex).getRating() == 1) {
			radGroup.setSelected(one.getModel(), true);
		}
		else if (imageLibrary.getPhotos().get(disIndex).getRating() == 2) {
			radGroup.setSelected(two.getModel(), true);
		}
		else if (imageLibrary.getPhotos().get(disIndex).getRating() == 3) {
			radGroup.setSelected(three.getModel(), true);
		}
		else if (imageLibrary.getPhotos().get(disIndex).getRating() == 4) {
			radGroup.setSelected(four.getModel(), true);
		}
		else {
			radGroup.setSelected(five.getModel(), true);
		}
		
		//ActionListeners for Radio Buttons, sets new rating for given Photo based on which Radio Button the user selects
		//Source Cited: ActionListener format from StackOverflow
		one.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary.getPhotos().get(disIndex).setRating(1);
			}
		});
		
		two.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary.getPhotos().get(disIndex).setRating(2);
			}
		});
		
		three.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary.getPhotos().get(disIndex).setRating(3);
			}
		});
		
		four.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary.getPhotos().get(disIndex).setRating(4);
			}
		});
		
		five.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				imageLibrary.getPhotos().get(disIndex).setRating(5);
			}
		});
		
		//Adds Radio Buttons to Panel3
		panel3.add(one);
		panel3.add(two);
		panel3.add(three);
		panel3.add(four);
		panel3.add(five);
		
		//Adding Panels to Pane, utilizing BorderLayout
		pane.add(panel1, BorderLayout.PAGE_START);
		pane.add(panel2, BorderLayout.CENTER);
		pane.add(panel3, BorderLayout.PAGE_END);
	}
	
	/**
	 * Puts together, formats, and sizes the GUI frame, allows GUI and its contents to become visible
	 */
	private static void createAndShowGUI() {
		PhotoViewer photoLibrary = new PhotoViewer();
		photoLibrary.setTitle("Photo Library");
		photoLibrary.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		photoLibrary.setSize(WIDTH/4, HEIGHT/4);
		
		//Method call adds pane created in addComponentsToPane() to photoLibrary JFrame
		photoLibrary.addComponentsToPane(photoLibrary.getContentPane());
		photoLibrary.pack();
		photoLibrary.setVisible(true);
	}
}