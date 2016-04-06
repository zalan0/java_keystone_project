


import java.util.Random;

import processing.core.*;

public class MyProcessingSketch extends PApplet{
	
	Stripe[] stripes = new Stripe[50];
	Random random = new Random();
	
	public static void main(String args[] ) {
		PApplet.main(new String[] {"--present", "MyProcessingSketch"});
	}
	
	public void settings() {
//		size(600, 600);
		fullScreen();
//		smooth(8); // 8x anti-aliasing
	}
	
	public void setup() {
//		background(0);
		for(int i = 0; i < stripes.length; i++) {
			stripes[i] = new Stripe(this);
		}
		
	}
	
	public void draw() {
		int randR = random.nextInt(256);
		int randG = random.nextInt(256);
		int randB = random.nextInt(256);
		
		background(0);
		stroke(100);
		fill(randR, randG, randB);
		if(mousePressed) {
//			line(mouseX, mouseY, pmouseX, pmouseY);
			ellipse(mouseX, mouseY, 50, 50);
		}
		
		for (int i = 0; i < stripes.length; i++) {
			stripes[i].move();
			stripes[i].display();
		}
		
	}

}
