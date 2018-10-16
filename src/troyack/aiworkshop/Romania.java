/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package troyack.aiworkshop;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author troyack
 */
public class Romania extends AIWorkshopPanel {

    private static final City[] CITIES;
    private static int citySize;
    private static final int ARAD = 0;
    private static final int BUCHAREST = 1;
    private static final int CRAIOVA = 2;
    private static final int DROBETA = 3;
    private static final int EFORIE = 4;
    private static final int FAGARAS = 5;
    private static final int GIURGIU = 6;
    private static final int HIRSOVA = 7;
    private static final int IASI = 8;
    private static final int LUGOJ = 9;
    private static final int MEHADIA = 10;
    private static final int NEAMT = 11;
    private static final int ORADEA = 12;
    private static final int PITESTI = 13;
    private static final int RIMNICU = 14;
    private static final int SIBIU = 15;
    private static final int TIMISOARA = 16;
    private static final int URZICENI = 17;
    private static final int VASLUI = 18;
    private static final int ZERIND = 19;

    static {
        CITIES = new City[]{new City("Arad", 55, 165, 366),
            new City("Bucharest", 569, 436, 0),
            new City("Craiova", 329, 501, 160),
            new City("Dobreta", 186, 482, 242),
            new City("Eforie", 833, 492, 161),
            new City("Fagaras", 417, 238, 176),
            new City("Giurgiu", 527, 529, 77),
            new City("Hirsova", 786, 399, 151),
            new City("Iasi", 689, 145, 226),
            new City("Lugoj", 186, 353, 244),
            new City("Mehadia", 191, 417, 241),
            new City("Neamt", 578, 95, 234),
            new City("Oradea", 130, 39, 380),
            new City("Pitesti", 440, 372, 100),
            new City("Rimnico Vilcea", 297, 302, 193),
            new City("Sibiu", 255, 224, 253),
            new City("Timisoara", 70, 302, 329),
            new City("Urziceni", 662, 399, 80),
            new City("Vaslui", 747, 245, 199),
            new City("Zerind", 94, 104, 374)
        };

        CITIES[ARAD].addVertice(CITIES[SIBIU], 140);
        CITIES[ARAD].addVertice(CITIES[TIMISOARA], 118);
        CITIES[ARAD].addVertice(CITIES[ZERIND], 75);
        CITIES[BUCHAREST].addVertice(CITIES[FAGARAS], 211);
        CITIES[BUCHAREST].addVertice(CITIES[GIURGIU], 90);
        CITIES[BUCHAREST].addVertice(CITIES[PITESTI], 101);
        CITIES[BUCHAREST].addVertice(CITIES[URZICENI], 85);
        CITIES[CRAIOVA].addVertice(CITIES[DROBETA], 120);
        CITIES[CRAIOVA].addVertice(CITIES[PITESTI], 138);
        CITIES[CRAIOVA].addVertice(CITIES[RIMNICU], 146);
        CITIES[DROBETA].addVertice(CITIES[MEHADIA], 75);
        CITIES[EFORIE].addVertice(CITIES[HIRSOVA], 86);
        CITIES[FAGARAS].addVertice(CITIES[SIBIU], 99);
        CITIES[HIRSOVA].addVertice(CITIES[URZICENI], 98);
        CITIES[IASI].addVertice(CITIES[NEAMT], 87);
        CITIES[IASI].addVertice(CITIES[VASLUI], 92);
        CITIES[LUGOJ].addVertice(CITIES[MEHADIA], 70);
        CITIES[LUGOJ].addVertice(CITIES[TIMISOARA], 111);
        CITIES[ORADEA].addVertice(CITIES[SIBIU], 151);
        CITIES[ORADEA].addVertice(CITIES[ZERIND], 71);
        CITIES[PITESTI].addVertice(CITIES[RIMNICU], 97);
        CITIES[RIMNICU].addVertice(CITIES[SIBIU], 80);
        CITIES[URZICENI].addVertice(CITIES[VASLUI], 142);
    }

    public Romania() {
        citySize = 10;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (City city : CITIES) {
            city.getVertices().forEach((vertice) -> {
                g.drawLine(city.getX() + (citySize / 2),
                        city.getY() + (citySize / 2),
                        vertice.getKey().getX() + (citySize / 2),
                        vertice.getKey().getY() + (citySize / 2));
                drawString(g, vertice.getValue().toString(),
                        (city.getX() + vertice.getKey().getX()) / 2,
                        (city.getY() + vertice.getKey().getY()) / 2);

            });
            g.fillRect(city.getX(), city.getY(), citySize, citySize);
            drawString(g, city.getName() + " (" + city.getHeuristics() + ")",
                    city.getX() + citySize * 2, city.getY() + citySize * 2);
        }
    }

    private void drawString(Graphics g, String text, int x, int y) {
        Color prev = g.getColor();
        g.setColor(Color.blue);
        g.drawString(text, x, y);
        g.setColor(prev);
    }

    @Override
    public String getTitle() {
        return "Romania";
    }

    @Override
    public String getDescription() {
        return "The Romanian map problem taken from the classic artificial intelligence textbook: \"Artificial Intellingence: A Modern Approach\" by Stuart Russell and Peter Norvig";
    }

    @Override
    public String getIconName() {
        return "glider.gif";
    }

    private static class City {

        private final int xPos;
        private final int yPos;
        private final String name;
        private final ArrayList<Pair<City, Integer>> vertices;
        private final int heuristics;

        public City(String cityName, int x, int y, int heuristics) {
            vertices = new ArrayList<>();
            xPos = x;
            yPos = y;
            name = cityName;
            this.heuristics = heuristics;
        }

        public void addVertice(City city, int distance) {
            vertices.add(new Pair<>(city, distance));
        }

        public ArrayList<Pair<City, Integer>> getVertices() {
            return vertices;
        }

        public String getName() {
            return name;
        }

        public int getHeuristics() {
            return heuristics;
        }

        public int getX() {
            return xPos;
        }

        public int getY() {
            return yPos;
        }
    }
}
