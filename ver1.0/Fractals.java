/**
	Author	: Chandima B Samarasinghe (e14305)
	Date 	: 31th Aug 2017
**/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

public class Fractals extends MyFrame{
	private JPanel panel;
	public static void main(String[] args){Fractals fractals=new Fractals(args);}

	public Fractals(String[] args){
		super("Fractals",800,800,false,true);
		this.setLayout(null);
		init(args);
		
		this.add(panel);
		//this.setAlwaysOnTop (true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack(); //JFrame pack();
	}
	public void init(String[] args){

		if(args.length==0){//No args
			System.out.println("No Arguments!");
			printHelp();
			System.exit(0);
		}else if(args.length==1){//default values
			if(args[0].equals("Mandelbrot")){
				panel=new Mandelbrot(
									-1d, //real1
									1d, //real2
									-1d, //imag1
									1d, //imag2
									1000 //iterations
				);
				this.setTitle("Fractals - Mandelbrot");
			}else if(args[0].equals("Julia")){
				panel=new Julia(
									-0.4d, //real_c
									0.6d, //imag_c
									1000 //iterations
				);
				this.setTitle("Fractals - Julia");
			}else{ //wrong argument
				System.out.println("wrong Argument!");
				printHelp();
				System.exit(0);
			}
		}else{//may have enough arguments
			if(args[0].equals("Mandelbrot")){
				try{
					panel=new Mandelbrot(
									Double.valueOf(args[1]), //real1
									Double.valueOf(args[2]), //real2
									Double.valueOf(args[3]), //imag1
									Double.valueOf(args[4]), //imag2
									Integer.valueOf(args[5]) //iterations
					);
					this.setTitle("Fractals - Mandelbrot");
				}catch(Exception ex){System.out.println("Check Arguments!");printHelp();System.exit(0);}finally{}
				
			}else if(args[0].equals("Julia")){
				try{
					panel=new Julia(
									Double.valueOf(args[1]), //real_c
									Double.valueOf(args[2]), //imag_c
									Integer.valueOf(args[3]) //iterations
					);
					this.setTitle("Fractals - Julia");
				}catch(Exception ex){System.out.println("Check Arguments!");printHelp();System.exit(0);}finally{}
			}else{
				System.out.println("Check Arguments!");
				printHelp();
				System.exit(0);
			}
		}
	}

	private void printHelp(){
		System.out.println("Arguments for Mandelbrot\nMandelbrot <real_1> <real_2> <imaginary_1> <imaginary_2> <iterations>");
		System.out.println("\nArguments for Julia\nJulia <real_1> <imaginary_1> <iterations>\n");
			
	}
}

class MyFrame extends JFrame implements ActionListener{
	public void actionPerformed(ActionEvent event){}
	public MyFrame(String title,int width,int height){
		this(title,width,height,true,true);
	}
	public MyFrame(String title,int width,int height,boolean isResizable,boolean isVisible){
		setTitle(title);	
		setResizable(isResizable);
		setSize(width,height);
		setPreferredSize(new Dimension(width,height));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation( //center screen
			dim.width/2-this.getSize().width/2,
			dim.height/2-this.getSize().height/2
		);
		setVisible(isVisible);
	}
}