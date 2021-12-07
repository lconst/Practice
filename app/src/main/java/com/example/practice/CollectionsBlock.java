package com.example.practice;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        if (firstList == null || secondList == null) {
            throw new NullPointerException();
        }
        List<T> combine = new ArrayList<>();
        combine.addAll(firstList);
        combine.addAll(secondList);
        Collections.sort(combine, Collections.reverseOrder());
        return combine;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        if (inputList == null) {
            throw new NullPointerException();
        }
        if (inputList.isEmpty()) {
            return inputList;
        }
        List<T> result = new ArrayList<>();
        result.add(inputList.get(0));
        for (int i = 1; i < inputList.size(); i++) {
            result.add(inputList.get(i));
            result.addAll(inputList.subList(0, i));
        }
        return result;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        if (firstList == null || secondList == null) {
            throw new NullPointerException();
        }
        HashSet<T> firstSet = new HashSet<>(firstList);
        HashSet<T> secondSet = new HashSet<>(secondList);

        return !(firstSet.addAll(secondSet));
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        if (inputList == null) {
            throw new NullPointerException();
        }
        List<T> list = new ArrayList<>();
        int shift;
        if (Math.abs(n) > inputList.size()) {
            shift = Math.abs(n % inputList.size());
        } else {
            shift = Math.abs(n);
        }

        if (n < 0) {
            list.addAll(inputList.subList(shift, inputList.size()));
            list.addAll(inputList.subList(0, shift));
        } else {
            list.addAll(inputList.subList(0, inputList.size() - shift));
            list.addAll(0, inputList.subList(inputList.size() - shift, inputList.size()));
        }
        return list;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        if (inputList == null || a == null || b == null) {
            throw new NullPointerException();
        }
        Pattern pattern = Pattern.compile(a);
        for (int i = 0; i < inputList.size(); i++) {
            String elem = inputList.get(i);
            Matcher matcher = pattern.matcher(elem);
            inputList.set(i, matcher.replaceAll(b));
        }
        return inputList;
    }

    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке. Найдите средний балл каждой группы по каждому предмету.
      Определите самого старшего студента и самого младшего студентов.
      Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */

    private class Student {
        public static final int NUMBER_DISCIPLINE = 5;
        private String surname;
        private String name;
        private String middleName;
        private int year;
        private int course;
        private int group;
        private List<Integer> grade;

        public Student(String surname, String name, String middleName, int year, int course, int group, List<Integer> grade) {
            this.surname = surname;
            this.name = name;
            this.middleName = middleName;
            this.year = year;
            this.course = course;
            this.group = group;
            this.grade = grade;
        }

        public String getSurname() {
            return surname;
        }

        public String getName() {
            return name;
        }

        public String getMiddleName() {
            return middleName;
        }

        public int getYear() {
            return year;
        }

        public int getCourse() {
            return course;
        }

        public int getGroup() {
            return group;
        }

        public List getGrade() {
            return grade;
        }

        @Override
        public String toString() {
            return String.format(
                    Locale.getDefault(),
                    "%-20s %-20s %-20s возраст = %d курс = %d группа = %d оценки = %s", surname, name, middleName, year, course, group, grade.toString());
        }

        public double getAverageGrade() {
            double average = 0.0;
            for (int i = 0; i < grade.size(); i++) {
                average += grade.get(i);
            }
            return average / grade.size();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean collectionTask5() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Пушкин", "Александр", "Сергеевич", 20, 3, 1, Arrays.asList(1, 2, 4, 5, 5)));
        studentList.add(new Student("Толстой", "Лев", "Николаевич", 18, 1, 1, Arrays.asList(4, 5, 4, 5, 5)));
        studentList.add(new Student("Достоевский", "Федор", "Михайлович", 18, 2, 2, Arrays.asList(2, 3, 5, 5, 5)));
        studentList.add(new Student("Чехов", "Антон", "Павлович", 19, 1, 2, Arrays.asList(2, 3, 5, 5, 5)));
        studentList.add(new Student("Гоголь", "Николай", "Васильевич", 19, 2, 2, Arrays.asList(2, 3, 5, 5, 5)));
        studentList.add(new Student("Лермонтов", "Михаил", "Юрьевич", 21, 3, 2, Arrays.asList(5, 5, 5, 5, 5)));
        System.out.println("\n===Список студентов===");
        printStudents(studentList);

        //ordering students
        List<Student> sortedList = studentList.stream()
                .sorted(Comparator.comparing(Student::getCourse)
                        .thenComparing(Student::getSurname))
                .collect(Collectors.toList());
        System.out.println("\n===Студенты сортированные по курсу и фамилии===");
        printStudents(sortedList);

        //find max min age
        Optional<Student> maxAge = studentList.stream().max(Comparator.comparing(Student::getYear));
        Optional<Student> minAge = studentList.stream().min(Comparator.comparing(Student::getYear));
        System.out.println("\n===Самый старший студент===");
        System.out.println(maxAge.isPresent() ? maxAge.get() : "Не найден");
        System.out.println("\n===Самый младший студент===");
        System.out.println(minAge.isPresent() ? minAge.get() : "Не найден");

        //Best student of group
        Map<Integer, List<Student>> groups = studentList.stream()
                .collect(Collectors.groupingBy(Student::getGroup));
        System.out.println("\n===Случшие студенты в группах===");
        groups.forEach((k, v) -> {
            Optional<Student> student = v.stream().max(Comparator.comparing(Student::getAverageGrade));
            System.out.printf("Группа %d %s\n", k, student.isPresent() ? student.get() : "Не найден");
        });

        //Average grade by group by discipline
        Map<Integer, List<Double>> averageGradeByGroup = new HashMap<>();
        groups.forEach((numGroup, students) -> {
            List<Double> averageGrade = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0);
            students.forEach(student -> {
                for (int i = 0; i < Student.NUMBER_DISCIPLINE; i++) {
                    averageGrade.set(i, averageGrade.get(i) + student.grade.get(i));
                }
            });
            for (int i = 0; i < Student.NUMBER_DISCIPLINE; i++) {
                averageGrade.set(i, averageGrade.get(i) / students.size());
            }
            averageGradeByGroup.put(numGroup, averageGrade);
        });

        System.out.println("\n===Средний балл каждой группы по каждому предмету===");
        averageGradeByGroup.forEach((numGroup, grade) -> {
            System.out.printf("Группа %d : ", numGroup);
            System.out.println(grade.toString());
        });
        return true;
    }

    private void printStudents(List<Student> studentList) {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }
}
