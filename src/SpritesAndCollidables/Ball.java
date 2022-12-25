
package SpritesAndCollidables;

import Collections.GameEnvironment;
import Game.GameLevel;
import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Interfaces.Collidable;
import Interfaces.Sprite;
import OtherClasses.CollisionInfo;
import OtherClasses.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * SpritesAndCollidables.Ball class.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnv;
    private static final int BALL_START_Y = 565;

    /**
     * constructor.
     *
     * @param x
     * @param y
     * @param r
     * @param color
     */
    public Ball(double x, double y, int r, Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        if (r < 0) {
            this.radius = -1 * r;
        }
        this.color = color;
        this.gameEnv = null;
    }

    /**
     * This method Return the ball`s radius.
     *
     * @return int - radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * This method Return the ball`s Collections.GameEnvironment.
     *
     * @return Collections.GameEnvironment object
     */
    public GameEnvironment getGameEnv() {
        return this.gameEnv;
    }

    /**
     * those methods set the ball`s boundaries.
     *
     * @param game - a Game.Game Object
     */
    public void setGameEnv(GameEnvironment game) {
        this.gameEnv = game;
    }

    /**
     * This method draw the ball on the given DrawSurface.
     *
     * @param surface - the suface to draw on it
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * This method is telling this ball time has passes- move yourself to the next point.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * This method add this ball to the game object.
     *
     * @param game a game object
     */
    public void addToGame(GameLevel game) {
        // Add a reference to the game environment:
        this.setGameEnv(game.getEnvironment());
        // Add the ball to the sprite collection:
        game.addSprite(this);
    }

    /**
     * This method set the ball`s velocity by velocity.
     *
     * @param v - OtherClasses.Velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

//    /**
//     * This method set the ball`s velocity by velocity in each direction.
//     *
//     * @param dx - velocity in the horizontal direction
//     * @param dy - velocity in the vertical direction
//     */
//    public void setVelocity(double dx, double dy) {
//        this.velocity = new Velocity(dx, dy);
//    }

    /**
     * This method finds the paddle of the game object that this ball is belong to.
     *
     * @param collidables all the collidables of the game
     * @return the paddle`s rectangle if found, otherwise- returns null
     */
    private Rectangle findPaddle(java.util.List<Collidable> collidables) {
        for (Collidable collidable : collidables) {
            if (collidable.getCollisionRectangle().getCeil().start().getY() == BALL_START_Y) {
                return collidable.getCollisionRectangle();
            }
        }
        return null;
    }

    /**
     * This method fixes the position of the ball, in case the paddle has overridden the ball in the game.
     *
     * @param upY    (double)
     * @param rightX (double)
     * @param leftX  (double)
     */
    private void fixPosition(double upY, double rightX, double leftX) {
        double distanceFromUp = this.center.getY() - upY;
        double distanceFromRight = rightX - this.center.getX();
        double distanceFromLeft = this.center.getX() - leftX;


        double min = distanceFromUp;
        if (distanceFromRight < min) {
            min = distanceFromLeft;
        }
        if (distanceFromLeft < min) {
            min = distanceFromLeft;
        }
        if (min == distanceFromUp) {
            this.center.setY(this.center.getY() - min - this.getSize());
            this.velocity.setDy(this.velocity.getDy() * -1);
        } else if (min == distanceFromRight) {
            this.center.setX(this.center.getX() + min + this.getSize());
            this.velocity.setDx(this.velocity.getDx() * -1);
        } else if (min == distanceFromLeft) {
            this.center.setX(this.center.getX() - min - this.getSize());
            this.velocity.setDx(this.velocity.getDx() * -1);
        }
    }

    /**
     * This method is responsible to move the ball one step further according to the frame limits
     * and obstacles of the game.
     * It checks if the ball is going to hit the blocks or the paddle, and if so, change its velocity direction,
     * accordingly.
     * If the ball doesn't hit anything on its next step- then move it one step with its current velocity.
     */
    public void moveOneStep() {
        // Computing the ball trajectory:
        Point endOfTrajectory = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, endOfTrajectory);

        // Checking (using the game environment) if moving on this trajectory will hit anything:
        GameEnvironment gameEnv = this.getGameEnv();
        CollisionInfo collision = gameEnv.getClosestCollision(trajectory);
        if (collision.collisionObject() == null) {
            this.center = velocity.applyToPoint(this.center);
            return;
        }
        Collidable obj = collision.collisionObject();
        Point collisionPoint = collision.collisionPoint();

        // If there is a hit, so move the ball to "almost" hit point:
        Velocity vel = obj.hit(this, collisionPoint, this.velocity);
        // Check if the ball is in the paddle and if so get it out:
        Rectangle paddleRect = findPaddle(this.getGameEnv().getColliables());
        if (paddleRect != null && this.center.getY() >= BALL_START_Y
                && this.center.getX() >= paddleRect.getUpperLeft().getX()
                && this.center.getX() <= paddleRect.getUpperLeft().getX() + paddleRect.getWidth()
        ) {
            fixPosition(paddleRect.getUpperLeft().getY(), paddleRect.getUpperLeft().getX()
                    + paddleRect.getWidth(), paddleRect.getUpperLeft().getX());
        }

        boolean right = this.velocity.getDx() * -1 == vel.getDx();
        boolean left = vel.getDx() * -1 == this.velocity.getDx();
        boolean top = this.velocity.getDy() * -1 == vel.getDy();
        boolean bottom = vel.getDy() * -1 == this.velocity.getDy();


        // if the ball hits the upper part of the rectangle:
        if (top) {
            if (this.center.getY() <= obj.getCollisionRectangle().getCeil().start().getY()) {
                this.center.setY(collisionPoint.getY() - this.getSize());
            }
        }
        // if the ball hits the downside part of the rectangle:
        if (bottom) {
            if (this.center.getY() >= obj.getCollisionRectangle().getFloor().start().getY()) {
                this.center.setY(collisionPoint.getY() + this.getSize());
            }
        }
        // if the ball hits the right part of the rectangle:
        if (right) {
            if (this.center.getX() >= obj.getCollisionRectangle().getRightWall().start().getX()) {
                this.center.setX(collisionPoint.getX() + this.getSize());
            }
        }
        // if the ball hits the left part of the rectangle:
        if (left) {
            if (this.center.getX() <= obj.getCollisionRectangle().getLeftWall().start().getX()) {
                this.center.setX(collisionPoint.getX() - this.getSize());
            }
        }
        this.setVelocity(vel);
    }

    /**
     * This method will remove this ball from the game`s sprites collection.
     *
     * @param game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
