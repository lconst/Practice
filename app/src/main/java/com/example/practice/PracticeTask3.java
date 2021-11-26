package com.example.practice;

import static com.example.practice.PracticeTask3.Directions.DOWN;
import static com.example.practice.PracticeTask3.Directions.LEFT;
import static com.example.practice.PracticeTask3.Directions.RIGHT;
import static com.example.practice.PracticeTask3.Directions.UP;

public class PracticeTask3 {


    public static void main(String[] args) {
        //Задание №2
        Closure myClosure = () -> System.out.println("I love Java");
        repeatTask(10, myClosure::print);

        //Задание №3
        makeManySteps();
    }

    public static void repeatTask(int times, Runnable task) {
        for (int i = 0; i < times; i++) {
            task.run();
        }
    }

    interface Closure {
        void print();

    }

    //Задание №3
    final static int STEP = 1;

    enum Directions {
        LEFT,
        RIGHT,
        UP,
        DOWN;
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point(" + x +
                    ", " + y +
                    ')';
        }
    }

    public static Point makeStep(Point point, Directions direction) {
        Point newPosition = new Point(point.x, point.y);
        switch (direction) {
            case LEFT:
                newPosition.x = -STEP;
                break;
            case RIGHT:
                newPosition.x += STEP;
                break;
            case UP:
                newPosition.y += STEP;
                break;
            case DOWN:
                newPosition.y -= STEP;
                break;
        }
        return newPosition;
    }

    public static void makeManySteps() {
        Point location = new Point(0,0);
        Directions [] moving = {UP, UP, LEFT, DOWN, LEFT, DOWN, DOWN, RIGHT, RIGHT, DOWN, RIGHT};
        for (int i = 0; i < moving.length; i++) {
            location = makeStep(location, moving[i]);
        }
        System.out.println(location);
    }

    //Задание №4
    interface Shape {
        double perimeter();
        double area();
    }

    class Rectangle implements Shape {

        private double length;
        private double width;

        @Override
        public double perimeter() {
            return (length + width) * 2;
        }

        @Override
        public double area() {
            return length * width;
        }
    }

    class Square implements Shape {

        private double length;

        @Override
        public double perimeter() {
            return length * 4;
        }

        @Override
        public double area() {
            return length * length;
        }
    }

    class Circle implements Shape {

        private double diameter;

        @Override
        public double perimeter() {
            return diameter * Math.PI;
        }

        @Override
        public double area() {
            return Math.PI * Math.pow(diameter / 2, 2);
        }
    }
}
