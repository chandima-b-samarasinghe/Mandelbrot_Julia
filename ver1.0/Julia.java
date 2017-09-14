/**
	Author	: Chandima B Samarasinghe (e14305)
	Date 	: 31th Aug 2017
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

public class Julia extends JPanel{
	private static double cr,ci,ds;
	private static int iterations;

	public Julia(double arg_cr,double arg_ci,int arg_iter){
		cr=arg_cr; ci=arg_ci; iterations=arg_iter;
		init();
		System.out.println("Processing...");
	}
	public void init(){
		setSize(800,800); //until the frame has no size, the painComponent will not invoke :v
		setPreferredSize(new Dimension(800,800));
		ds=2.0/799;
	}

	public static Color calculatePoint0(int row,int col){
		double zr,zrc,zi,abs_z2;int iteration=0;
		zr=-1+col*ds; zi=1-row*ds; //z0

		zrc=zr;
		zr=zr*zr-zi*zi+cr;
		zi=2*zrc*zi + ci;			
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
			ite*360, //color map [0,360]
			0.9f,//saturation, //intensity [0,1]
			0.9f//brightness [0,1]
		);
	}
	public static Color calculatePoint1(int row,int col){
		double zr,zrc,zi,abs_z2;int iteration=0;
		zr=-1+col*ds; zi=1-row*ds; //z0

		zrc=zr;
		zr=zr*zr-zi*zi+cr;
		zi=2*zrc*zi + ci;			
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
			ite*360, //color map [0,360]
			0.9f,//saturation, //intecity [0,1]
			0.9f//brightness [0,1]
		);
	}
	public static Color calculatePoint2(int row,int col){
		double zr,zrc,zi,abs_z2;int iteration=0;
		zr=-1+col*ds; zi=1-row*ds; //z0

		zrc=zr;
		zr=zr*zr-zi*zi+cr;
		zi=2*zrc*zi + ci;			
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
			ite*360, //color map [0,360]
			0.9f,//saturation, //intecity [0,1]
			0.9f//brightness [0,1]
		);
	}
	public static Color calculatePoint3(int row,int col){
		double zr,zrc,zi,abs_z2;int iteration=0;
		zr=-1+col*ds; zi=1-row*ds; //z0

		zrc=zr;
		zr=zr*zr-zi*zi+cr;
		zi=2*zrc*zi + ci;			
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
			ite*360, //color map [0,360]
			0.9f,//saturation, //intecity [0,1]
			0.9f//brightness [0,1]
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
			                		//printPoint((Graphics2D)graphics,new Color(200,0,255),new Point(row,col));
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