import java.util.Scanner;
import java.util.List;
public class Poker
{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to Poker!");
        System.out.println("What's your name?");
        String name = scan.nextLine();
        Player p = new Player(name);
        Player c = new Player("Computer");
        int round =1;
        boolean playing = true;
        int win=0;
        int lose=0;
        int tie=0;
        while (playing ==true){
         
            Deck deck = new Deck();
            for (int i=0; i<1000; i++){
                deck.riffleShuffle();
            }
            for (int i=0; i<5; i++){
                p.getHand().add(deck.getDeck().pop());
                c.getHand().add(deck.getDeck().pop());
            }
            p.sortHand();
            System.out.println(p.getName() + " - " + p.getHand());
            System.out.println("What indexes would you like to replace?");
            String indexes = scan.nextLine();
            for (int i=0; i<indexes.length(); i++){
                int index = Integer.parseInt(indexes.substring(i,i+1));
                Card draw = deck.getDeck().pop();
                p.getHand().remove(index);
                p.getHand().add(index, draw);
            }
            p.sortHand();
            System.out.println(p.getName() + " - " + p.getHand());
            c.sortHand();
            System.out.println(c.getName() + " - " + c.getHand());
            int pHandType = getHandType(p);
            int cHandType = getHandType(c);
            String PwinType = "";
            String CwinType = "";
            if (pHandType==1){
                PwinType = "Royal Flush";
            }
            else if (pHandType==2){
                PwinType = "Straight Flush";
            }
            else if (pHandType==3){
                PwinType = "Four of a Kind";
            }
            else if (pHandType==4){
                PwinType = "Full House";
            }
            else if (pHandType==5){
                PwinType = "Flush";
            }
            else if (pHandType==6){
                PwinType = "Straight";
            }
            else if (pHandType==7){
                PwinType = "Three of a Kind";
            }
            else if (pHandType==8){
                PwinType = "Two Pair";
            }
            else if (pHandType==9){
                PwinType = "Pair";
            }
            else{
                PwinType = "High Card";
            }

            if (cHandType==1){
                CwinType = "Royal Flush";
            }
            else if (cHandType==2){
                CwinType = "Straight Flush";
            }
            else if (cHandType==3){
                CwinType = "Four of a Kind";
            }
            else if (cHandType==4){
                CwinType = "Full House";
            }
            else if (cHandType==5){
                CwinType = "Flush";
            }
            else if (cHandType==6){
                CwinType = "Straight";
            }
            else if (cHandType==7){
                CwinType = "Three of a Kind";
            }
            else if (cHandType==8){
                CwinType = "Two Pair";
            }
            else if (cHandType==9){
                CwinType = "Pair";
            }
            else{
                CwinType = "High Card";
            }

            if (pHandType<cHandType){
                System.out.println("You win! A " + PwinType + " of " + p.getHand() + " beats a " + CwinType + " of " + c.getHand());
                win++;
            }
            else if (pHandType>cHandType){
                System.out.println("You lose! A " + PwinType + " of " + p.getHand() + " loses to a " + CwinType + " of " + c.getHand());
                lose++;
            }
            else{
                System.out.println("You tie! A " + PwinType + " of " + p.getHand() + " equals a " + CwinType + " of " + c.getHand());
                tie++;
            }

            System.out.println("Do you wanna play again? Type y or n");

            String playagain = scan.nextLine();

            if (playagain.equalsIgnoreCase("y")){
                System.out.println("Let's play again!!!");
                p.getHand().clear();
                c.getHand().clear();
                round++;
            }
            else {
                System.out.println("Thanks for playing!");
                System.out.println("You played " + round + " rounds; and you won - " + win + ", you lost - " + lose + ", you tie - " + tie);
                playing = false;
            }
        }
    }

    public static int getHandType(Player p){
        int flushValue = hasFlush(p.getHand());
        int twoPairValue = hasTwoPair(p.getHand());
        int pairValue = hasPair(p.getHand());
        int straightFlush = hasStraightFlush(p.getHand());
        int royalFlush = hasRoyalFlush(p.getHand());
        int fourKind = hasFourKind(p.getHand());
        int fullHouse = hasFullHouse(p.getHand());
        int straight = hasStraight(p.getHand());
        int threeKind = hasThreeKind(p.getHand());
        if (royalFlush != -1){
            return 1;
        }
        else if (straightFlush != -1){
            return 2;
        }
        else if (fourKind != -1){
            return 3;
        }
        else if (fullHouse != -1){
            return 4;
        }
        else if (flushValue != -1){
            return 5;
        }
        else if (straight != -1){
            return 6;
        }
        else if (threeKind != -1){
            return 7;
        }
        else if (twoPairValue != -1){
            return 8;
        }
        else if (pairValue != -1){
            return 9;
        }
        else{
            return 10;
        }

    }

    public static int hasPair(List<Card> hand){
        for (int i=0; i<4; i++){
            if (hand.get(i).getValue()==hand.get(i+1).getValue()){
                return hand.get(i).getValue();
            }
        }
        return -1;
    }

    public static int hasTwoPair(List<Card> hand){
        if (hand.get(0).getValue()==hand.get(1).getValue() && hand.get(2).getValue()== hand.get(3).getValue()){
            return hand.get(3).getValue();
        }
        if (hand.get(0).getValue()==hand.get(1).getValue() && hand.get(3).getValue()== hand.get(4).getValue()){
            return hand.get(4).getValue();
        }
        if (hand.get(1).getValue()==hand.get(2).getValue() && hand.get(2).getValue()== hand.get(3).getValue()){
            return hand.get(2).getValue();
        }
        return -1;
    }

    public static int hasFlush(List<Card> hand){
        for (int i=0; i<4; i++){
            if (!hand.get(i).getSuit().equals(hand.get(i+1).getSuit())){
                return -1;
            }
        }
        return hand.get(4).getValue();
    }

    public static int hasStraightFlush(List<Card> hand){
        boolean hasFlush = true;
        for (int i=0; i<4; i++){
            if (!hand.get(i).getSuit().equals(hand.get(i+1).getSuit())){
                hasFlush = false;
            }
        }
        if (hasFlush==true && hand.get(0).getValue()+1==hand.get(1).getValue() && hand.get(1).getValue()+1== hand.get(2).getValue() && hand.get(2).getValue()+1== hand.get(3).getValue()){
            return hand.get(0).getValue();
        }
        return -1;
    }

    public static int hasRoyalFlush(List<Card> hand){
        boolean hasFlush = true;
        for (int i=0; i<4; i++){
            if (!hand.get(i).getSuit().equals(hand.get(i+1).getSuit())){
                hasFlush = false;
            }
        }
        if (hasFlush ==true && hand.get(0).getValue()==10){
            if (hand.get(1).getValue()==11){
                if (hand.get(2).getValue()==12){
                    if (hand.get(3).getValue()==13){
                        if (hand.get(4).getValue()==1  ){
                            return 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static int hasFourKind(List<Card> hand){
        int k=0;
        for (int i=0; i<4; i++){
            k=0;
            for (int j=i+1; j<hand.size()-1; j++){
                if (j==i){
                    k++;
                }
            }
            if (k==3){
                return 1;
            }
        }

        return -1;
    }

    public static int hasFullHouse(List<Card> hand){
        int k=0;
        boolean hasFull = true;
        for (int i=0; i<4; i++){
            k=0;
            for (int j=i+1; j<hand.size()-1; j++){
                if (j==i){
                    k++;
                }
            }
            if (k==2){
                hasFull = true;
                break;
            }
        }
        if (hasFull==true){
            for (int i=0; i<4; i++){
                for (int j=i+1; j<hand.size()-1; j++){
                    if(i==j){
                        return 1;
                    }
                }
            }
        }
        return -1;
    }

    public static int hasStraight(List<Card> hand){
        if (hand.get(0).getValue()==hand.get(1).getValue() && hand.get(1).getValue()==hand.get(2).getValue() && hand.get(2).getValue()==hand.get(3).getValue() && hand.get(3).getValue()==hand.get(4).getValue()){
            return 1;
        }
        return -1;
    }

    public static int hasThreeKind(List<Card> hand){
        int count=0;
        for (int i=0; i<4; i++){
            count=0;
            for (int j=0; j<hand.size(); j++){
                if (hand.get(i).getValue()==hand.get(j).getValue()){
                    count++;
                }
                if (count==3){
                    return 1;
                }
            }
        }
        return -1;
    }
}
