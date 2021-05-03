package me.Splerix.GemFinder;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;

public class Player implements GemFinderVariables{
    private boolean showStats = false;
    private Message message;
    private User user;
    private String[][] map = new String[HEIGHT][WIDTH];
    private int x = 0;
    private int y = 0;
    private int skips = 0;
    private int wins = 0;

    Player(Message m, User u) {
        message = m;
        user = u;
    }
    void endGame() {
        message.editMessage("**This game has expired or been closed**").queue();
        GameManager.userPlayer.remove(user.getId());
    }
    int getWins() {return wins;}
    int getSkips() {return skips;}
    void addReactions() {
        message.addReaction(left).queue();
        message.addReaction(up).queue();
        message.addReaction(right).queue();
        message.addReaction(down).queue();
        message.addReaction(skip).queue();
        message.addReaction(stop).queue();
        message.addReaction(statistics).queue();
    }
    boolean getShowStats() { return showStats; }
    Message getMessage() { return message; }
    User getUser() { return user; }
    void skipMap() {
        generateMap();
        updateMap();
    }
    void setSkips(int s) {skips = s;}
    void setWins(int w) {wins = w;}
    void addWins() {wins++;}
    void addSkips() {skips++;}
    void setUser(User u) { user = u; }
    void setMessage(Message m) { message = m; }
    void setShowStats(boolean stats) {showStats = stats;}
    void toggleShowStats() {showStats = (!(showStats));}
    void generateMap() {
        //Resets Map
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                map[i][j] = background;
            }
        }
        //Generates Randomized Walls
            for (int i = 0; i<WALLS;) {
                int x1 = random.nextInt(WIDTH);
                int y1 = random.nextInt(HEIGHT);
                if (!(map[y1][x1].equals(wall))) {
                    map[y1][x1] = wall;
                    i++;
                }
        }
        //Player
        for (int i = 0; i<1;) {
                int x1 = random.nextInt(WIDTH);
                int y1 = random.nextInt(HEIGHT);
                boolean canPass = true;
                boolean fullCheck = true;
                switch (testNumber(x1, y1)) {
                    case "-":
                        break;
                    case "r":
                        fullCheck = false;
                        canPass = !rightCheck(x1, y1);
                        break;
                    case "l":
                        fullCheck = false;
                        canPass = !leftCheck(x1, y1);
                        break;
                    case "u":
                        fullCheck = false;
                        canPass = !upCheck(x1, y1);
                        break;
                    case "d":
                        fullCheck = false;
                        canPass = !downCheck(x1, y1);
                        break;
                    case "ur":
                        fullCheck = false;
                        canPass = !upRightCheck(x1, y1);
                        break;
                    case "ul":
                        fullCheck = false;
                        canPass = !upLeftCheck(x1, y1);
                        break;
                    case "dr":
                        fullCheck = false;
                        canPass = !downRightCheck(x1, y1);
                        break;
                    case "dl":
                        fullCheck = false;
                        canPass = !downLeftCheck(x1, y1);
                        break;
                }
                if (fullCheck)
                    if (map[y1 + 1][x1].equals(wall) && map[y1 - 1][x1].equals(wall) && map[y1][x1 + 1].equals(wall) && map[y1][x1 - 1].equals(wall))
                        canPass = false;
                    if (canPass)
                    if (!(map[y1][x1].equals(wall) || map[y1][x1].equals(gem) || map[y1][x1].equals(player))) {
                        x = x1;
                        y = y1;
                        map[y1][x1] = player;
                        i++;
                    }
                }
        //Gem
        for (int i = 0; i<1;) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            boolean canPass = true;
            boolean fullCheck = true;
            switch (testNumber(x1, y1)) {
                case "-":
                    break;
                case "r":
                    fullCheck = false;
                    canPass = !rightCheck(x1, y1);
                    break;
                case "l":
                    fullCheck = false;
                    canPass = !leftCheck(x1, y1);
                    break;
                case "u":
                    fullCheck = false;
                    canPass = !upCheck(x1, y1);
                    break;
                case "d":
                    fullCheck = false;
                    canPass = !downCheck(x1, y1);
                    break;
                case "ur":
                    fullCheck = false;
                    canPass = !upRightCheck(x1, y1);
                    break;
                case "ul":
                    fullCheck = false;
                    canPass = !upLeftCheck(x1, y1);
                    break;
                case "dr":
                    fullCheck = false;
                    canPass = !downRightCheck(x1, y1);
                    break;
                case "dl":
                    fullCheck = false;
                    canPass = !downLeftCheck(x1, y1);
                    break;
            }
            if (fullCheck) {
                if (map[y1 + 1][x1].equals(wall) && map[y1 - 1][x1].equals(wall) && map[y1][x1 + 1].equals(wall) && map[y1][x1 - 1].equals(wall))
                    canPass = false;
            }
            if (canPass)
                if (!(map[y1][x1].equals(wall) || map[y1][x1].equals(gem) || map[y1][x1].equals(player))) {
                    map[y1][x1] = gem;
                    i++;
                }
         }
    }
    void updateMap() {
        message.editMessage(makeStringMap()).queue();
    }
    String makeStringMap() {
        String makeMap = "";
        if (showStats) {
            makeMap = "Skips: **" + skips + "**\n"
                    + "Wins: **" + wins + "**\n"
                    + "Difference: **" + (wins - skips) + "**\n";
        }

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                makeMap = makeMap + map[i][j];
            }
            makeMap = makeMap + "\n";
        }
        return makeMap;
    }
    void move(char direction) {
            switch (direction) {
                case 'u':
                    int changeU = 0;
                    for (int i = 0; i<1;) {
                        if (y - changeU  - 1 < 0) i++;
                        else if (map[y - changeU - 1][x] == wall) i++;
                        else if (map[y - changeU - 1][x] == gem) { win();return; }
                        else changeU++;
                    }
                    for (int j = changeU; j != 0; j--) {
                        map[y][x] = background;
                        y--;
                        map[y][x] = player;
                    }
                    updateMap();
                    break;
                case 'l':
                    int changeL = 0;
                    for (int i = 0; i<1;) {
                        if (x - changeL - 1 < 0) i++;
                        else if (map[y][x - changeL - 1] == wall) i++;
                        else if (map[y][x - changeL - 1] == gem) { win();return; }
                        else changeL++;
                    }
                    for (int j = changeL; j != 0; j--) {
                        map[y][x] = background;
                        x--;
                        map[y][x] = player;
                    }
                    updateMap();
                    break;
                case 'd':
                    int changeD = 0;
                    for (int i = 0; i<1;) {
                        if (y + changeD  + 1> HEIGHT-1) i++;
                        else if (map[y + changeD + 1][x] == wall) i++;
                        else if (map[y + changeD + 1][x] == gem) { win(); return; }
                        else changeD++;
                    }
                    for (int j = changeD; j != 0; j--) {
                        map[y][x] = background;
                        y++;
                        map[y][x] = player;
                    }
                    updateMap();
                    break;
                case 'r':
                    int changeR = 0;
                    for (int i = 0; i<1;) {
                        if (x + changeR  + 1> WIDTH-1) i++;
                        else if (map[y][x + changeR + 1] == wall) i++;
                        else if (map[y][x + changeR + 1] == gem) { win(); return; }
                        else changeR++;
                    }
                    for (int j = changeR; j != 0; j--) {
                        map[y][x] = background;
                        x++;
                        map[y][x] = player;
                    }
                    updateMap();
                    break;
            }
    }
    void win() {
        addWins();
        skipMap();
    }
//Checks
    private String testNumber(int x1, int y1) {
        boolean right = false;
        boolean up = false;
        boolean down = false;
        boolean left = false;

        if (x1+1 > 4) right = true;
        if (x1-1 < 0) left = true;
        if (y1+1 > 4) down = true;
        if (y1-1 < 0) up = true;

        if (right && up) return "ur";
        if (right && down) return "dr";
        if (left && up) return "ul";
        if (left && down) return "dl";

        if (right) return "r";
        if (left) return "l";
        if (up) return "u";
        if (down) return "d";
        return "-";
    }
    private boolean rightCheck(int x1, int y1) {return map[y1+1][x1].equals(wall) && map[y1-1][x1].equals(wall) && map[y1][x1-1].equals(wall);}
    private boolean leftCheck(int x1, int y1) {return map[y1+1][x1].equals(wall) && map[y1-1][x1].equals(wall) && map[y1][x1+1].equals(wall);}
    private boolean upCheck(int x1, int y1) {return map[y1+1][x1].equals(wall) && map[y1][x1+1].equals(wall) && map[y1][x1-1].equals(wall);}
    private boolean downCheck(int x1, int y1) {return map[y1-1][x1].equals(wall) && map[y1][x1+1].equals(wall) && map[y1][x1-1].equals(wall);}
    private boolean upRightCheck(int x1, int y1) {return map[y1+1][x1].equals(wall) && map[y1][x1-1].equals(wall);}
    private boolean downRightCheck(int x1, int y1) {return map[y1-1][x1].equals(wall) && map[y1][x1-1].equals(wall);}
    private boolean upLeftCheck(int x1, int y1) {return map[y1+1][x1].equals(wall) && map[y1][x1+1].equals(wall);}
    private boolean downLeftCheck(int x1, int y1) {return map[y1-1][x1].equals(wall) && map[y1][x1+1].equals(wall);}
}