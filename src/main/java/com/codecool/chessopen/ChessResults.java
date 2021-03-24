package com.codecool.chessopen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ChessResults {

    public static class Players {

        private String name;
        private int sum;

        public Players(String name, int sum) {
            this.name = name;
            this.sum = sum;
        }

        public String getName() {
            return name;
        }

        public int getSum() {
            return sum;
        }
    }
    public List<String> getCompetitorsNamesFromFile(String fileName){
        List<Players> playersList = new ArrayList<>();
        try {
            List<String> items = Files.readAllLines(Paths.get(fileName));

            for (int i = 0; i < items.size(); i++) {
                String[] playersData = items.get(i).split(",");
                playersList.add(new Players(playersData[0],

                        Integer.parseInt(playersData[1]) +
                                Integer.parseInt(playersData[2]) +
                                Integer.parseInt(playersData[3]) +
                                Integer.parseInt(playersData[4]) +
                                Integer.parseInt(playersData[5])));
                }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        return playersList.stream()
                .sorted((Comparator.comparingInt(Players::getSum).reversed()))
                .map(Players::getName)
                .collect(Collectors.toList());
    }
}
