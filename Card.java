public class Card {
  private int rank;
  private int suit; // 0 = spades, 1 = heart, 2 = diamond, 3 = club

  public Card(int suit, int rank) {
    this.suit = suit;
    this.rank = rank;
  }

  public void setRank(int rank) {
    this.rank = rank;
  }

  public int getRank() {
    return rank;
  }

  public void setSuit(int suit) {
    this.suit = suit;
  }

  public int getSuit() {
    return suit;
  }

  public String toString() {
    String result = "";

    result += "Rank: " + rank + " Suit: " + suit;
    return result;
  }

}