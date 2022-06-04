package graphic.Panels;

import graphic.GameLoop;
import graphic.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LeaderBoard extends JPanel implements ActionListener {
    public static TreeMap<Integer, ArrayList<String>> leads=new TreeMap<>();
    private int count=0;
    private int shift;
    private int defaultX=80,defaultY=80,XString=680;
    public LeaderBoard(){
        /*ArrayList<String> names=new ArrayList<>();
        ArrayList<String> n=new ArrayList<>();
        names.add("amir");names.add("taymaz");names.add("ahmad");names.add("amirahmad");names.add("erfan");names.add("er1");
        n.add("amir");n.add("taymaz");n.add("ahmad");n.add("amirahmad");n.add("er1");n.add("pooria");
        leads.put(-99,names);
        leads.put(-77999,n);*/
        JButton back=new JButton("Back");
        back.setFocusable(false);
        back.setFont(new Font("Arial",Font.PLAIN,10));
        back.setBounds(0,0,70,70);
        back.addActionListener(this);

        this.setBounds(0,0,840,960);
        this.setBackground(new Color(168,60,40));
        this.add(back);
        this.setVisible(true);
        this.setLayout(null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d=(Graphics2D) g;
        g2d.setFont(new Font("Arial",Font.BOLD,14));
        for (Integer i: leads.keySet()) {
            for (String name: leads.get(i)) {
                g2d.drawString(String.valueOf(-i+1),XString,(count+1)*defaultY);
                g2d.drawString(name,defaultX,(count+1)*defaultY);
                count++;
            }
            if (count==11)break;
        }
        count=0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action=e.getActionCommand();
        MainFrame.getMainFrame().doAction(action);
    }
}
