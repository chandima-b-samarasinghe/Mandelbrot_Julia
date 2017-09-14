/**
	Author	: Chandima B Samarasinghe (e14305)
	Date 	: 31th Aug 2017

	Mandelbrot.java
**/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Mandelbrot extends JPanel{
	private static double real_1,real_2,imag_1,imag_2,dr,dc;
	private static int iterations;

	public Mandelbrot(double arg_real_1,double arg_real_2,double arg_imag_1,double arg_imag_2,int arg_iter){
		real_1=arg_real_1; real_2=arg_real_2; imag_1=arg_imag_1; imag_2=arg_imag_2; iterations=arg_iter;
		
		this.dr=(real_2-real_1)/799;
		this.dc=(imag_2-imag_1)/799;
		init();
		System.out.println("Processing...");
	}

	public void init(){
		setSize(800,800); //until the frame has no size, the painComponent will not invoke :v
		setPreferredSize(new Dimension(800,800));
	}

	public static Color calculatePoint0(int row,int col){
		double zr,zrc,zi,cr,ci,abs_z2;int iteration=0;
		cr=real_1+dr*row; ci=imag_2-dc*col;
		zr=cr; zi=ci;

		abs_z2=zr*zr+zi*zi;

		while(abs_z2<4){
			zrc=zr;
			zr=zr*zr-zi*zi+cr;
			zi=2*zrc*zi + ci;			
			abs_z2=zr*zr+zi*zi;
			iteration++;
			if(iteration>iterations){iteration=iterations; break;}
		}
		float ite=(float)iteration/iterations;
		return Color.getHSBColor(//floats
			ite*10, //color map
			0.7f,//saturation, //intensity
			0.8f//brightness
		);
	}
	
	public static Color calculatePoint1(int row,int col){
		double zr,zrc,zi,cr,ci,abs_z2;int iteration=0;
		cr=real_1+dr*row; ci=imag_2-dc*col;
		zr=cr; zi=ci;

		abs_z2=zr*zr+zi*zi;

		while(abs_z2<4){
			zrc=zr;
			zr=zr*zr-zi*zi+cr;
			zi=2*zrc*zi + ci;			
			abs_z2=zr*zr+zi*zi;
			iteration++;
			if(iteration>iterations){iteration=iterations; break;}
		}
		float ite=(float)iteration/iterations;
		return Color.getHSBColor(//floats
			ite*10, //color map
			0.7f,//saturation, //intensity
			0.8f//brightness
		);
	}
	public static Color calculatePoint2(int row,int col){
		double zr,zrc,zi,cr,ci,abs_z2;int iteration=0;
		cr=real_1+dr*row; ci=imag_2-dc*col;
		zr=cr; zi=ci;

		abs_z2=zr*zr+zi*zi;

		while(abs_z2<4){
			zrc=zr;
			zr=zr*zr-zi*zi+cr;
			zi=2*zrc*zi + ci;			
			abs_z2=zr*zr+zi*zi;
			iteration++;
			if(iteration>iterations){iteration=iterations; break;}
		}
		float ite=(float)iteration/iterations;
		return Color.getHSBColor(//floats
			ite*10, //color map
			0.7f,//saturation, //intensity
			0.8f//brightness
		);
	}

	public static Color calculatePoint3(int row,int col){
		double zr,zrc,zi,cr,ci,abs_z2;int iteration=0;
		cr=real_1+dr*row; ci=imag_2-dc*col;
		zr=cr; zi=ci;

		abs_z2=zr*zr+zi*zi;

		while(abs_z2<4){
			zrc=zr;
			zr=zr*zr-zi*zi+cr;
			zi=2*zrc*zi + ci;			
			abs_z2=zr*zr+zi*zi;
			iteration++;
			if(iteration>iterations){iteration=iterations; break;}
		}
		float ite=(float)iteration/iterations;
		return Color.getHSBColor(//floats
			ite*10, //color map
			0.7f,//saturation, //intensity
			0.8f//brightness
		);
	}

	public void paintComponent(Graphics g) {
		Thread[] thread=new Thread[4];
		long startTime = System.currentTimeMillis();
		Color[][] mat_color=new Color[800][800];

			thread[0]=new Thread(new Runnable(){
			            @Override
			            public void run() {
			                //do job per thread here
			                for(int row=0; row<200;row++){	
			                	for(int col=0;col<800;col++){//cols
			                		mat_color[row][col]=calculatePoint0(row,col);
			                	}
			                }
			                //System.out.println("thread_index=0,over");
			            }   
			        });
			thread[0].start();

			

			thread[1]=new Thread(new Runnable(){
			            @Override
			            public void run() {
			                //do job per thread here
			                for(int row=200; row<400;row++){	
			                	for(int col=0;col<800;col++){//cols
			                		mat_color[row][col]=calculatePoint1(row,col);
			                	}
			                }
			                //System.out.println("thread_index=1,over");
			            }   
			        });
			thread[1].start();

			thread[2]=new Thread(new Runnable(){
			            @Override
			            public void run() {
			                //do job per thread here
			                for(int row=400; row<600;row++){	
			                	for(int col=0;col<800;col++){//cols
			                		mat_color[row][col]=calculatePoint2(row,col);
			                	}
			                }
			                //System.out.println("thread_index=2,over");
			            }   
			        });
			thread[2].start();

			thread[3]=new Thread(new Runnable(){
			            @Override
			            public void run() {
			                //do job per thread here
			                for(int row=600; row<800;row++){	
			                	for(int col=0;col<800;col++){//cols
			                		mat_color[row][col]=calculatePoint3(row,col);
			                	}
			                }
			                //System.out.println("thread_index=3,over");
			            }   
			        });
			thread[3].start();

			try{
				thread[0].join();thread[1].join();thread[2].join();thread[3].join(); //wait to finish

				//paint
				for(int row=0;row<800;row++){
					for(int col=0;col<800;col++){
						printPoint((Graphics2D)g,mat_color[row][col],new Point(row,col));
					}
				}
			}catch(InterruptedException ex){
				System.out.println(ex.toString());
			}

			System.out.println("Finished in "+(System.currentTimeMillis()-startTime)+" ms");

    }
    public static void printPoint(Graphics2D frame, Color c, Point p) {
		frame.setColor(c); 
		frame.draw(new Line2D.Double(
			p.getX(), p.getY(), 
			p.getX(), p.getY()
			)
		); 
    }
}