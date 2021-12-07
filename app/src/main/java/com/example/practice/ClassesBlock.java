package com.example.practice;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Набор заданий по работе с классами в java.
 * <p>
 * Задания подразумевают создание класса(ов), выполняющих задачу.
 * <p>
 * Проверка осуществляется ментором.
 */
public interface ClassesBlock {

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию, которая находит наибольшее
      значение из этих двух переменных.
     */
    class TwoVariables {
        private int firstValue;
        private int secondValue;

        public TwoVariables(int firstValue, int secondValue) {
            this.firstValue = firstValue;
            this.secondValue = secondValue;
        }

        public void print() {
            System.out.printf("A = %d B = %d", firstValue, secondValue);
        }

        public void changeVariables(int a, int b) {
            firstValue = a;
            secondValue = b;
        }

        public int sum() {
            int sum = firstValue + secondValue;
            System.out.printf("A + B = %d", sum);
            return sum;
        }

        public int max() {
            return Math.max(firstValue, secondValue);
        }
    }

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */
    class DynamicArray {
        private List<Integer> array;
        private int size;

        public DynamicArray(int size) {
            this.size = size;
            array = new ArrayList<>(size);
        }

        public void fillArray() {
            for (int i = 0; i < size; i++) {
                array.add(i, new Random().nextInt(10 - 1) + 1);
            }
            print();
        }

        public void shuffle() {
            Collections.shuffle(array);
            print();
        }

        public int countDifferentElements() {
            Set<Integer> diff = new HashSet<>(array);
            return diff.size();
        }

        public void print() {
            System.out.println(array);
        }

        public static void main(String[] args) {
            DynamicArray array = new DynamicArray(10);
            array.fillArray();
            array.shuffle();
            System.out.println(array.countDifferentElements());
        }
    }

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */
    class Triangle {

        private Point topA;
        private Point topB;
        private Point topC;

        public Triangle(Point topA, Point topB, Point topC) {
            this.topA = topA;
            this.topB = topB;
            this.topC = topC;
        }

        public double square() {
            double semiPerimeter = perimeter() / 2;
            return Math.sqrt(semiPerimeter
                    * (semiPerimeter - topA.lengthTo(topB))
                    * (semiPerimeter - topB.lengthTo(topC))
                    * (semiPerimeter - topC.lengthTo(topA))
            );
        }

        public double perimeter() {
            return topA.lengthTo(topB) + topB.lengthTo(topC) + topC.lengthTo(topA);
        }

        public Point crossMedian() {
            double x = topA.x;
            double x1 = (topB.x + topC.x) / 2;
            double y1 = (topB.y + topC.y) / 2;
            double xm = (topA.x + 2 * x1) / 3;
            double xy = (topA.y + 2 * y1) / 3;
            return new Point(xm, xy);
        }

        static class Point {
            public double x;
            public double y;

            public Point(double x, double y) {
                this.x = x;
                this.y = y;
            }

            public double lengthTo(Point point) {
                return Math.sqrt(Math.pow(this.x - point.x, 2) + Math.pow(this.y - point.y, 2));
            }

            @Override
            public String toString() {
                return "Point{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }

        public static void main(String[] args) {
            Triangle triangle = new Triangle(
                    new Point(1.0, 1.0),
                    new Point(3.0, 5.0),
                    new Point(5.0, 2.0));
            System.out.println(triangle.perimeter());
            System.out.println(triangle.square());
            System.out.println(triangle.crossMedian());
        }
    }
    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

    class Time {
        private int hour;
        private int minute;
        private int seconds;

        public Time(int hour, int minute, int seconds) {
            this.hour = hour;
            this.minute = minute;
            this.seconds = seconds;
        }

        public void setHour(int hour) {
            if (hour < 0 || hour > 23) {
                throw new IllegalArgumentException();
            }
            this.hour = hour;
        }

        public void setMinute(int minute) {
            if (minute < 0 || minute > 59) {
                throw new IllegalArgumentException();
            }
            this.minute = minute;
        }

        public void setSeconds(int seconds) {
            if (seconds < 0 || seconds > 59) {
                throw new IllegalArgumentException();
            }
            this.seconds = seconds;
        }

        public void setTime(int hour, int minute, int seconds) {
            setHour(hour);
            setMinute(minute);
            setSeconds(seconds);
        }
    }

    /*
      V

      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор; Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */
    class Subscriber {
        private int id;
        private String surname;
        private String name;
        private String middleName;
        private String address;
        private long cardNumber;
        private int debit;
        private int credit;
        private long timeCrossCity;
        private long timeLocal;

        public Subscriber(
                int id,
                String surname,
                String name,
                String middleName,
                String address,
                long cardNumber,
                int debit,
                int credit,
                long timeCrossCity,
                long timeLocal
        ) {
            this.id = id;
            this.surname = surname;
            this.name = name;
            this.middleName = middleName;
            this.address = address;
            this.cardNumber = cardNumber;
            this.debit = debit;
            this.credit = credit;
            this.timeCrossCity = timeCrossCity;
            this.timeLocal = timeLocal;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(long cardNumber) {
            this.cardNumber = cardNumber;
        }

        public int getDebit() {
            return debit;
        }

        public void setDebit(int debit) {
            this.debit = debit;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public long getTimeCrossCity() {
            return timeCrossCity;
        }

        public void setTimeCrossCity(long timeCrossCity) {
            this.timeCrossCity = timeCrossCity;
        }

        public long getTimeLocal() {
            return timeLocal;
        }

        public void setTimeLocal(long timeLocal) {
            this.timeLocal = timeLocal;
        }

        @Override
        public String toString() {
            return "Subscriber{" +
                    "id=" + id +
                    ", surname='" + surname + '\'' +
                    ", name='" + name + '\'' +
                    ", middleName='" + middleName + '\'' +
                    ", address='" + address + '\'' +
                    ", cardNumber=" + cardNumber +
                    ", debit=" + debit +
                    ", credit=" + credit +
                    ", time=" + timeCrossCity +
                    '}';
        }

        public static void main(String[] args) {
            List<Subscriber> subscribers = new ArrayList<>();
            subscribers.add(new Subscriber(0, "Пушкин", "Александр", "Сергеевич", "Москва", 12345, 100, 10, 0, 0));
            subscribers.add(new Subscriber(1, "Лермонтов", "Михаил", "Юрьевич", "Пятигорск", 11111, 500, 100, 5, 10));
            subscribers.add(new Subscriber(2, "Достоевский", "Федор", "Михайлович", "Санкт-Петербург", 54322, 1000, 1300, 0, 20));
            subscribers.add(new Subscriber(3, "Чехов", "Антон", "Павлович", "Таганрог", 98989, 2300, 203, 52, 0));
            subscribers.add(new Subscriber(4, "Буглаков", "Михаил", "Афанасьевич", "Киев", 22222, 400, 123, 10, 5));

            int timeLocalLimit = 10;
            System.out.println("Абоненты превысившие лимит " + timeLocalLimit);
            subscribers.stream()
                    .filter(subscriber -> subscriber.timeLocal > timeLocalLimit)
                    .sorted(Comparator.comparing(Subscriber::getSurname))
                    .forEach(System.out::println);

            System.out.println("Абоненты с междугородними звонками");
            subscribers.stream()
                    .filter(subscriber -> subscriber.timeCrossCity > 0)
                    .sorted(Comparator.comparing(Subscriber::getSurname))
                    .forEach(System.out::println);
        }
    }

    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */
    class Enrollee {
        private int id;
        private String surname;
        private String name;
        private Map<Exam, Integer> resultExams;

        public Enrollee(int id, String surname, String name) {
            this.id = id;
            this.surname = surname;
            this.name = name;
            resultExams = new HashMap<>();
        }

        public double getAvgScore() {
            List<Integer> scores = new ArrayList<>();
            resultExams.forEach(((exam, score) -> {
                scores.add(score);
            }));
            OptionalDouble average = scores.stream().mapToDouble(a -> a).average();
            return average.isPresent() ? average.getAsDouble() : 0.0;
        }
    }

    class Faculty {
        private int id;
        private String name;
        private List<Exam> exams;
        private List<Enrollee> enrollees;

        public Faculty(int id, String name, List<Exam> exams) {
            this.id = id;
            this.name = name;
            this.exams = exams;
            enrollees = new ArrayList<>();
        }

        public void registryEnrollee(Enrollee enrollee) {
            enrollees.add(enrollee);
        }
    }

    class Exam {
        private int id;
        private String name;
        private int minScore;

        public Exam(int id, String name, int minScore) {
            this.id = id;
            this.name = name;
            this.minScore = minScore;
        }
    }

    class VerificationSystem {
        private final double MIN_SCORE = 80.0;
        private List<Faculty> faculties;

        public VerificationSystem(List<Faculty> faculties) {
            this.faculties = faculties;
        }

        public List<Enrollee> getEnrolliesPassedExam() {
            List<Enrollee> enrollies = new ArrayList<>();
            faculties.forEach((faculty -> {
                enrollies.addAll(faculty.enrollees.stream()
                                .filter(enrollee -> enrollee.getAvgScore() >= MIN_SCORE)
                                .collect(Collectors.toList()));
            }));
            return enrollies;
        }
    }

    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */
    class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    class Client {
        private int id;
        private String name;
        private List<Order> orders;

        public Client(int id, String name) {
            this.id = id;
            this.name = name;
            orders = new ArrayList<>();
        }

        public void makeOrder(List<Product> products, boolean isPaid) {
            orders.add(new Order(products, isPaid));
        }

        public void payOrder(@NonNull Order order) {
            order.isPaid = true;
        }
    }

    class Customer {
        private List<Order> registredOrders = new ArrayList<>();
        private List<Client> blackList = new ArrayList<>();

        private int id;
        private String name;

        public Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public void registryOrder(@NonNull Order order) {
            if (order.isPaid()) {
                registredOrders.add(order);
            }
        }

        public void addToBlack(Client client) {
            blackList.add(client);
        }
    }

    class Order {
        private List<Product> products;
        private boolean isPaid;

        public Order(List<Product> products, boolean isPaid) {
            this.products = products;
            this.isPaid = isPaid;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public boolean isPaid() {
            return isPaid;
        }

        public void setPaid(boolean paid) {
            isPaid = paid;
        }

        public void addProducts(Product product) {
            products.add(product);
        }
    }
}
