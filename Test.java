public class Test {

    public static void main(String[] args) {
        Player testPlayer = new Player();

        Card c1 = new Card(0,1);
        Card c2 = new Card(1,1);
        Card c6 = new Card(2, 1); 
        Card c3 = new Card(2,10);
        Card c4 = new Card(3,1);
        Card c5 = new Card(0,9);

        testPlayer.addCard(c1);
        System.out.println("User's Cards: " + testPlayer.getCardsInHand() + " total: " + testPlayer.getCurrentTotalPoints());
        testPlayer.addCard(c2);
        System.out.println("User's Cards: " + testPlayer.getCardsInHand() + " total: " + testPlayer.getCurrentTotalPoints());
        testPlayer.addCard(c3);
        System.out.println("User's Cards: " + testPlayer.getCardsInHand() + " total: " + testPlayer.getCurrentTotalPoints());
        testPlayer.addCard(c4);
        System.out.println("User's Cards: " + testPlayer.getCardsInHand() + " total: " + testPlayer.getCurrentTotalPoints());
        testPlayer.addCard(c5);
        System.out.println("User's Cards: " + testPlayer.getCardsInHand() + " total: " + testPlayer.getCurrentTotalPoints());
        
    }
}