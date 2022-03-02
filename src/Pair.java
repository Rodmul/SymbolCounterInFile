public class Pair {
    private char symbol;
    private int count;

    public Pair(char symbol, int count){
        this.symbol = symbol;
        this.count = count;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
