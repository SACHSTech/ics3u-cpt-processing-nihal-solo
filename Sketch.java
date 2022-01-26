/*public class Sketch{
    import java.util.Random;
import javax.sound.sampled.spi.AudioFileReader;
import java.awt.Rectangle;
import processing.core.PApplet;
import processing.core.PImage;
public class Sketch extends PApplet {
  int w = 400;
  int h = 400;
  int carX = w/40*19;
  double fuel = 100;
  float showfuel = 100;
  int carY = h/40*28;
  float t = 0;
  int gasmas = 0;
  float rMove;
  float eX = carX;
  float eY = height + (height*20);
  float difficulty = 1;
  boolean leftPressed = false;
  boolean rightPressed = false;
  boolean allBlocksOff = false;
  boolean isRand = false;
  int[] blocks = new int[10];
  int gases = 0;
  int gasesX = 0;
  float rand = random(0,width);
  Random ra = new Random();
  int j = ra.nextInt(width);
  Random rl = new Random();
  int k = ra.nextInt(width);
  int[] rectX = {j,j,j,j,j,j,j,j,j,j};
  int[] fuelX = {k,k,k,k,k,k,k,k,k,k};
  float obY = 20;
  float typeOfCar = 0;
  float level = 0;
  float moveGround = 0;
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
   * Called once at the beginning of execution, put your size all in this method
   */
  /*public void settings() {
	// put your size call here
    size(w, h);
  }
  
  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  /*public void setup() {
    background(0);
    
    
    img = loadImage("car.png");
    explo = loadImage("explode.png");
    road = loadImage("road.png");
    bCar = loadImage("badcar.png");
    truck = loadImage("truck.png");
    needgas = loadImage("gas.png");
    textSize(36);
    
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  
 /* public void draw() {
    background(0); 
    t = t+1;   
    showfuel = Math.round(fuel);
    String f = "Fuel: "+Double.toString(showfuel) + "%";
    fuel = fuel -(0.01);
    timeBetweenScore = timeBetweenScore+1;
    timeBetweenGas = timeBetweenGas+1;
    String pointsString = "Score: "+Integer.toString(pointsInt);
    for (int i = 0; i<blocks.length; i++){
    image(road, -65, rMove- height);
    road.resize(width+130, height+(height*2));
    rMove = rMove+1;
    
    if(rMove>height){
      rMove = height/4+height/40;
    }
    }
    text(gamable, 100, 100);
    text(play, 70, 200);
    text(pointsString, width/40, 50);
    textSize(30);
    text(f, 230, 50);
    needgas.resize(30, 30);
    image(explo, eX, eY);
    explo.resize(30, 50);
    Rectangle yellow = new Rectangle(carX,carY,img.width,img.height);
    img.resize(30, 50);
    image(img, carX, carY);
    
    
      moveGround = moveGround+3;
      //if max difficulty has been reached
    if(difficulty>=9){  
      for (int i = 0; i < blocks.length; i++){
        if (easyorhard[i]<=2){
          bCar.resize(30, 50);
          image(bCar, rectX[i], blocks[i]);
          Rectangle green = new Rectangle(rectX[i],blocks[i],bCar.width,bCar.height);
          widthimg = bCar.width;
          if(yellow.intersects(green)|| fuel <=0){
            
          
        carY = height+height;
        gamable = gamable +"Game Over"+"                              ";
        eX = carX;
        eY = 280;
        isCrashed = true;
        play = play+"Press r to replay"+"                              ";
        
      }
      if (key == 'r' && isCrashed) {
        isCrashed = false;
        eY = height+height;
        carY = 280;
        gamable = "";
        pointsInt = 0;
        play = "";
        difficulty = 1;
        level = 0;
        blocks[i] = -20;
        carX = 190;
        fuel = 100;
      } 
          }
          else if(easyorhard[i]==3){
            truck.resize(30, 60);
            image(truck, rectX[i], blocks[i]);
            Rectangle grey = new Rectangle(rectX[i],blocks[i],truck.width,truck.height);
            blocks[i] = blocks[i] - 2;
            typeOfCar = 2;
            if(yellow.intersects(grey)|| fuel<=0){
              
            
          carY = height+height;
          gamable = gamable +"Game Over"+"                              ";
          eX = carX;
          eY = 280;
          isCrashed = true;
          play = play+"Press r to replay"+"                              ";
          
        }
          //  }
        if (key == 'r' && isCrashed) {
          isCrashed = false;
          eY = height+height;
          carY = 280;
          gamable = "";
          pointsInt = 0;
          play = "";
          difficulty = 1;
          level = 0;
          blocks[i] = -20;
          carX = 190;
          fuel = 100;
        } 
      }
      
    
      //car
      
      //blocks
      
      
      blocks[i]+=8;
      
      
      if (blocks[i] > height/4*3) {
        blocks[i] = 0;
        Random rand = new Random();
        int int_random = rand.nextInt(width);
        rectX[i] = int_random;
        if(carY<height && timeBetweenScore>=50){
          pointsInt = pointsInt+1;
          timeBetweenScore =0;
          
          
        }
        
        
          
      }  
      if(blocks[i]==-20){
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
    blocks[i]= blocks[i]+8;
    
    if (easyorhard[i]<=2){
    bCar.resize(30, 50);
    image(bCar, rectX[i], blocks[i]);
    Rectangle green = new Rectangle(rectX[i],blocks[i],bCar.width,bCar.height);
    widthimg = bCar.width;
    if(yellow.intersects(green)||fuel<=0){
      
    
  carY = height+height;
  gamable = gamable +"Game Over"+"                              ";
  eX = carX;
  eY = 280;
  isCrashed = true;
  play = play+"Press r to replay"+"                              ";
  
}
if (key == 'r' && isCrashed) {
  isCrashed = false;
  eY = height+height;
  carY = 280;
  gamable = "";
  pointsInt = 0;
  play = "";
  difficulty = 1;
  level = 0;
  blocks[i] = -20;
  carX = 190;
  fuel = 100;
} 
    }
    else if(easyorhard[i]==3){
      truck.resize(30, 60);
      image(truck, rectX[i], blocks[i]);
      Rectangle grey = new Rectangle(rectX[i],blocks[i],truck.width,truck.height);
      blocks[i] = blocks[i] - 2;
      typeOfCar = 2;
      if(yellow.intersects(grey)||fuel<=0){
        
      
    carY = height+height;
    gamable = gamable +"Game Over"+"                              ";
    eX = carX;
    eY = 280;
    isCrashed = true;
    play = play+"Press r to replay"+"                              ";
    
  }
    //  }
  if (key == 'r' && isCrashed) {
    isCrashed = false;
    eY = height+height;
    carY = 280;
    gamable = "";
    pointsInt = 0;
    play = "";
    difficulty = 1;
    level = 0;
    blocks[i] = -20;
    carX = 190;
    fuel = 100;
  } 
    }
    
    if (blocks[i] >= height+20) {
      blocks[i] = -20;
      Random rand = new Random();
      int int_random = rand.nextInt(width);
      rectX[i] = int_random;
      
      
      level = level + 1;
      
      if(carY<height && timeBetweenScore>=50){
        pointsInt = pointsInt+10;
        timeBetweenScore =0;
        if(pointsInt%(100)==0){
          difficulty = difficulty+1;
          level = 0;
        }
      }
    }  
    
    
    if(blocks[i]==-20){
      Random corb = new Random();
      int block_car = corb.nextInt(4);
      easyorhard[i] = block_car;
      
      
    }

  }
  if(fuel <=30 && timeBetweenGas>=50){
    gases+=8;
    if(gases>=height){
      gases = -20;
      Random gaschance = new Random();
    int gas_random = gaschance.nextInt(10);
    gasmas = gas_random;
    Random gla = new Random();
    int me_random = gla.nextInt(width);
    gasesX = me_random;
    }
      if(gasmas <=6){
        
        if(gasesX<=width/2){
          
        Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
        image(needgas, gasesX,gases);
       
        if(yellow.intersects(black)&& timeBetweenGas >=50&& fuel<=99){
          fuel++;
          timeBetweenGas = 0;
        }
        
        }
        if(gasesX>width/2){
          
          Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
          image(needgas, gasesX,gases); 
          
          if(yellow.intersects(black)&& timeBetweenGas >=50&&fuel<=99){
            fuel++;
            timeBetweenGas = 0;
  
          }
        }
      }
    }
    else if(fuel <=100&& fuel>30 && timeBetweenGas>=50){
      gases+=8;
      if(gases>=height){
        gases = -20;
        if(gases<=0){
        Random gaschance = new Random();
      int gas_random = gaschance.nextInt(10);
      gasmas = gas_random;
      Random gla = new Random();
      int me_random = gla.nextInt(width);
      gasesX = me_random;
        }
      }
      if(gasmas >=4){
        
        if(gasesX<=width/2){
          
        Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
        image(needgas, gasesX,gases);
        
        if(yellow.intersects(black)&& timeBetweenGas >=50&& fuel<=99){
    
          
            fuel++;
            timeBetweenGas = 0;
            timeGasGlitch = 0;
            gases = height+height;
            
        }
        
        }
        if(gasesX>width/2){
          
          Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);
          image(needgas, gasesX,gases); 
         
          if(yellow.intersects(black)&& timeBetweenGas >=50&&fuel<=99){
          
            
              fuel++;
            timeBetweenGas = 0;
            timeGasGlitch = 0;
            gases = height+height;
            
  
          }
        }
      }
    }

  
}


if (leftPressed && isCrashed == false) {
  carX = carX -4;
}
if (rightPressed && isCrashed == false) {
  carX = carX +4;
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
}*/