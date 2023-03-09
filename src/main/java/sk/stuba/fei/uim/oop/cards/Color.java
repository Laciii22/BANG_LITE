package sk.stuba.fei.uim.oop.cards;

public enum Color {
    BROWN(0), BLUE(1);
    private int value;
    Color(int value){
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    public Color getColorFromValue(int value){
        return Color.values()[value];
    }
}
