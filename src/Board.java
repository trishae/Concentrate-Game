import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Class instantiates and creates the game play of the concentration game.
 * Class creates Buttons and JFrame board for the game.
 * Class also maintains player scores and number of turns. 
 * Class contains isGameOver(), makeMove(), and checkIfPaired() methods.
 * @author Trisha Echual 013470806 piechual@gmail.com
 *
 */
public class Board extends JFrame
{
	////////// Fields //////////
	//Grid dimensions
	private final int GRID_ROWS = 6;
	private final int GRID_COLUMNS = 8;
	
	//Button faces of all pairs of buttons
	private String[] myFace = new String[] {"w", "O", "d", "l", "U", "v",
											"i", "b", "h", "q", "P", "R",
											"!", "@", "#", "$", "%", "^",
											"&", "*", "<", ">", "?", "+"}; 
	
	//Number of pairs in gameplay
	private final int buttonPairs = myFace.length; 
	
	//Gameplay buttons
	private List<Button> globalButtons; 
	
	//Temporary reference buttons
	private Button pressedButton; 
	private Button firstButton;
	private Button secondButton;
	
	//Timer instantiation of unmatched buttons/cards and delay (half-second)
	private Timer clock; 
	private int delay = 500; 
	
	//Player turn keeping and number of turns
	private boolean isP1Turn; 
	private int turnNum;
	
	//Player score keeping
	private int p1Score;
	private int p2Score;
	
	//JFrame components
	private JPanel labelPane;
	private JLabel scoreBoard;
	private JPanel buttonPanel;
	private Container main;
	
	
	////////// Constructors //////////
	public Board()
	{
		//Set up scores and score board
		isP1Turn = true;
		turnNum = 0;
		p1Score = 0;
		p2Score = 0;

		List<Button> localButtons = new ArrayList<Button>(); //create buttons
		List<String> buttonFaces = new ArrayList<String>(); //create button contents
		
		for (int i = 0; i < buttonPairs; i++)
		{
			buttonFaces.add(myFace[i]);
			buttonFaces.add(myFace[i]);
		}
		
		Collections.shuffle(buttonFaces); //randomize placements from button faces list
		
		//set up gameplay buttons
		for (String face : buttonFaces)
		{
			Button tempButton = new Button();
			tempButton.setFace(face); //insert face
			
			//buttonize
			tempButton.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent event)
												{ //do this when button pressed
													pressedButton = tempButton;
													makeMove();
												}
											});
			
			localButtons.add(tempButton);
		}
		this.globalButtons = localButtons;
		
		//set up timer
		clock = new Timer(delay, new ActionListener()
								{
									public void actionPerformed(ActionEvent event)
									{ //do this when timer on
										checkIfPaired();
									}
								});
		clock.setRepeats(false);
		
		//set up game board
		setPreferredSize(new Dimension(500,500)); //need to use this instead of setSize
		setLocation(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
		//Construct panel for score board
		labelPane = new JPanel();
		scoreBoard = new JLabel("PLAYER 1 TURN           [Player 1: " + p1Score + "] [Player 2: " + p2Score + "]                      " + turnNum + " TURNS", JLabel.CENTER);
		scoreBoard.setHorizontalTextPosition(JLabel.CENTER); 
		labelPane.add(scoreBoard);
		
		//Construct panel for button grid
		buttonPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLUMNS));
		for (Button btn : globalButtons)
		{
			buttonPanel.add(btn);
		}
		
		//Add all panels onto the container jframe
		main = getContentPane();
		main.add(labelPane, BorderLayout.NORTH);
		main.add(buttonPanel, BorderLayout.CENTER);

		setVisible(true);
	}
	
	
	////////// Methods //////////
	/**
	 * Method to check if the game is over
	 * @return
	 */
	public boolean isGameOver()
	{
		boolean gameOver = false;
		if ((p1Score == 13) || (p2Score == 13))
			gameOver = true;
		return gameOver;
	}
	
	/**
	 * Method to execute a single turn and show faces when buttons are pressed
	 */
	public void makeMove()
	{
		//if no moves have been made in a single turn...
		if ((firstButton == null) && (secondButton == null))
		{
			firstButton = pressedButton;
			firstButton.setText(firstButton.getFace()); //show face of first button
		}
		
		//if only first button has been pressed
		if ((firstButton != null) && (firstButton != pressedButton) && (secondButton == null))
		{
			secondButton = pressedButton;
			secondButton.setText(secondButton.getFace()); //show face of second button
			clock.start(); //begin 3 seconds timer
		}
	}
	
	/**
	 * Method to check if buttons' faces are paired (matching)
	 */
	public void checkIfPaired()
	{
		//if pairs have been matched, make buttons invisible
		if (firstButton.getFace() == secondButton.getFace())
		{
			firstButton.setText("");
			firstButton.setEnabled(false); 
			firstButton.setMatchStatus(true);
			
			secondButton.setText("");
			secondButton.setEnabled(false);
			secondButton.setMatchStatus(true);
			
			//if Player 1 correct, increase Player 1 score
			if (isP1Turn)
			{
				p1Score += 1;
			}
			else //if Player 2 correct, increase Player 2 score
			{
				p2Score += 1;
			}
			
			if (isGameOver()) //Notify winner if game is over
			{
				if (p1Score == 13)
					JOptionPane.showMessageDialog(this, "PLAYER 1 won the game!", "Concentration Game", JOptionPane.PLAIN_MESSAGE);
				else if (p2Score == 13)
					JOptionPane.showMessageDialog(this, "PLAYER 2 won the game!", "Concentration Game", JOptionPane.PLAIN_MESSAGE);
				//else
				//	JOptionPane.showMessageDialog(this, "It's a tie!", "Concentration Game", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
		}
		else if (firstButton.getFace() != secondButton.getFace())
		{ //Hide text if cards do not match
			firstButton.setText("");
			secondButton.setText("");
		}
		
		//Update scores if no winner is found yet
		isP1Turn = !isP1Turn;
		turnNum++;
		if (isP1Turn)
			scoreBoard.setText("PLAYER 1 TURN           [Player 1: " + p1Score + "] [Player 2: " + p2Score + "]                      " + turnNum + " TURNS");
		else
			scoreBoard.setText("PLAYER 2 TURN           [Player 1: " + p1Score + "] [Player 2: " + p2Score + "]                      " + turnNum + " TURNS");
		
		//reset
		firstButton = null;
		secondButton = null;
	}
}
