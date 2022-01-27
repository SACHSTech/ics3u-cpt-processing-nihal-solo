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

    // background is black
    background(0);   

    // fuel amount
    showfuel = Math.round(fuel);

    // how fuel is displayed
    String f = "Fuel: "+Double.toString(showfuel) + "%";

    // losing fuel
    fuel = fuel -(0.01);

    // determining how long it has been since you scored
    timeBetweenScore = timeBetweenScore+h/400;

    // determining how long it has been since you got gas
    timeBetweenGas = timeBetweenGas+h/400;

    // how score is displayed
    String pointsString = "Score: "+Integer.toString(pointsInt);

    // for loop for road
    for (int i = 0; i<blocks.length; i++){
    
    // draws road
    image(road, h*1625/10000*(-1), rMove- h);

    // sizes road
    road.resize(w/40*53, h*3);

    // moves road
    rMove = rMove+h/400;
    
    // makes road change position when off screen
    if(rMove>h){
      rMove = h/40*11;
    }
    }

    // writes text
    text(gamable, w/4, h/4);
    text(play, w/40*7, h/2);
    text(pointsString, w/40, h/8);
    textSize(w/40*3);
    text(f, w/40*23, h/8);

    // gas resize
    needgas.resize(w/40*3, h/40*3);

    // explosion image
    image(explo, eX, eY);

    // explosion resize
    explo.resize(w/40*3, h/40*5);

    // rectangle around car
    Rectangle yellow = new Rectangle(carX,carY,img.width,img.height);

    //car image resize
    img.resize(w/40*3, h/40*5);
    
    // car image
    image(img, carX, carY);
    


  for (int i = 0; i < difficulty; i++) {
    
    //blocks
    blocks[i]= blocks[i]+h/50;

    // if a random number from 0-3 is less than or equal to 2
    if (easyorhard[i]<=2){

    //draws and resizes a green car and rectangle around it
    bCar.resize(w/40*3, h/40*5);
    image(bCar, rectX[i], blocks[i]);
    Rectangle green = new Rectangle(rectX[i],blocks[i],bCar.width,bCar.height);

    // if the rectangle around the yellow car touches the rectangle around the green car or loses fuel or runs off the road
    if(yellow.intersects(green)||fuel<=0||carX<=0||carX+img.width>=w){
      
      // your car goes off the screen
      carY = h*2;

      // say game over
      gamable = gamable +"Game Over"+"                              ";

      // explosion is at same place as car
      eX = carX;
      eY = h/10*7;

      // boolean detecting if you crashed is true
      isCrashed = true;

      // says you can press r to replay
      play = play+"Press r to replay"+"                              ";
  
}
  

  // if you press r when you crash
  if (key == 'r' && isCrashed) {
  // you are not crashed anymore
    isCrashed = false;

  // explosion goes off screen
    eY = h*2;
  
  // car goes back to y position
    carY = h/10*7;

  // you don't see game over
    gamable = "";
  
  // you have zero points
    pointsInt = 0;

  // doesn't say press r to replay
    play = "";

  // difficulty goes back to 1
    difficulty = 1;

  // cars go to top of screen
    blocks[i] = (h/20)*(-1);

  // your car centers
    carX = w/40*19;

  // you have full fuel
    fuel = 100;
  } 
    }
    // if a random number from 0-3 is equal to 3
    else if(easyorhard[i]==3){

      //draws and resizes a grey truck and rectangle around it
      truck.resize(w/40*3,h/40*6);
      image(truck, rectX[i], blocks[i]);
      Rectangle grey = new Rectangle(rectX[i],blocks[i],truck.width,truck.height);

      // trucks go faster since they move slower
      blocks[i] = blocks[i] + h/200;

      // if the rectangle around the yellow car touches the rectangle around the grey truck or loses fuel or runs off the road
      if(yellow.intersects(grey)||fuel<=0||carX<=0||carX+img.width>=w){
        
      // same as before
        carY = h*2;
        gamable = gamable +"Game Over"+"                              ";
        eX = carX;
        eY = h/10*7;
        isCrashed = true;
        play = play+"Press r to replay"+"                              ";
    
  }
    // same as before
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
    
    // if cars are off road
    if (blocks[i] >= h/10*13) {
      blocks[i] = h/20*(-1);

      // random from 0-width-1
      Random rand = new Random();
      int int_random = rand.nextInt(w);

      // x of cars is that random
      rectX[i] = int_random;
      
      
      // if you are still alive and a certain time has been met
      if(carY<h && timeBetweenScore>=h/8){

        // you get 10 points
        pointsInt = pointsInt+10;

        // the timer goes back to zero
        timeBetweenScore =0;

        // when you reach every one hundred points
        if(pointsInt%(100)==0&& difficulty <=8){
          // an extra car comes
          difficulty = difficulty+1;
        }
      }
    }  
    
    // when the car gets to the bottom
    if(blocks[i]==h/20*(-1)){

      //a new random from 0-3 is selected
      Random corb = new Random();
      int block_car = corb.nextInt(4);

      // easyorhard equals this random
      easyorhard[i] = block_car;
      
      
    }

  }

  // if fuel is less than 30 and gas timer is more than a certain amount
  if(fuel <=30 && timeBetweenGas>=h/8){

    // makes gas move down
    gases+=h/50;

    // if gas passes the bottom
    if(gases>=h){

      // gas goes to top
      gases = h/20*(-1);

      // if gas is back at the top
      if(gases<=0){

        // random chance of gas from 0-9
        Random gaschance = new Random();
      int gas_random = gaschance.nextInt(10);
      gasmas = gas_random;

      // random gas x from 0-width-1
      Random gla = new Random();
      int me_random = gla.nextInt(w);
      gasesX = me_random;
        }
      // random chance of gas from 0-9
      Random gaschance = new Random();
    int gas_random = gaschance.nextInt(10);
    gasmas = gas_random;

    // random gas x from 0-width-1
    Random gla = new Random();
    int me_random = gla.nextInt(w);
    gasesX = me_random;
    }

      // if random from 0-9 is less than or equal to 6
      if(gasmas <=6){
        
        // draws rectangle around gas  
        Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);

        // draws gas image
        image(needgas, gasesX,gases);
       
        // if car touches gas and timer is at or above a certain number
        if(yellow.intersects(black)&& timeBetweenGas >=h/8&& fuel<=99){
          fuel++;
          timeBetweenGas = 0;
          gases = h*2;
        }
        
        
        
      }
    }

    // if fuel is in between 30 and 100
    else if(fuel <=100&& fuel>30 && timeBetweenGas>=h/8){
      // same
      gases+=h/50;

      // same
      if(gases>=h){
        gases = h/20*(-1);

      // same
        if(gases<=0){

      // same
        Random gaschance = new Random();
      int gas_random = gaschance.nextInt(10);
      gasmas = gas_random;

      // same
      Random gla = new Random();
      int me_random = gla.nextInt(w);
      gasesX = me_random;
        }
      }
      // if random from 0-9 is less than or equal to 4
      if(gasmas >=4){
        
        // same 
        Rectangle black = new Rectangle(gasesX,gases,needgas.width,needgas.height);

        // same
        image(needgas, gasesX,gases);
        
        // same
        if(yellow.intersects(black)&& timeBetweenGas >=h/8&& fuel<=99){
            fuel++;
            timeBetweenGas = 0;
            timeGasGlitch = 0;
            gases = h*2;
            
        }
        
        
        
      }
    }

  


// if not crashed and left is pressed
if (leftPressed && isCrashed == false) {

  // move left
  carX = carX -w/100;
}

// if not crashed and right is pressed
if (rightPressed && isCrashed == false) {

  // move right
  carX = carX +w/100;
}
}
/**
   * Checks to see if a certain key has been pressed
   */
  
  public void keyPressed() {

    // if you pressed left
    if (keyCode == LEFT) {
      leftPressed = true;
      rightPressed = false;
    }

    // if you pressed right
    else if (keyCode == RIGHT) {
      rightPressed = true;
      leftPressed = false;
    }
  }
  /**
   * Checks to see if a certain key has been released
   */
  public void keyReleased() {

    // if you release left
    if (keyCode == LEFT) {
      leftPressed = false;
    }

    // if you release right
    else if (keyCode == RIGHT) {
      rightPressed = false;
    }
  }
  
  // define other methods down here.
}