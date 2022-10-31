package main.java;


public class Main {
    public static void main(String args[]) {
        QuadrantTree anySpace1 = new QuadrantTree(new Borders(0, 0, 1000, 1000));

        for (int i = 0; i < 9; i++) {
            anySpace1.add(i*100, i*100, 1);
        }

        anySpace1.add(510, 610, 1);
        anySpace1.add(520, 620, 1);
        anySpace1.add(530, 630, 1);
        anySpace1.add(540, 640, 1);
        anySpace1.add(550, 650, 1);
        anySpace1.add(555, 655, 1);
        anySpace1.add(560, 660, 1);
        anySpace1.add(50, 60, 3);


        anySpace1.print(anySpace1.getRoot());
    }
}