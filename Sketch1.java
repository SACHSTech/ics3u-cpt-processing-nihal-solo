import java.util.Random;
import javax.sound.sampled.spi.AudioFileReader;
import java.awt.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
public class Sketch1 extends PApplet {
  int w = 400;
  int h = 400;
  int carX = w/40*19;
  double fuel = 100;
  float showfuel = 100;
  int carY = h/40*28;
  
  int gasmas = 0;
  float rMove;
  float eX = carX;
  float eY = h + (h*20);
  float difficulty = 1;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean allBlocksOff = false;
  boolean isRand = false;
  int[] blocks = new int[10];
  int gases = 0;
  int gasesX = 0;
  float rand = random(0,w);
  Random ra = new Random();
  int j = ra.nextInt(w);
  int k = ra.nextInt(w);
  int[] rectX = {j,j,j,j,j,j,j,j,j,j};
  int[] fuelX = {k,k,k,k,k,k,k,k,k,k};
  String gamable ="";
  String play ="";
  int pointsInt = 0;
  float[] easyorhard = {0,0,0,0,0,0,0,0,0,0};
  int fueltanks;
  float timeBetweenScore = 0;
  float timeBetweenGas = 0;
  float timeGasGlitch = 0;
  float useless = 0;
  boolean isCrashed = false;
  int widthimg = 0;
  int gg = 0;
  PImage img;
  PImage explo;
  PImage road;
  PImage bCar;
  PImage truck;
  PImage needgas;
	
  /**
   * Called at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// size
    size(w, h);
  }
  
  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    //backgroung colour
    background(0);
    
    //image loading
    img = loadImage("car.png");
    explo = loadImage("explode.png");
    road = loadImage("road.png");
    bCar = loadImage("badcar.png");
    truck = loadImage("truck.png");
    needgas = loadImage("gas.png");
    //text size
    textSize(w/100*9);
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  
  public void draw() {
    background(0);    
    showfuel = Math.round(fuel);
    String f = "Fuel: "+Double.toString(showfuel) + "%";
    fuel = fuel -(0.01);
    timeBetweenScore = timeBetweenScore+h/400;
    timeBetweenGas = timeBetweenGas+h/400;
    String pointsString = "Score: "+Integer.toString(pointsInt);
    for (int i = 0; i<blocks.length; i++){
    image(road, h*1625/10000*(-1), rMove- h);
    road.resize(w/40*53, h*3);
    rMove = rMove+h/400;
    
    if(rMove>h){
      rMove = h/40*11;
    }
    }
    text(gamable, w/4, h/4);
    text(play, w/40*7, h/2);
    text(pointsString, w/40, h/8);
    textSize(w/40*3);
    text(f, w/40*23, h/8);
    needgas.resize(w/40*3, h/40*3);
    image(explo, eX, eY);
    explo.resize(w/40*3, h/40*5);
    Rectangle yellow = new Rectangle(carX,carY,img.width,img.height);
    img.resize(w/40*3, h/40*5);
    image(img, carX, carY);
    
    
      //if max difficulty has been reached
    if(difficulty>=9){  
      for (int i = 0; i < blocks.length; i++){
        if (easyorhard[i]<=2){
          bCar.resize(w/40*3, h/40*5);
          image(bCar, rectX[i], blocks[i]);
          Rectangle green = new Rectangle(rectX[i],blocks[i],bCar.width,bCar.height);
          widthimg = bCar.width;
          if(yellow.intersects(green)|| fuel <=0){
            
          
        carY = h*2;
        gamable = gamable +"Game Over"+"                              ";
        eX = carX;
        eY = h/10*7;
        isCrashed = true;
        play = play+"Press r to replay"+"                              ";
        
      }
      if (key == 'r' && isCrashed) {
        isCrashed = false;
        eY = h*2;
        carY = h/10*7;
        gamable = "";
        pointsInt = 0;
        play = "";
        difficulty = 1;
        blocks[i] = (h/20)*(-1);
        carX = w/40*19;
        fuel = 100;
      } 
          }
          else if(easyorhard[i]==3){
            truck.resize(w/40*3, h/40*6);
            image(truck, rectX[i], blocks[i]);
            Rectangle grey = new Rectangle(rectX[i],blocks[i],truck.width,truck.height);
            blocks[i] = blocks[i] + h/200;
            if(yellow.intersects(grey)|| fuel<=0){
              
            
              carY = h*2;
              gamable = gamable +"Game Over"+"                              ";
              eX = carX;
              eY = h/10*7;
              isCrashed = true;
              play = play+"Press r to replay"+"                              ";
          
        }
          //  }
          if (key == 'r' && isCrashed) {
            isCrashed = false;
            eY = h*2;
            carY = h/10*7;
            gamable = "";
            pointsInt = 0;
            play = "";
            difficulty = 1;
            blocks[i] = (h/20)*(-1);
            carX = w/40*19;
            fuel = 100;
          } 
      }
      
    
      //car
      
      //blocks
      
      
      blocks[i]+=h/50;
      
      
      if (blocks[i] > h/4*3) {
        blocks[i] = 0;
        Random rand = new Random();
        int int_random = rand.nextInt(w);
        rectX[i] = int_random;
        if(carY<h && timeBetweenScore>=h/8){
          pointsInt = pointsInt+h/400;
          timeBetweenScore =0;
          
          
        }
        
        
          
      }  
      if(blocks[i]==h/20*(-1)){
        Random corb = new Random();
        int block_car = corb.nextInt(4);
        easyorhard[i] = block_car;
      }
        
  
}
    }


else{
  for (int i = 0; i < difficulty; i++) {
    //car  
    
    //blocks
    blocks[i]= blocks[i]+h/50;
    
    if (easyorhard[i]<=2){
    bCar.resize(w/40*3, h/40*5);
    image(bCar, rectX[i], blocks[i]);
    Rectangle green = new Rectangle(rectX[i],blocks[i],bCar.width,bCar.height);
    widthimg = bCar.width;
    if(yellow.intersects(green)||fuel<=0){
      
    
      carY = h*2;
      gamable = gamable +"Game Over"+"                              ";
      eX = carX;
      eY = h/10*7;
      isCrashed = true;
      play = play+"Press r to replay"+"                              ";
  
}
  //  }
  if (key == 'r' && isCrashed) {
    isCrashed = false;
    eY = h*2;
    carY = h/10*7;
    gamable = "";
    pointsInt = 0;
    play = "";
    difficulty = 1;
    blocks[i] = (h/20)*(-1);
    carX = w/40*19;
    fuel = 100;
  } 
    }
    else if(easyorhard[i]==3){
      truck.resize(w/40*3,h/40*6);
      image(truck, rectX[i], blocks[i]);
      Rectangle grey = new Rectangle(rectX[i],blocks[i],truck.width,truck.height);
      blocks[i] = blocks[i] + h/200;
      if(yellow.intersects(grey)||fuel<=0){
        
      
        carY = h*2;
        gamable = gamable +"Game Over"+"                              ";
        eX = carX;
        eY = h/10*7;
        isCrashed = true;
        play = play+"Press r to replay"+"                              ";
    
  }
    //  }
    if (key == 'r' && isCrashed) {
      isCrashed = false;
      eY = h*2;
      carY = h/10*7;
      gamable = "";
      pointsInt = 0;
      play = "";
      difficulty = 1;
      blocks[i] = (h/20)*(-1);
      carX = w/40*19;
      fuel = 100;
    } 
    }
    
    if (blocks[i] >= h/10*13) {
      blocks[i] = h/20*(-1);
      Random rand = new Random();
      int int_random = rand.nextInt(w);
      rectX[i] = int_random;
      
      
      
      if(carY<h && timeBetweenScore>=h/8){
        pointsInt = pointsInt+h/40;
        timeBetweenScore =0;
        if(pointsInt%(100)==0){
          difficulty = difficulty+h/400;
        }
      }
    }  
    
    
    if(blocks[i]==h/20*(-1)){
      Random corb = new Random();
      int block_car = corb.nextInt(4);
      easyorhard[i] = block_car;
      
      
    }

  }
  if(fuel <=30 && timeBetweenGas>=h/8){
    gases+=h/50;
    if(gases>=h){
      gases = h/20*(-1);
      Random gaschance = new Random();
    int gas_random = gaschance.nextInt(10);
    gasmas = gas_random;
    Random gla = new Random();
    int me_random = gla.nextInt(w);
    gasesX = me_random;
    }
      if(gasmas <=6){
        
        if(gasesX<=w/2){
          
        Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
        image(needgas, gasesX,gases);
       
        if(yellow.intersects(black)&& timeBetweenGas >=h/8&& fuel<=99){
          fuel++;
          timeBetweenGas = 0;
        }
        
        }
        if(gasesX>w/2){
          
          Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
          image(needgas, gasesX,gases); 
          
          if(yellow.intersects(black)&& timeBetweenGas >=h/8&&fuel<=99){
            fuel++;
            timeBetweenGas = 0;
  
          }
        }
      }
    }
    else if(fuel <=100&& fuel>30 && timeBetweenGas>=h/8){
      gases+=h/50;
      if(gases>=h){
        gases = h/20*(-1);
        if(gases<=0){
        Random gaschance = new Random();
      int gas_random = gaschance.nextInt(10);
      gasmas = gas_random;
      Random gla = new Random();
      int me_random = gla.nextInt(w);
      gasesX = me_random;
        }
      }
      if(gasmas >=4){
        
        if(gasesX<=w/2){
          
        Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
        image(needgas, gasesX,gases);
        
        if(yellow.intersects(black)&& timeBetweenGas >=h/8&& fuel<=99){
    
          
            fuel++;
            timeBetweenGas = 0;
            timeGasGlitch = 0;
            gases = h*2;
            
        }
        
        }
        if(gasesX>w/2){
          
          Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
          image(needgas, gasesX,gases); 
         
          if(yellow.intersects(black)&& timeBetweenGas >=h/8&&fuel<=99){
          
            
              fuel++;
            timeBetweenGas = 0;
            timeGasGlitch = 0;
            gases = h*2;
            
  
          }
        }
      }
    }

  
}


if (leftPressed && isCrashed == false) {
  carX = carX -w/100;
}
if (rightPressed && isCrashed == false) {
  carX = carX +w/100;
}
}

  
  public void keyPressed() {
    if (keyCode == LEFT) {
      leftPressed = true;
      rightPressed = false;
    }
    else if (keyCode == RIGHT) {
      rightPressed = true;
      leftPressed = false;
    }
  }
  
  public void keyReleased() {
    if (keyCode == LEFT) {
      leftPressed = false;
    }
    else if (keyCode == RIGHT) {
      rightPressed = false;
    }
  }
  
  // define other methods down here.
}