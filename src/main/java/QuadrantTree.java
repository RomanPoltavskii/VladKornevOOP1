package main.java;

import java.util.ArrayList;
import java.util.List;

public class QuadrantTree<T>{
    private class QuadrantNode<T>{
        int level = -1;
        List<Point> points;
        QuadrantNode topLeft = null;
        QuadrantNode topRight = null;
        QuadrantNode bottomLeft = null;
        QuadrantNode bottomRight = null;
        Borders boundry;
        QuadrantNode(int level, Borders boundry) {
            this.level = level;
            points = new ArrayList<>();
            this.boundry = boundry;
        }

    }
    private QuadrantNode root = null;
    private int MAX_CAPACITY = 4;

    public QuadrantTree(Borders boundry) {
        this.root = new QuadrantNode(1, boundry);
    }
    public QuadrantTree(Borders boundry, int MAX_CAPACITY) {
        this.root = new QuadrantNode(1, boundry);
        this.MAX_CAPACITY = MAX_CAPACITY;
    }


    public void splitQuarters(QuadrantNode node) {
        int x = node.boundry.getxMin()
                + (node.boundry.getxMax() - node.boundry.getxMin()) / 2;
        int y = node.boundry.getyMin()
                + (node.boundry.getyMax() - node.boundry.getyMin()) / 2;

        node.topLeft = new QuadrantNode(node.level + 1, new Borders(
                node.boundry.getxMin(), node.boundry.getyMin(), x,
                y));
        node.topRight = new QuadrantNode(node.level + 1, new Borders(x,
                node.boundry.getyMin(), x, y));
        node.bottomLeft = new QuadrantNode(node.level + 1, new Borders(
                node.boundry.getxMin(), x, x,
                node.boundry.getyMax()));
        node.bottomRight = new QuadrantNode(node.level + 1, new Borders(x, y,
                node.boundry.getxMax(), node.boundry.getyMax()));
    }

    private void add(QuadrantNode n, int x, int y, T value) {
        if (!n.boundry.isInside(x, y)) {
            return;
        }

        Point point = new Point(x, y, value);
        if (n.points.size() < MAX_CAPACITY) {
            n.points.add(point);
            return;
        }
        if (n.topLeft == null) {
            splitQuarters(n);
        }

        if (n.topLeft.boundry.isInside(x, y))
            add(n.topLeft, x, y, value);
        else if (n.topRight.boundry.isInside(x, y))
            add(n.topRight, x, y, value);
        else if (n.bottomLeft.boundry.isInside(x, y))
            add(n.bottomLeft, x, y, value);
        else if (n.bottomRight.boundry.isInside(x, y))
            add(n.bottomRight, x, y, value);
        else
            System.out.printf("\nЧто-то пошло не так [X1=%d Y1=%d]",x,y);
    }

    public void add(int x, int y, T value) {
        add(root, x, y, value);
    }

    public void add(Point<T> p) {
        add(root, p.getX(), p.getY(), p.getValue());
    }
     public void print(QuadrantNode<T> node) {
        if (node == null)
            return;

        System.out.printf("\nLVL = %d BORDERS: [X1=%d Y1=%d] \t[X2=%d Y2=%d] ",
                node.level, node.boundry.getxMin(), node.boundry.getyMin(),
                node.boundry.getxMax(), node.boundry.getyMax());


         System.out.println("\nCONTAINS:");
        for (Point point : node.points) {
            System.out.printf("\n\t  x=%d y=%d", point.getX(), point.getY());
        }
        if (node.points.size() == 0) {
            System.out.printf(" \n\t  NOTHING.");
        }
        System.out.println();
        print(node.topLeft);
        print(node.topRight);
        print(node.bottomLeft);
        print(node.bottomRight);

    }

    public QuadrantNode getRoot() {
        return root;
    }
}
