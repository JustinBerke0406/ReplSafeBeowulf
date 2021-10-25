public class Beowulf extends Player {
    public static final int startingSoldiers = 31;

    public Beowulf(Game game) {
      this(game, startingSoldiers);
    }

    public Beowulf(Game game, int customHealth) {
      super(customHealth, game);
    }

    public Card pickCard() {
      return Card.pickCard(true);
    }

    public void turnAction(Card card, int stack, int damage, Player opponent) {
      stack = Math.min(stack, numberOfCardType(card));

      for (int i = 0; i < stack; i++) {
        removeCard(indexOf(card));

        if (card.equals(Card.GIANTS_SWORD)) damage *= 2;
        else if (card.equals(Card.THREE_SOLDIERS)) {
            Game game = this.getGame();
            if (game.getDefenses() > 0) {
              if (getHealth() < 40) {
                setHealth(Math.min(40, getHealth() + 3));
                Stats.soldiersGained += 3;

                if (getHealth() == 40) Stats.reachedHpCap++;
              }
              else {
                addCard(card);
                Stats.reachedHpCap++;
              }
            }
        }
      }

      opponent.damageBy(damage);
    }
}
