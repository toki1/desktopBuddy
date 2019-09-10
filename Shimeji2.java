import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;

/**
 * Write a description of class Shimeji2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Shimeji2 
{
    private  JWindow jf;
    private HashMap<String, ArrayList<JLabel>> sprites;
    private JLabel current;
    private boolean status=true;
    private Point location;
    private Point mouse_loc= new Point();
    private boolean left_click=false;
    private Point mouse_drag= new Point();
    private vector v;
    private final double width= Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private final double height= Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public Shimeji2(HashMap<String, ArrayList<JLabel>> spritemap)
    {
        jf= new JWindow();
        jf.setSize(200,200);
        jf.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        jf.setAlwaysOnTop (true);
        jf.setBounds(100,100,200,200);
        jf.setContentPane(new TranslucentPane());      
        mouse m = new mouse();
        jf.addMouseListener(m);
        jf.addMouseMotionListener(m);
        sprites = spritemap;

    }

    public void creation()
    {
        jf.add(sprites.get("idleL").get(0));
        current = sprites.get("idleL").get(0);
        jf.repaint();
        jf.revalidate();
        jf.setVisible(true); 
        v= new vector(270, 0.99);
        main();
    }

    private void main()
    {
        while(status)
        {
            if(left_click)
            {
                grabbed();
            }
            location = jf.getLocationOnScreen();
            if(location.getY()<height-150&&!left_click)
            {
                falling();
            }
        }
    }

    private void climbing(boolean isright)
    {

    }

    private void walking(boolean isright)
    {

    }

    private void grabbed()
    {
        ArrayList<JLabel> floatsp = sprites.get("float");
        while(left_click)
        {
            mouse_loc=MouseInfo.getPointerInfo().getLocation();   
            jf.setLocation((int)mouse_loc.getX()-100, (int)mouse_loc.getY()-50);
            if(mouse_loc.getX()>mouse_drag.getX())
            {
                v.setangle(Math.toDegrees((Math.atan((-(mouse_loc.getY()-mouse_drag.getY())+0.000001)/(mouse_loc.getX()-mouse_drag.getX()+0.000001)))));
                v.setmagnitude(Math.pow(Math.pow(mouse_loc.getX()-mouse_drag.getX(),2)+Math.pow(mouse_loc.getY()-mouse_drag.getY(),2),0.5)*0.4);
            }
            else if(mouse_loc.getX()<mouse_drag.getX())
            {
                v.setangle(180+Math.toDegrees((Math.atan((-(mouse_loc.getY()-mouse_drag.getY())+0.000001)/(mouse_loc.getX()-mouse_drag.getX()+0.000001)))));
                v.setmagnitude(Math.pow(Math.pow(mouse_loc.getX()-mouse_drag.getX(),2)+Math.pow(mouse_loc.getY()-mouse_drag.getY(),2),0.5)*0.4);

            }
            if(v.magnitude>12.5&&(v.angle<90||v.angle>270))
            {
                sprite_change(floatsp.get(0));
                if(v.magnitude>25)
                {
                    sprite_change(floatsp.get(2));
                    if(v.magnitude>50)
                    {
                        sprite_change(floatsp.get(4));
                    }
                }
            }
            else if(v.magnitude>12.5&&(v.angle>90||v.angle<270))
            {
                sprite_change(floatsp.get(1));
                if(v.magnitude>25)
                {
                    sprite_change(floatsp.get(5));
                }
            }
            if(v.magnitude<12.5||(v.angle>265&&v.angle<275))
            {
                sprite_change(sprites.get("idleL").get(0));
  
            }
        }
    }

    private void sprite_change(JLabel image)
    {
        jf.remove(current);

        jf.add(image);
        current=image;

        jf.repaint();
        jf.revalidate();
    }

    private void drag()
    {
        mouse_drag= MouseInfo.getPointerInfo().getLocation();
        try {
            Thread.sleep(20);  
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private void falling()
    {        

        while(location.getY()<height-150&&!left_click)
        {
            location = jf.getLocationOnScreen();
            try {
                Thread.sleep(20);  
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            v.add(270.0,0.25);
            if(location.getY()<=height-150&&location.getY()>=height-170)
            {
                jf.setLocation((int)location.getX(), (int)height-150);     
                v.reset();
            }
            else
            {
                positionUpdate();
            }            
        }       
    }

    private void positionUpdate()
    {
        jf.setLocation((int)Math.round(jf.getX()+v.magnitude*Math.cos(Math.toRadians(v.angle))), (int)Math.round(jf.getY()+v.magnitude*(-1)*Math.sin(Math.toRadians(v.angle))));
    }
    private static  class vector
    {
        public double angle;
        public double magnitude;
        public vector(double anglee, double magnitudee)
        {
            angle= anglee;
            magnitude=magnitudee;
        }

        public  void add(double anglee, double magnitudee)
        {
            double x= Math.cos(Math.toRadians(angle))*magnitude;
            double y= Math.sin(Math.toRadians(angle))*magnitude;
            double xother= Math.cos(Math.toRadians(anglee))*magnitudee;
            double yother= Math.sin(Math.toRadians(anglee))*magnitudee;
            if(xother+x<0.001&&xother+x>-0.001)
            {
                if((angle<180&&anglee<180)||(angle>180&&anglee>180))
                    magnitude+=Math.abs(magnitudee);
                else
                    magnitude+=Math.abs(magnitudee);
            }
            else
            {
                magnitude= Math.pow(Math.pow(x+xother,2)+Math.pow(y+yother,2), 0.5);
                if(angle>90)                
                    angle= Math.toDegrees(Math.atan((y+yother)/(x+xother)))+180;                   
                else
                    angle= Math.toDegrees(Math.atan((y+yother)/(x+xother)));                   
            }
        }

        public  void setmagnitude(double magnitudee)
        {
            magnitude= magnitudee;
        }

        public  void setangle(double anglee)
        {
            angle = anglee;
        }

        public  void reset()
        {

            magnitude=0.0000001;
        }
    }
    private  class mouse extends MouseAdapter implements MouseMotionListener
    {
        public void mousePressed(MouseEvent e) 
        {            
            left_click= true;
        }

        public void mouseReleased(MouseEvent e)
        {
            Thread.currentThread().interrupt();
            left_click=false;
        }   

        public void mouseDragged(MouseEvent e)
        {
            drag();
        }
    }
}
