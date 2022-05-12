
public class Map {
    private String[] initialMap;
    private String[] clearMap;

    private int playerX, playerY;
    private int targetX, targetY;

    public Map(String layout[]) {
        initialize(layout);
    }

    private void initialize(String[] layout) {
        initialMap = layout;
        clearMap = new String[layout.length];

        boolean foundPlayer = false;
        boolean foundTarget = false;
        for (int y = 0; y < layout.length; y++) {
            String line = layout[y];

            for (int x = 0; x < line.length(); x++) {
                char currentChar = line.charAt(x);

                // Posição inicial do player
                if (currentChar == 'S') {
                    playerX = x;
                    playerY = y;
                    foundPlayer = true;
                }

                // Posição final do player
                if (currentChar == 'E') {
                    targetX = x;
                    targetY = y;
                    foundTarget = true;
                }
            }

            // Remove as marcações
            clearMap[y] = layout[y].replace("S", " ").replace("S", " ");
        }

        if (!foundPlayer) {
            throw new Error("Nenhum jogador encontrado no mapa");
        }
        if (!foundTarget) {
            throw new Error("Nenhum objetivo encontrado no mapa");
        }
    }

    public boolean tryMoveLeft() {
        if (getCharAt(playerX - 1, playerY) == '#') return false;
        playerX--;
        return true;
    }

    public boolean tryMoveRight() {
        if (getCharAt(playerX + 1, playerY) == '#') return false;
        playerX++;
        return true;
    }

    public boolean tryMoveUp() {
        if (getCharAt(playerX, playerY - 1) == '#') return false;
        playerY--;
        return true;
    }

    public boolean tryMoveDown() {
        if (getCharAt(playerX, playerY + 1) == '#') return false;
        playerY++;
        return true;
    }

    private char getCharAt(int x, int y) {
        if (y < 0 || y > initialMap.length - 1) return '#';
        String line = initialMap[y];
        if (x < 0 || x > line.length() - 1) return '#';
        return line.charAt(x);
    }

    public boolean isPlayerOnTarget() {
        return playerX == targetX && playerY == targetY;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int y = 0; y < clearMap.length; y++) {
            String line = clearMap[y];
            for (int x = 0; x < line.length(); x++) {
                if (x == playerX && y == playerY)
                    builder.append("P");
                else
                    builder.append(line.charAt(x));
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
