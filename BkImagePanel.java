//--------------------------------------------------------------------------
// BkImagePanel.java 
// Authors: Amal Tidjani
//
// Defines a BkImagePanel class that creates a panel with a background image
//---------------------------------------------------------------------------

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BkImagePanel extends JPanel{
    String imageFile = "/Images/MainScreen.png";
    
    public BkImagePanel(){
       super();
    }
    
    public BkImagePanel(String imagePath){
       super();
       this.imageFile = imagePath;
    }
    
    public BkImagePanel(LayoutManager layout){
       super(layout);
    }
    
    public void paintComponent(Graphics g){
       /*Create image icon to get image*/
       ImageIcon icon = new ImageIcon(getClass().getResource(imageFile));
       Image image = icon.getImage();
       
       /*Draw image on the panel*/
       super.paintComponent(g);
       
       if (image != null)
          g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}