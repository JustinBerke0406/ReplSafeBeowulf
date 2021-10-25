public enum Card {
  GIANTS_SWORD(5),
  THREE_SOLDIERS(8),
  FATIGUE(7),

  DOUBLE_DAMAGE(8),
  LOSE_DEFENSE(4),
  INJURY(8),

  EMPTY(0);

  private int weight;

  Card(int weight) {
    this.weight = weight;
  }

  public static Card pickCard(boolean isBeowulf) {
    int number = (int) (Math.random() * 20);

    if (isBeowulf) {
      if (number < GIANTS_SWORD.weight) return GIANTS_SWORD;
      else if (number < GIANTS_SWORD.weight + THREE_SOLDIERS.weight) return THREE_SOLDIERS;
      else return FATIGUE;
    }
    else {
      if (number < DOUBLE_DAMAGE.weight) return DOUBLE_DAMAGE;
      else if (number < DOUBLE_DAMAGE.weight + LOSE_DEFENSE.weight) return LOSE_DEFENSE;
      else return INJURY;
    }
  }

  public boolean equals(Card other) {
    return other.ordinal() == this.ordinal();
  }
}