import java.util.Objects;

class Card {
    private String rank;
    private String suit;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Card)) {
            return false;
        }

        Card other = (Card) obj;
        return rank.equals(other.rank) && suit.equals(other.suit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rank, suit);
    }
}

public class drive{
    public static void main(String[] args) {

        Card[] cards = new Card[10];

        Card[] newCards = {
            new Card("Ace", "Spades"),
            new Card("King", "Hearts"),
            new Card("Queen", "Diamonds"),
            new Card("Ace", "Spades"),
            new Card("Jack", "Clubs")
        };

        int count = 0;

        for (Card card : newCards) {

            boolean duplicate = false;

            // Check against earlier cards
            for (int i = 0; i < count; i++) {
                if (card.equals(cards[i])) {
                    duplicate = true;
                    break;
                }
            }

            if (duplicate) {
                System.out.println("Duplicate found: " + card);
                break;
            }

            // Add card if it is not a duplicate
            cards[count] = card;
            count++;
        }
    }
}
