import javax.swing.JOptionPane;

/**
 * Class containing main method.
 * Class informs user of game play rules and begins game.
 * @author Trisha Echual 013470806 piechual@gmail.com
 *
 */
public class ConcentrateGame 
{
	public static void main(String[] args)
	{		
		String gameInfo = "The concentration is a game for two players.  Each player takes\n"
				        + "turns choosing two 'face down' cards.  When cards are chosen they\n"
				        + "are turned up.  A player gets a point when the two cards match.\n"
				        + "The first player to 13 points wins the game.";
		JOptionPane.showMessageDialog(null, gameInfo, "Concentration Game", JOptionPane.PLAIN_MESSAGE);
		
		Board concentrateGame = new Board();
	}
}
