package main;


import java.util.Arrays;

public class Tile {
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


    public static class Bag {
        int[] quantity;
        Tile[] tiles;

        private static Bag b;

        private Bag() {

            // Quantity Initializing
            initQuan();

            // Tiles Initializing
            initTiles();
        }

        private void initQuan() {
            this.quantity = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4,
                    2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
        }

        private void initTiles() {
            this.tiles = new Tile[]{new Tile('A', 1),
                    new Tile('B', 3), new Tile('C', 3),
                    new Tile('D', 2), new Tile('E', 1),
                    new Tile('F', 4), new Tile('G', 2),
                    new Tile('H', 4), new Tile('I', 1),
                    new Tile('J', 8), new Tile('K', 5),
                    new Tile('L', 1), new Tile('M', 3),
                    new Tile('N', 1), new Tile('O', 1),
                    new Tile('P', 3), new Tile('Q', 10),
                    new Tile('R', 1), new Tile('S', 1),
                    new Tile('T', 1), new Tile('U', 1),
                    new Tile('V', 4), new Tile('W', 4),
                    new Tile('X', 8), new Tile('Y', 4),
                    new Tile('Z', 10)
            };
        }

        public Tile getRand() {
            // 1) Check if the bag isn't empty
            if (size() == 0)
                return null;

            // 2) Generate a random tile (number: 0-25)
            int rand = (int) (Math.random() * 26 + 1) - 1;

            // 3) Check if the generated tile exist.
            while (this.quantity[rand] == 0)
                rand = (int) (Math.random() * 26 + 1) - 1;

            // 4) Decrease the tile quantity
            this.quantity[rand]--;

            return this.tiles[rand];
        }

        public Tile getTile(char l) {
            // 1) Check if the bag isn't empty
            if (size() == 0)
                return null;

            // 2) Check if the tile exist
            int index;
            for (Tile t : this.tiles) {
                index = t.letter-'A';
                if (l == t.letter && this.quantity[index] > 0) {
                    this.quantity[index]--;
                    return this.tiles[index];
                }
            }
            return null;
        }

        public void put(Tile t) {
            // 1) Check if the Bag is full
            if (size() == 98)
                return;

            // 2) Increase the tile quantity
            int index = t.letter - 'A';
            this.quantity[index]++;
        }

        public int size() {
            return Arrays.stream(this.quantity).sum();
        }

        public int[] getQuantities() {
            return this.quantity.clone();
        }

        // SingleTone Pattern
        public static Bag getBag() {
            if (b == null)
                b = new Bag();
            return b;
        }
    }
}

