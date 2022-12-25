package SpritesAndCollidables;

import Game.GameLevel;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;
import OtherClasses.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * SpritesAndCollidables.Paddle class.
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Geometry.Rectangle rectangle;
    private final Color color;
    private static final int LEFT_BORDER = 21;
    private static final int RIGHT_BORDER = 780;
    private static final int ANGLE1 = 30;
    private static final int ANGLE2 = 60;
    private static final int ANGLE3 = 300;
    private static final int ANGLE4 = 330;
    private final int speed;

    /**
     * Constructor.
     *
     * @param rect
     * @param color
     * @param keyboard
     * @param speed
     */
    public Paddle(Geometry.Rectangle rect, Color color, KeyboardSensor keyboard, int speed) {
        this.rectangle = rect;
        this.color = color;
        this.keyboard = keyboard;
        this.speed = speed;
    }

    /**
     * This method moves the paddle to the left when the left arrow is being pressed.
     *
     * @param dx the horizontal velocity (double)
     */
    public void moveLeft(double dx) {
        this.getCollisionRectangle().moveLeft(dx, this.LEFT_BORDER);
    }

    /**
     * This method moves the paddle to the right when the right arrow is being pressed.
     *
     * @param dx the horizontal velocity (double)
     */
    public void moveRight(double dx) {
        this.getCollisionRectangle().moveRight(dx, this.RIGHT_BORDER);
    }

    /**
     * This method is telling the paddle time has passes-
     * so if right or left arrows have been pressed- move accordingly.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(this.speed);
        } else if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(this.speed);
        }
    }

    /**
     * This method draws this paddle on the given surface.
     *
     * @param d - a surface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        int leftCornerX = (int) getCollisionRectangle().getUpperLeft().getX();
        int leftCornerY = (int) getCollisionRectangle().getUpperLeft().getY();
        int width = (int) getCollisionRectangle().getWidth();
        int height = (int) getCollisionRectangle().getHeight();
        d.fillRectangle(leftCornerX, leftCornerY, width, height);
    }

    /**
     * Returns this paddle`s rectangle.
     *
     * @return Geometry.Rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method checks if the collisionPoint between the paddle and the ball.
     * If the ball hits the paddle from up or down- change its vertical velocity.
     * If the ball hits the paddle from right or left- change its horizontal velocity.
     *
     * @param collisionPoint  a Geometry.Point
     * @param currentVelocity a OtherClasses.Velocity
     * @param hitter          a ball object
     * @return Velocity
     */
    public Velocity hit(Ball hitter, Geometry.Point collisionPoint, Velocity currentVelocity) {
        double velDx = currentVelocity.getDx(), velDy = currentVelocity.getDy();
        double paddleLength = this.getCollisionRectangle().getCeil().length();
        double leftCorner = this.getCollisionRectangle().getUpperLeft().getX();
        double speed;
        Velocity v = currentVelocity;

        // Check if the ball hits the upper side or downside of the paddle:
        if (this.rectangle.getCeil().contain(collisionPoint)) {
            speed = v.getSpeed(velDx, velDy);
            if (collisionPoint.getX() < leftCorner + paddleLength / 5) {
                v = Velocity.fromAngleAndSpeed(ANGLE3, speed);
            } else if (collisionPoint.getX() < leftCorner + 2 * (paddleLength / 5)) {
                v = Velocity.fromAngleAndSpeed(ANGLE4, speed);
            } else if (collisionPoint.getX() < leftCorner + 3 * (paddleLength / 5)) {
                v.setDy(velDy * -1);
            } else if (collisionPoint.getX() < leftCorner + 4 * (paddleLength / 5)) {
                v = Velocity.fromAngleAndSpeed(ANGLE1, speed);
            } else if (collisionPoint.getX() < leftCorner + 5 * (paddleLength / 5)) {
                v = Velocity.fromAngleAndSpeed(ANGLE2, speed);
            }
        }

        // Check if the ball hits the right or the left side of the paddle:
        if (this.rectangle.getRightWall().contain(collisionPoint)
                || this.rectangle.getLeftWall().contain(collisionPoint)) {
            v.setDx(velDx * -1);
        }

        return v;
    }

    /**
     * Add this paddle to the game.
     *
     * @param game a game object
     */
    public void addToGame(GameLevel game) {
        // Add the ball to the sprite collection:
        game.addSprite(this);
        // Add a reference to the game environment:
        game.addCollidable(this);
    }
}