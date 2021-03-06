package com.group0540.matchstickmenadventures.gamecode.matchstickmen;

import java.util.Random;

class ExtraordinaryManObject extends MatchstickMenObject {
  /** Indicates whether this MatchstickMenObject is moving up. */
  private boolean goingUp;

  /**
   * Constructs a new ExtraordinaryManObject.
   *
   * @param x the initial x coordinate of this MatchstickMen object
   * @param y the initial y coordinate of this MatchstickMen object
   * @param gridWidth the width of the displaying area
   * @param gridHeight the height of the displaying area
   */
  ExtraordinaryManObject(int x, int y, int gridWidth, int gridHeight) {
      super(x, y, gridWidth, gridHeight);
    this.goingUp = true;
  }

  /** Turns this ExtraordinaryManObject around, causing it to reverse direction. */
  private void turnUpDown() {
    goingUp = !goingUp;
  }

  /** Move this ExtraordinaryManObject. */
  @Override
  void move() {
    Random random = new Random();

    if (this.goingUp) {
      if (y >= super.getGridHeight() - 150) {
        turnUpDown();
      } else {
        y += random.nextInt(50);
      }
    } else {
      if (y <= 0) {
        turnUpDown();
      } else {
        y -= random.nextInt(50);
      }
    }

    if (this.isGoingRight()) {
      if (x >= super.getGridWidth() - 150) {
        turnAround();
      } else {
        x += random.nextInt(50);
      }
    } else {
      if (x <= 0) {
        turnAround();
      } else {
        x -= random.nextInt(50);
      }
    }
  }
}
