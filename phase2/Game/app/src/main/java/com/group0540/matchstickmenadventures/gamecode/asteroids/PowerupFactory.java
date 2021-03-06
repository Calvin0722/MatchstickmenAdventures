package com.group0540.matchstickmenadventures.gamecode.asteroids;

class PowerupFactory {
  private PowerupFactory() {}

  /**
   * Returns a random powerup.
   *
   * @param x x coordinate of powerup.
   * @param y y coordinate of powerup.
   * @param vX velocity in the x direction of powerup.
   * @param vY velocity in the y direction of powerup.
   * @param angle angle powerup is rotated.
   * @param collisionRadius radius of powerup
   * @return random powerup
   */
  static Asteroid getRandomPowerup(
      double x,
      double y,
      double vX,
      double vY,
      double angle,
      double collisionRadius) {
    int powerupType = (int) (Math.random() * 3);
    if (powerupType == 0) {
      return new ExplosivePowerupAsteroid(x, y, vX, vY, angle, collisionRadius, 36);
    } else if (powerupType == 1) {
      return new AdditionalLifePowerupAsteroid(x, y, vX, vY, angle, collisionRadius);
    } else {
      return new WeaponPowerupAsteroid(x, y, vX, vY, angle, collisionRadius);
    }
  }
}
