package SpritesAndCollidables;

import Game.GameLevel;
import Geometry.Point;
import Interfaces.Collidable;
import Interfaces.HitListener;
import Interfaces.HitNotifier;
import Interfaces.Sprite;
import OtherClasses.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * SpritesAndCollidables.Block class.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Geometry.Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners = new ArrayList<HitListener>();

    /**
     * Constructor.
     *
     * @param rect
     * @param color
     */
    public Block(Geometry.Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
    }

    /**
     * Returns this block rectangle.
     *
     * @return Geometry.Rectangle
     */
    public Geometry.Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * This method is drawing this SpritesAndCollidables.Block on a given surface.
     *
     * @param d a surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        // Draw each block:
        d.setColor(this.color);
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getHeight());
        d.setColor(Color.BLACK);
        // Draw top limit:
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), (int) getCollisionRectangle().getWidth(), 1);
        // Draw bottom limit:
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY() + (int) getCollisionRectangle().getHeight(),
                (int) getCollisionRectangle().getWidth(), 1);
        // Draw right limit:
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX() + (int) getCollisionRectangle().getWidth(),
                (int) getCollisionRectangle().getUpperLeft().getY(), 1,
                (int) getCollisionRectangle().getHeight());
        // Draw left limit:
        d.fillRectangle((int) getCollisionRectangle().getUpperLeft().getX(),
                (int) getCollisionRectangle().getUpperLeft().getY(), 1,
                (int) getCollisionRectangle().getHeight());
    }

    /**
     * will be written on the next assignment.
     */
    @Override
    public void timePassed() {

    }

    /**
     * This method adds this block to the game object.
     *
     * @param game - Game.Game object
     */
    public void addToGame(GameLevel game) {
        // Add the ball to the sprite collection:
        game.addSprite(this);
        // Add a reference to the game environment:
        game.addCollidable(this);
    }

    /**
     * This method checks if the collisionPoint between the block and the ball.
     * If the ball hits the block from up or down- change its vertical velocity.
     * If the ball hits the block from right or left- change its horizontal velocity.
     *
     * @param collisionPoint  a Geometry.Point
     * @param currentVelocity a OtherClasses.Velocity
     * @param hitter          a ball object
     * @return new OtherClasses.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double velDx = currentVelocity.getDx(), velDy = currentVelocity.getDy();
        // Check if the ball hits the upper side of the block:
        if (this.rectangle.getCeil().contain(collisionPoint)) {
            velDy *= -1;
        }
        // Check if the ball hits the lower side of the block:
        if (this.rectangle.getFloor().contain(collisionPoint)) {
            velDy *= -1;
        }
        // Check if the ball hits the right side of the block:
        if (this.rectangle.getRightWall().contain(collisionPoint)) {
            velDx *= -1;
        }
        // Check if the ball hits the left side of the block:
        if (this.rectangle.getLeftWall().contain(collisionPoint)) {
            velDx *= -1;
        }
        this.notifyHit(hitter);

        return new Velocity(velDx, velDy);
    }

    /**
     * This method removes current block from the game, by remove it from the game collidables and sprites collection.
     *
     * @param game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * This method adds an Interfaces.HitListener to this block`s listeners list.
     *
     * @param hl
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * This method removes an Interfaces.HitListener from this block`s listeners list.
     *
     * @param hl
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * This method notifies when ever this block gets hit by a ball, and notify about this hit event to all of this
     * block`s listeners list.
     *
     * @param hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
