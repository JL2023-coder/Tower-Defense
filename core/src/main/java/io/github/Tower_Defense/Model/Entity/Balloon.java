package io.github.Tower_Defense.Model.Entity;

public class Balloon implements IBalloon{
    private int health;
    private int posX;
    private int posY;
    private static int width = 64;
    private static int height = 64;
    private String balloonType;
    // Contains a vector with x,y where they represent direction and speed
    private int speed;
    
    public Balloon(String type, int health, int posX, int posY, int speed) {
        this.balloonType = type;
        this.health = health;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    public static Balloon newBalloon(String type, int posX, int posY){
        int health;
        int speed;
        switch (type) {
            case "base":
                health = 100;
                speed = 100;
                break;
            default:
                throw new IllegalArgumentException("Uknown type");
        }

        return new Balloon(type, health, posX, posY, speed);
    }

    public void move(float delta, char direction){
        if(direction == 'U'){
            posY += Math.round(delta * speed);
        }
        else if(direction == 'D'){
            posY += -Math.round(delta * speed);
        }
        else if(direction == 'R'){
            posX += Math.round(delta * speed);
        }
        else if(direction == 'L'){
            posX += -Math.round(delta * speed);
        }
    }

    public int getHealth(){
        return health;
    }
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

}
