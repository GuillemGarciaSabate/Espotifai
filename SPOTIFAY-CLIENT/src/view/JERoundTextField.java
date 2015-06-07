package view;

import java.awt.Color; 
import java.awt.Dimension; 
import java.awt.GradientPaint; 
import java.awt.Graphics; 
import java.awt.Graphics2D; 
import java.awt.Image; 
import java.awt.Paint; 
import java.awt.geom.RoundRectangle2D; 

import javax.swing.Icon; 
import javax.swing.ImageIcon; 
import javax.swing.JTextField; 
import javax.swing.border.EmptyBorder; 

@SuppressWarnings("serial")
/**
 * 
 * This class permits to create text fields with a rounded form
 * 
 * @author Jordi Badia
 * @author Guillem Gracia
 * @author Albert Trias	
 * @author Daniel Mateu
 * @author Adria Acero
 *
 */
public class JERoundTextField extends JTextField { 
    private int arcw=10; 
    private int arch=10; 
    private Image image=null; 
    private Icon icon; 

    /**
     * Constructor
     */
    public JERoundTextField() { 
        setOpaque(false); 
        setBorder(new EmptyBorder(0,5,0,2)); 
        setPreferredSize(new Dimension(200,25)); 
    } 
    
    /**
     * 
     * Constructor
     * 
     * @param arcw is the bottom angle
     * @param arch is the top angle
     * @param h is the dimension
     */
    public JERoundTextField(int arcw, int arch,int h) { 
    	this.arcw=arcw;
    	this.arch=arch;
        setOpaque(false); 
        setBorder(new EmptyBorder(0,5,0,2)); 
        setPreferredSize(new Dimension(h,25)); 
    } 

    @Override 
    /**
     * This method is used to paint the borders of the text fields
     * This method is executed all the time
     * 
     */
     protected void paintComponent(Graphics g) { 

        Graphics2D g2 = (Graphics2D) g; 
        Paint oldPaint = g2.getPaint(); 

        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float( 
                0,0,getWidth(),getHeight(),arcw,arch); 
        g2.clip(r2d); 

        g2.setPaint(new GradientPaint(0.0f, 0.0f, getBackground(), 
                0.0f, getHeight(), getBackground())); 
        g2.fillRect(0,0,getWidth(),getHeight()); 
        if(getImage()!=null){ 
            g2.drawImage(getImage(), 5, 2, getHeight()-3, getHeight()-3, null); 
            setBorder(new EmptyBorder(0,(int)(getHeight()*1.2),0,2)); 
        } 
        g2.setPaint(new GradientPaint(0.0f, 0.0f, Color.WHITE,0.0f, getHeight(), Color.WHITE)); 
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), arcw, arch); 

        g2.setPaint(oldPaint); 
        super.paintComponent(g); 

    } 

    /**
     * 
     * Gets the bottom angle
     * 
     * @return the bottom angle
     */
    public int getArcw() { 
        return arcw; 
    } 

    /**
     * 
     * Sets the bottom angle
     * 
     * @param arcw is the bottom angle
     */
    public void setArcw(int arcw) { 
        this.arcw = arcw; 
    } 

    /**
     * 
     * Gets the top angle
     * 
     * @return the top angle
     */
    public int getArch() { 
        return arch; 
    } 

    /**
     * 
     * Sets the top angle
     * 
     * @param arch is the top angle
     */
    public void setArch(int arch) { 
        this.arch = arch; 
    } 

    /**
     * 
     * Gets an image
     * @return Image value to return
     */
    public Image getImage() { 
        return image; 
    } 

    /**
     * 
     * Sets an image
     * @param image Image to set in the RoundTextField
     */
    public void setImage(Image image) { 
        this.image = image; 
    } 

    /**
     * 
     * Gets an icon
     * @return Icon value to return
     */
    public Icon getIcon() { 
        return icon; 
    } 

    /**
     * 
     * Sets an icon
     * @param icon Icon to set in the RoundTextField
     */
    public void setIcon(Icon icon){ 
        this.icon=icon; 
        setImage(((ImageIcon)icon).getImage()); 
    } 

}
