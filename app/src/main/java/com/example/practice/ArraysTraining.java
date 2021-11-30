package com.example.practice;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < valuesArray.length; i++) {
                if (valuesArray[i] < valuesArray[i - 1]) {
                    int temp = valuesArray[i];
                    valuesArray[i] = valuesArray[i - 1];
                    valuesArray[i - 1] = temp;
                    isSorted = false;
                }
            }
        }
        return valuesArray;
    }

    private void printArray(int[] valuesArray) {
        for (int value : valuesArray) {
            System.out.print(value + " ");
        }
        System.out.println();
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        if (values.length == 0) return 0;
        int max = values[0];
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int n = array.length;
        for (int i = 0; i < n / 2; i++) {
            int tmp = array[i];
            array[i] = array[n - i - 1];
            array[n - i - 1] = tmp;
        }
        return array;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        if (numbersCount < 1) return new int[]{};
        int[] fibonacci = new int[numbersCount];
        fibonacci[0] = 0;
        if (numbersCount > 1) {
            fibonacci[1] = 1;
        } else {
            return fibonacci;
        }
        for (int i = 2; i < numbersCount; i++) {
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }
        return fibonacci;
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        int max = 1;
        int count = 1;
        array = sort(array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[i - 1]) {
                count++;
            } else {
                count = 1;
            }
            if (count > max) max = count;
        }
        return max;
    }
}
