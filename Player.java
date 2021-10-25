import java.util.*;

public abstract class Player {
  private int health;
  private ArrayList<Card> cards = new ArrayList<>();
  private Game game;

  public Player(int health, Game game) {
    this.health = health;
    this.game = game;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getHealth() {
    return health;
  }

  public void addCard(Card card) {
    cards.add(card);
  }

  public boolean hasCards() {
    return (cards.size() > 0);
  }

  public boolean validIndex(int index) {
    return (hasCards() && index < cards.size() && index >= 0);
  }

  public Card peekCard(int index) {
    if (validIndex(index)) return cards.get(index);
    else return Card.EMPTY;
  }

  public Card peekTopCard() {
    return peekCard(cards.size()-1);
  }

  public Card removeCard(int index) {
    if (validIndex(index)) return cards.remove(index);
    else return Card.EMPTY;
  }

  public int numberOfCardType(Card card) {
    int count = 0;

    for (int i = 0; i < cards.size(); i++)
      if (peekCard(i).equals(card)) count++;

    return count;
  }

  public int indexOf(Card card) {
    for (int i = 0; i < cards.size(); i++)
      if (cards.get(i).equals(card)) return i;

    return -1;  
  }

  public abstract Card pickCard();

  public abstract void turnAction(Card card, int stack, int damage, Player opponent);

  public void damageBy(int damage) {
    health -= damage;
  }

  public boolean isDead() {
    return (health <= 0);
  }

  public static int rollDice() {
    return (int) (Math.random() * 6) + 1;
  }

  public static int getDiceDamage(int dice) {
    return 1 + (dice-1) % 3;
  }

  public void takeTurn(Player opponent, Card use, int stack) {
    int dice = rollDice();
    int damage = getDiceDamage(dice);

    if (peekTopCard().equals(Card.FATIGUE) || peekTopCard().equals(Card.INJURY)) {
      removeCard(cards.size() - 1);
      
      damage = 0;
    }

    turnAction(use, stack, damage, opponent); 

    if (dice > 3) addCard(pickCard());
  }

  public Game getGame() {
    return game;
  }
}