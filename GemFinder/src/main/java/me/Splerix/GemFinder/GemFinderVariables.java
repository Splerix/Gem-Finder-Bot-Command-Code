package me.Splerix.GemFinder;

import java.util.Random;

public interface GemFinderVariables {
    int WALLS = 10;
    int WIDTH = 5;
    int HEIGHT = 5;

     Random random = new Random();

     String wall = "🟫";;
     String background = "🟧";
     String player = "🤠";
     String gem = "💎";
     String left = "⬅";
     String up = "⬆";
     String right = "➡";
     String down = "⬇";
     String skip = "⏩";
     String stop = "🛑";
     String statistics = "📊";
}
