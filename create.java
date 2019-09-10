import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.security.*;

public class create
{
    public static void main(String[]args){
        Shimeji2 luka= new Shimeji2(loadMap());
        luka.creation();
    }

    private static HashMap<String, ArrayList<JLabel>> loadMap()
    {
        HashMap <String, ArrayList<JLabel>> sprites= new HashMap();
        ArrayList<JLabel> temp= new ArrayList<JLabel>();

        temp.add(new JLabel(new ImageIcon("shime1.png")));
        sprites.put("idleL", temp);
        temp= new ArrayList<JLabel>();
        temp.add(new JLabel(new ImageIcon("shime1R.png")));
        sprites.put("idleR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=1;i<=3;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("walkL", temp);
        temp= new ArrayList<JLabel>();
        for(int i=1;i<=3;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("walkR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=5;i<=10;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("float", temp);
        temp= new ArrayList<JLabel>();
        temp.add(new JLabel(new ImageIcon("shime4.png")));
        sprites.put("fallL", temp);
        temp= new ArrayList<JLabel>();
        temp.add(new JLabel(new ImageIcon("shime4R.png")));
        sprites.put("fallR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=12;i<=14;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("climbL", temp);
        temp= new ArrayList<JLabel>();
        for(int i=12;i<=14;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("climbR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=15;i<=17;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("sitL", temp);
        temp= new ArrayList<JLabel>();
        for(int i=15;i<=17;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("sitR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=18;i<=21;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("crawlL", temp);
        temp= new ArrayList<JLabel>();
        for(int i=18;i<=21;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("crawlR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=22;i<=22;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("jumpL", temp);
        temp= new ArrayList<JLabel>();
        for(int i=22;i<=22;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("jumpR", temp);
        temp= new ArrayList<JLabel>();
        for(int i=23;i<=25;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("roofL", temp);
        temp= new ArrayList<JLabel>();
        for(int i=23;i<=25;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("roofR", temp);
                temp= new ArrayList<JLabel>();
        for(int i=26;i<=32;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+".png")));
        }
        sprites.put("sit2L", temp);
        temp= new ArrayList<JLabel>();
        for(int i=26;i<=32;++i)
        {
            temp.add(new JLabel(new ImageIcon("shime"+(i)+"R.png")));
        }
        sprites.put("sit2R", temp);
        //sprites.put("right", new JLabel(new ImageIcon("shime"+(i+1)+".png")));
        return sprites;
    }
}
