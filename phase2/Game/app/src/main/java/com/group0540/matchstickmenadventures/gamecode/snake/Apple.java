package com.group0540.matchstickmenadventures.gamecode.snake;

/** An apple in Snake. */
class Apple extends SnakeObject implements Edible {
  /** Indicates if this apple is been eaten. */
  private boolean isEaten;

  /**
   * Constructs a new apple.
   *
   * @param x the initial x coordinate of this apple
   * @param y the initial y coordinate of this apple
   * @param size the side length of this apple
   * @param shape the shape of this apple
   */
  Apple(int x, int y, int size, SnakeShape shape) {
    super(x, y, size, shape);
    this.setType(SnakeObjectType.APPLE);
    this.isEaten = false;
  }

  /**
   * Return whether this apple is eaten
   *
   * @return true if this apple is eaten, false otherwise.
   */
  public boolean isEaten() {
    return isEaten;
  }

  /** Set if this apple is eaten. */
  public void setEaten() {
    isEaten = true;
  }
}
