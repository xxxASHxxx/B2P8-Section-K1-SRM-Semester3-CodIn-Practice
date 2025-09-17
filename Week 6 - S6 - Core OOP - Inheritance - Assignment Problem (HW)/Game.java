import java.util.Objects;

class Game {
    protected String title;
    protected int players;
    
    public Game(String title, int players) {
        this.title = title;
        this.players = players;
    }
    
    @Override
    public String toString() {
        return "Game{title='" + title + "', players=" + players + "}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Game game = (Game) obj;
        return players == game.players && Objects.equals(title, game.title);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, players);
    }
}

class CardGame extends Game {
    private String cardType;
    
    public CardGame(String title, int players, String cardType) {
        super(title, players);
        this.cardType = cardType;
    }
    
    @Override
    public String toString() {
        return super.toString().replace("Game{", "CardGame{") + 
               " + cardType='" + cardType + "'}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        CardGame cardGame = (CardGame) obj;
        return Objects.equals(cardType, cardGame.cardType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cardType);
    }
}

public class ObjectMethodsDemo {
    public static void main(String[] args) {
        Game game1 = new Game("Chess", 2);
        Game game2 = new Game("Chess", 2);
        Game game3 = new Game("Checkers", 2);
        
        System.out.println("toString: " + game1);
        System.out.println("equals same: " + game1.equals(game2)); // true
        System.out.println("equals different: " + game1.equals(game3)); // false
        
        CardGame card1 = new CardGame("Poker", 4, "Standard");
        CardGame card2 = new CardGame("Poker", 4, "Standard");
        
        System.out.println("CardGame toString: " + card1);
        System.out.println("CardGame equals: " + card1.equals(card2)); // true
        System.out.println("Cross-class equals: " + card1.equals(game1)); // false
    }
}
