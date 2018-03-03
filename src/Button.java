import javax.swing.*;

/**
 * Class instantiates a game button.
 * Class stores button face.
 * Class contains setFace(), getFace(), setMatchStatus(), and getMatchStatus() methods
 * @author Trisha Echual 013470806 piechual@gmail.com
 *
 */
public class Button extends JButton
{
	////////// Fields //////////
	private String face = "";
	private boolean isMatched = false;
	
	////////// Constructors //////////
	/**
	 * Constructor from super class
	 */
	public Button()
	{
		super();
	}
	
	////////// Methods //////////
	/**
	 * Method sets the face (content) of the "face-down" button
	 * @param face content of a button
	 */
	public void setFace(String face)
	{
		this.face = face;
	}
	
	/**
	 * Method gets the face (content) of the "face-down" button
	 * @return content of a button
	 */
	public String getFace()
	{
		return this.face;
	}
	
	/**
	 * Method sets the status for a button that may be matched
	 * @param flag status of button 
	 */
	public void setMatchStatus(boolean flag)
	{
		this.isMatched = flag;
	}
	
	/**
	 * Method gets the status for a button that may be matched
	 * @return status of button
	 */
	public boolean getMatchStatus()
	{
		return this.isMatched;
	}
}
