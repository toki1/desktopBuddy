import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.security.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Write a description of class asdf here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shimejitest
{
    private static boolean inhouse=false;
    private static boolean freed=false;
    private static int emails=0;
    private static int emails_prev=0;

    public static class Asriel_Shimeji extends Thread implements MouseMotionListener
    {
        private  boolean right_click= false;
        private  boolean left_click= false;
        private  boolean limit= false;
        private Timer timer=new Timer();
        private boolean got_mail= false;
        private Window w;
        private  Point update;
        private  Point loc;
        private  int diff=0;
        private  Point temp_mouse;
        private  JWindow jf;
        private JLabel current;
        private  JLabel[] comp=new JLabel[51];
        private  JLabel[] compR=new JLabel[49];
        public Asriel_Shimeji()
        {
            jf= new JWindow();
            jf.setSize(200,200);
            jf.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
            jf.setAlwaysOnTop (true);
            jf.setBounds(500,100,200,200);
            jf.setContentPane(new TranslucentPane());
            jf.addMouseListener(new MouseAdapter(){
                    public void mousePressed(MouseEvent e) 
                    {
                        temp_mouse=MouseInfo.getPointerInfo().getLocation();
                        if(e.getButton() == MouseEvent.BUTTON1)
                        {
                            left_click= true;
                            temp_mouse=MouseInfo.getPointerInfo().getLocation();
                        }       
                        else if(e.getButton() == MouseEvent.BUTTON3)
                        {
                            temp_mouse=MouseInfo.getPointerInfo().getLocation();
                            right_click= true;
                        }
                    }

                    public void mouseReleased(MouseEvent e)
                    {
                        if(left_click)
                        {
                            left_click=false;
                        }
                    }   

                });
            jf.addMouseMotionListener(this);
        }

        public void creation() {
        jf.add(comp[0]);
        current=comp[0];
        jf.repaint();
        jf.revalidate();
        jf.setVisible(true);
        sprite_change(0,true);
        int delay=0;
        int delay2=0;
        boolean jumping= false;
        double move;
        boolean alive=true;
        boolean climbing=false;
        int incrementor=0;
        double action;
        Point point=jf.getLocationOnScreen();
        int sequence=0;
        boolean refresh=false;
        int i=0;
        while(alive)
        {

        if(refresh==true)
        {
        jf.setVisible(true);
        jf.setAlwaysOnTop (true);
        }
        if(left_click)
        {
        climbing=false;
        grabbed();
        }
        else if(!climbing&&(int)point.getY()<=1025)
        {
        falling();
        sprite_change(0, true);
        }
        else if(emails_prev<emails)
        {
        emails_prev=emails;
        sprite_change(50, true);
        }
        else if(onwall())
        {
        try {
        Thread.sleep(10);  
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        action= Math.random();
        if(action<=0.5)
        {
        move=Math.random()*10;
        if((int)point.getX()>0)
        {
        climbing=true;
        climbing(false, (int)move);
        }
        else
        {
        climbing=true;
        climbing(true,(int)move);
        }
        }
        }                    
        else  
        {
        try {
        if(left_click)
        {
        Thread.currentThread().interrupt();
        }
        Thread.sleep(1000);  
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        action= Math.random();               
        if(action<=0.5)
        {
        move=Math.random()*10;
        walking(true, (int)move);
        }
        else if(action>0.5)
        {
        move=Math.random()*10;
        walking(false, (int)move);
        }
        }
        point=jf.getLocationOnScreen();
        if(!left_click&&point.getX()>=-1750&&point.getX()<=-1650&&point.getY()>=900&&!freed)
        {
        inhouse=true;
        }
        else if(freed&&!(point.getX()>=-1750&&point.getX()<=-1650&&point.getY()>=900))
        {
        freed=false;
        }

        }
        }

        public boolean onwall()
        {
        return !left_click&&(jf.getLocationOnScreen().getX()>=(1810)||jf.getLocationOnScreen().getX()<=(-1910));
        }

        public boolean onground()
        {
        return (int)jf.getLocationOnScreen().getY()>=1026;
        }

        public void falling()
        {
        sprite_change(3, true);
        while((int)jf.getLocationOnScreen().getY()<=1025)
        {
        try {
        Thread.sleep(2,5);  
        //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        jf.setLocation(jf.getX(), jf.getY()+1);
        if(left_click)
        {
        break;
        }
        }
        }

        public void climbing(boolean direction, int length)
        {      
        int sequence=0;
        if(direction)
        {
        for(int  i=0;i<(int)(length*100);++i)
        {
        if((int)jf.getLocationOnScreen().getY()==10)
        {
        break;
        }
        try {
        Thread.sleep(10);  
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        jf.setLocation(jf.getX(), jf.getY()-1);
        if(sequence==0&&i%50==0)
        {                            
        sprite_change(11, true);
        ++sequence;
        }
        else if(sequence==1&&i%50==0)
        {
        --sequence;        
        sprite_change(12, true);
        }
        if(left_click)
        {
        break;
        }
        }
        }
        else
        {
        for(int  i=0;i<(int)(length*100);++i)
        {
        if((int)jf.getLocationOnScreen().getY()==10)
        {
        break;
        }
        try {
        Thread.sleep(10);  
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        jf.setLocation(jf.getX(), jf.getY()-1);
        if(sequence==0&&i%50==0)
        {                            
        sprite_change(11, false);
        ++sequence;
        }
        else if(sequence==1&&i%50==0)
        {
        sprite_change(12, false);
        }
        if(left_click)
        {
        break;
        }
        }
        }
        }

        public void sprite_change(int i, boolean j)
        {
        jf.remove(current);
        if(j)
        {
        jf.add(comp[i]);
        current=comp[i];
        }
        else
        {
        jf.add(compR[i]);
        current=compR[i];
        }
        jf.repaint();
        jf.revalidate();
        }

        public void walking(boolean direction, int length)
        {   
        int sequence=0;

        if(direction)
        {
        for(int  i=0;i<(int)(length*100);++i)
        {
        if((jf.getLocationOnScreen().getX()<=(-1910)))
        {
        break;
        }
        try {
        Thread.sleep(10);  
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }

        jf.setLocation(jf.getX()-1, jf.getY());
        if(sequence==0&&i%50==0)
        {                            
        sprite_change(1, true);
        ++sequence;
        }
        else if(sequence==1&&i%50==0)
        {
        --sequence;        
        sprite_change(2, true);
        }
        if(left_click)
        {
        break;
        }
        }
        }
        else
        {
        for(int  i=0;i<(int)(length*100);++i)
        {
        if((jf.getLocationOnScreen().getX()>=(1810)))
        {
        break;
        }
        try {
        Thread.sleep(10);  
        } catch(InterruptedException ex) {
        Thread.currentThread().interrupt();
        }
        jf.setLocation(jf.getX()+1, jf.getY());
        if(sequence==0&&i%50==0)
        {                            
        sprite_change(0, false);
        ++sequence;
        }
        else if(sequence==1&&i%50==0)
        {
        --sequence;        
        sprite_change(1, false);
        }
        if(left_click)
        {
        break;
        }
        }
        }
        sprite_change(0, true);
        }
         
        public void grabbed()
        {
            boolean istilted= false;
            sprite_change(5, true);
            int xchange=0;
            int x1=0;
            int prev=0;
            boolean imp1=false;
            boolean imp2=false;
            boolean imp3=false;
            boolean imp4=false;
            boolean imp5=false;
            boolean idle= false;
            while(left_click)
            {

                jf.setBounds((int)MouseInfo.getPointerInfo().getLocation().getX()-75,(int)MouseInfo.getPointerInfo().getLocation().getY()-25,200,200);
                int dist=0;
                if(update!=null)
                {
                    if(diff>0)
                    {
                        if(diff>20&&!imp1)
                        {
                            sprite_change(7, true);
                            imp1=true;
                            imp2=false;
                            imp3=false;
                            imp4=false;
                            imp5=false;
                        }
                    }
                    else if(diff<0)
                    {
                        if(diff<-30&&!imp2)
                        {
                            sprite_change(8, true);
                            imp2=true;
                            imp1=false;
                            imp3=false;
                            imp4=false;
                            imp5=false;
                        }
                        else if(diff<-10&&diff>-30&&!imp3)
                        {
                            sprite_change(6, true);
                            imp3=true;
                            imp2=false;
                            imp1=false;
                            imp4=false;
                            imp5=false;
                        }
                        else if(diff<-5&&diff>-15&&diff>-10&&!imp4)
                        {
                            sprite_change(4, true);
                            imp4=true;
                            imp2=false;
                            imp3=false;
                            imp1=false;
                            imp5=false;
                        }
                    }
                    else if(!imp5)
                    {
                        sprite_change(5, true);
                        imp5=true;
                        imp2=false;
                        imp3=false;
                        imp4=false;
                        imp1=false;
                    }
                }
            }
            sprite_change(3, true);
        }

        public class TranslucentPane extends JPanel {
            public TranslucentPane() {
                setOpaque(false);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.SrcOver.derive(0.0f));
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        }

        public void mousePressed(MouseEvent e) 
        {
            temp_mouse=MouseInfo.getPointerInfo().getLocation();
            if(e.getButton() == MouseEvent.BUTTON1)
            {
                left_click= true;
                temp_mouse=MouseInfo.getPointerInfo().getLocation();
            }       
            else if(e.getButton() == MouseEvent.BUTTON3)
            {
                temp_mouse=MouseInfo.getPointerInfo().getLocation();
                right_click= true;
            }
        }

        public void mouseReleased(MouseEvent e)
        {
            if(left_click)
            {
                left_click=false;
            }
            else
            {
                got_mail=false;
                System.out.println("ASDFASDF");
            }
        }

        public void mouseEntered(MouseEvent e) 
        {
        }

        public void mouseExited(MouseEvent e) 
        {
        }

        public void mouseDragged(MouseEvent e)
        {
            if(left_click)
            {   
                limit=false;
                loc=MouseInfo.getPointerInfo().getLocation();
                try {
                    Thread.sleep(50);  
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                update=MouseInfo.getPointerInfo().getLocation();
                diff=(int)loc.getX()-(int)update.getX();
            }
        }

        public void mouseClicked(MouseEvent e) 
        {
        }

        public void mouseMoved(MouseEvent e)
        {
        }

        public void mouseWheelMoved(MouseWheelEvent e)
        {
        }

        public void loadimage()
        {
            for(int i=0;i<46;++i)
            {
                comp[i]= new JLabel(new ImageIcon("shime"+(i+1)+".png"));     
            }
            comp[50]= new JLabel(new ImageIcon("shime_email.png"));    
            for(int i=0;i<4;++i)
            {
                compR[i]= new JLabel(new ImageIcon("shime"+(i+1)+"_R.png"));      
            }
            for(int i=10;i<46;++i)
            {
                compR[i]= new JLabel(new ImageIcon("shime"+(i+1)+"_R.png"));      
            }
        }
    }

    public static void main(String[] args) {
        Asriel_Shimeji  t= new Asriel_Shimeji ();
        t.loadimage();
        t.creation();              
        //t.live();
    }

}
