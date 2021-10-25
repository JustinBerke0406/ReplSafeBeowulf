public class Game {
  private int defenses;

  private Beowulf beowulf;
  private Grendel grendel;

  public Game() {
    beowulf = new Beowulf(this);
    grendel = new Grendel(this);

    defenses = 4;
  }

  public Game(int grendelHP, int beowulfHP) {
    beowulf = new Beowulf(this, beowulfHP);
    grendel = new Grendel(this, grendelHP);

    defenses = 4;
  }

  public void setDefenses(int defenses) {
    this.defenses = defenses;
  }
  
  public int getDefenses() {
    return defenses;
  }

  public void begin() {
    while (!beowulf.isDead() && !grendel.isDead()) {
      int chance = (int) (Math.random() * 2);

      if (chance == 0)
        beowulf.takeTurn(grendel, beowulf.peekTopCard(), 100);
      else
        beowulf.takeTurn(grendel, Card.EMPTY, 1);

      chance = (int) (Math.random() * 2);

      if (chance == 0)
        grendel.takeTurn(beowulf, grendel.peekTopCard(), 100);
      else
        grendel.takeTurn(beowulf, Card.EMPTY, 1);

      Stats.totalRounds += 1;
    }

    if (defenses <= 0) Stats.allDefensesDestroyed++;

    if (grendel.isDead()) Stats.beowulfWins++;
    else Stats.grendelWins++;
  }
}