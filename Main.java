class Main {
  public static void main(String[] args) {
      double games = 0;
      Stats.games = (int) games;
      Stats.roundsPerGame = new int[(int) games];

      for (int i = 0; i < games; i++) {
        new Game(30,24).begin();
      }

      System.out.println("Beowulf wins: " + Stats.beowulfWins + "    Grendel wins: " + Stats.grendelWins);
      System.out.println("Win Ratio: "+(double) Stats.beowulfWins / Stats.grendelWins);
      System.out.println("Average Soldiers Gained: " + Stats.soldiersGained/games);
      System.out.println("Average Rounds Per Game: " + Stats.totalRounds/games);
      System.out.println("All Defenses Destroyed: " + 100 * Stats.allDefensesDestroyed/games + "%");
      System.out.println("Average Defenses Destroyed: " + Stats.totalDefensesDestroyed/games);
      System.out.println("HP Limit Reached: " + 100*Stats.reachedHpCap/games + "%");

      bestHP();
    }

    public static void hpScatter() {
      int[] hp = new int[41];
      double[] ratio = new double[41];
      for (int i = 0; i <= 40; i++) {
        Stats.beowulfWins = 0;
        Stats.grendelWins = 0;

        for (int c = 0; c < 1000; c++)
          new Game(0, i).begin();

        hp[i] = i;
        ratio[i] = (double) Stats.beowulfWins / Stats.grendelWins;
      }

      for (int a = 0; a <= 40; a++) {
        System.out.print(hp[a] + " ");
      }

      System.out.println();

      for (int a = 0; a <= 40; a++) {
        System.out.print(ratio[a] + " ");
      }
    }

  public static void bestHP() {
    int gre = 20;
    int beo = 20;
    double ratio = 0;
    double rounds = 0;

    for (int a = 0; a < 41; a++) {
      for (int b = 0; b < 41; b++)
      {
        Stats.grendelWins = 0;
        Stats.beowulfWins = 0;
        Stats.totalRounds = 0;
        
        for (int c = 0; c < 10000; c++)
          new Game(a, b).begin();

        double diff = Math.abs(1-((double) Stats.beowulfWins / Stats.grendelWins));

        if (diff < Math.abs(1-ratio) && (double) Stats.totalRounds/10000 > 15) {
          ratio = ((double) Stats.beowulfWins / Stats.grendelWins);
          gre = a;
          beo = b;
          rounds = Stats.totalRounds;
        }
      }

      System.out.println((double) (100*a)/40+"%");
    } 

    System.out.println("Grendel: " +gre + "      Beowulf: "   + beo + "     Ratio: " + ratio + "       Rounds: " + rounds/10000);
  }
}