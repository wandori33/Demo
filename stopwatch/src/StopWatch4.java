import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StopWatch4 extends JFrame implements ActionListener, Runnable{
	JLabel hour;
	JLabel min;
	JLabel sec;
	JButton b1;
	JButton b2;
	JButton b3;
	Thread t_display=null;
	
	StopWatch4(){
		super("Stop Watch");
		
		JPanel p1 = new JPanel();
		b1 = new JButton("START");
		b2 = new JButton("PAUSE");
		b3 = new JButton("RESET");
		
		JPanel p2 = new JPanel();
		b2.setEnabled(false);
		b3.setEnabled(false);
		
		hour = new JLabel("00");
		JLabel mid1 = new JLabel(":"); 
		min = new JLabel("00");
		JLabel mid2 = new JLabel(":");
		sec = new JLabel("0");
		
		hour.setFont(new Font("Courier New", Font.BOLD, 20));
		mid1.setFont(new Font("Courier New", Font.BOLD, 20));
		min.setFont(new Font("Courier New", Font.BOLD, 20));
		sec.setFont(new Font("Courier New", Font.BOLD, 20));
		
		p1.add(b1);
		p1.add(b2);
		p1.add(b3);
		p2.add(hour);
		p2.add(mid1);
		p2.add(min);
		p2.add(mid2);
		p2.add(sec);
		
		
		add(p1, "South");
		add(p2, "Center");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		
		
		pack();
		setLocation(100, 100);
		setResizable(false);
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae){
		String s = ae.getActionCommand();
		
		if(s.equals("START")){
			if(t_display==null) t_display = new Thread(this);
			
			t_display.start(); 
			b1.setEnabled(false);
			b2.setEnabled(true);
			b3.setEnabled(false);	
			
		}else if(s=="PAUSE"){
			t_display = null;
			
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(true);
		}else if(s=="RESET"){
			hour.setText("00");
			min.setText("00");
			sec.setText("00");
			t_display = null;
			
			b1.setEnabled(true);
			b2.setEnabled(false);
			b3.setEnabled(false);
		}
		
	}	

    class WindowEventHandler extends WindowAdapter{
        public void windowClosing(WindowEvent i) {
              System.exit(1);           
        }
    }

	
	public void run(){

		while(Thread.currentThread() == t_display){

			
			int i = Integer.parseInt(sec.getText());
			int j = Integer.parseInt(min.getText());
			int k = Integer.parseInt(hour.getText());
			
			if(i==9){ 
				min.setText(String.format("%02d", ++j));
				i=0;
				sec.setText(String.valueOf(i));
			}			
			else 
				sec.setText(String.valueOf(++i));
			
			try{
				Thread.sleep(100);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			if(j==59){					
				hour.setText(String.format("%02d", ++k));
				j=0;
				min.setText(String.format("%02d", j));
			}

		}		
			
	}
	
	public static void main(String[] args){
		 new StopWatch4();
	}
	
}