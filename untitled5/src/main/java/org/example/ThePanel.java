package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class ThePanel extends JPanel {
    private BufferedImage bufferedImage;
    private Rectangle rectangle;
    private List<Point> passedPoints;
    private List<Rectangle> obstacles;
    public static final int SQUARE_WIDTH = 50;
    public static final int SQUARE_HEIGHT = 50;
    public static final int OBSTACLE_COUNT = 5;

    public ThePanel() {
        setLayout(new BorderLayout());
        this.setSize(400, 400);
        this.setVisible(true);
        this.setFocusable(true);
        this.addMouseListener(new MyMouseListener(this));
        this.bufferedImage = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    paintImage();
                    repaint();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.addKeyListener(new MyKeyboardListener(this));

        this.rectangle = new Rectangle(0, 0, SQUARE_WIDTH, SQUARE_HEIGHT);
        this.passedPoints = new ArrayList<Point>();
        this.passedPoints.add(new Point(0,0));
        this.obstacles = new ArrayList<Rectangle>();
        addObstacles();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bufferedImage, 0, 0, this);
        g.setColor(Color.PINK);
        for(Rectangle r : obstacles) {
            g.fillRect(r.x, r.y, SQUARE_WIDTH, SQUARE_HEIGHT);
        }
        g.setColor(Color.green);
        for(Point p : passedPoints) {
            g.fillRect(p.x, p.y, SQUARE_WIDTH, SQUARE_HEIGHT);
        }
        g.setColor(Color.red);
        g.fillRect((int) this.rectangle.getX(), (int) this.rectangle.getY(), (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());

    }

    public boolean checkObstacleCollision(int x, int y) {
        Rectangle tempRect = new Rectangle(rectangle.x + x, rectangle.y + y, rectangle.width, rectangle.height);
        for(Rectangle r : obstacles) {
            if(tempRect.intersects(r)) {
                return true;
            }
        }
        return false;
    }

//    public boolean checkObstacleCollision(int x, int y){
//        boolean collision = false;
//        for(Rectangle r : obstacles) {
//            if(this.rectangle.getX()+x == r.getX() && this.rectangle.getY()+y == r.getY()){
//                collision = true;
//            }
//        }
//        return collision;
//    }

    public void addObstacles(){
        int y = 100;
        int x = 250;
        for (int i = 0; i<OBSTACLE_COUNT; i++){
            this.obstacles.add(new Rectangle(x, y, SQUARE_WIDTH, SQUARE_HEIGHT));
            y += SQUARE_HEIGHT;
            x += SQUARE_WIDTH;
        }

    }

    public void addPassedPoint(Point point) {
        this.passedPoints.add(point);
    }

    public void paintImage(){
        int blue = 200;
        int red = 200;
        int green = 100;
        Color newColor = new Color(red, green, blue);
        for(int x = 0; x < this.bufferedImage.getWidth(); x++){
            for(int y = 0; y < this.bufferedImage.getHeight(); y++){
                this.bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void unPaintImage(){
        int blue = 255;
        int red = 255;
        int green = 255;
        Color newColor = new Color(red, green, blue);
        for(int x = 0; x < this.bufferedImage.getWidth(); x++){
            for(int y = 0; y < this.bufferedImage.getHeight(); y++){
                this.bufferedImage.setRGB(x, y, newColor.getRGB());
            }
        }
    }

    public void setRectangleX(int x) {
        if((int) this.rectangle.getX() + x >= 0 &&
                this.rectangle.getX() + x < this.getWidth() - this.rectangle.getWidth() &&
                !checkObstacleCollision(x, 0)) {
            this.rectangle.setLocation(new Point((int) (this.rectangle.getX() + x), (int) this.rectangle.getY()));
        }
    }

    public void setRectangleY(int y) {
        if((int) this.rectangle.getY() + y >= 0 &&
                this.rectangle.getY() + y < this.getHeight() - this.rectangle.getHeight() &&
                !checkObstacleCollision(0, y)) {
            this.rectangle.setLocation(new Point((int) (this.rectangle.getX()), (int) this.rectangle.getY() + y));
        }
    }

//    public void setRectangleX(int x){
//        if((int) this.rectangle.getX()+x >= 0 && this.rectangle.getX()+x < this.getWidth() - this.rectangle.getWidth()) {
//            this.rectangle.setLocation(new Point((int) (this.rectangle.getX() + x), (int) this.rectangle.getY()));
//        }
//    }
//    public void setRectangleY(int y){
//        if((int) this.rectangle.getY()+y >= 0 && this.rectangle.getY()+y < this.getHeight()-this.rectangle.getHeight()) {
//            this.rectangle.setLocation(new Point((int) (this.rectangle.getX()), (int) this.rectangle.getY() + y));
//        }
//    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public List<Point> getPassedPoints() {
        return passedPoints;
    }

    public void setPassedPoints(List<Point> passedPoints) {
        this.passedPoints = passedPoints;
    }

    public List<Rectangle> getObstacles() {
        return obstacles;
    }

    public void setObstacles(List<Rectangle> obstacles) {
        this.obstacles = obstacles;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
