import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Переменные для хранения текущей суммы и минимальной разницы
        int totalScore = 0;
        int smallestGap = Integer.MAX_VALUE;

        try (Scanner fileReader = new Scanner(new File("src\\input.txt"))) {
            while (fileReader.hasNextLine()) {
                // Читаем строку и разделяем на числа
                String currentLine = fileReader.nextLine();
                String[] values = currentLine.split(" ");
                int num1 = Integer.parseInt(values[0]);
                int num2 = Integer.parseInt(values[1]);

                // Проверяем условия кратности чисел на 3
                if (num1 % 3 == num2 % 3 || (num1 % 3 != 0 && num2 % 3 != 0)) {
                    totalScore += Math.max(num1, num2);
                } else {
                    // Если одно из чисел кратно 3, а другое нет
                    if (num1 % 3 == 0 && num1 > num2) {
                        int currentGap = num1 - num2;
                        if (currentGap < smallestGap) {
                            smallestGap = currentGap;
                        }
                        totalScore += num1;
                    } else if (num2 % 3 == 0 && num2 > num1) {
                        int currentGap = num2 - num1;
                        if (currentGap < smallestGap) {
                            smallestGap = currentGap;
                        }
                        totalScore += num2;
                    } else {
                        totalScore += Math.max(num1, num2);
                    }
                }
            }

            // Корректируем итоговую сумму, если она делится на 3
            if (totalScore % 3 == 0) {
                totalScore -= smallestGap;
            }

            // Вывод итогового результата
            System.out.println(totalScore);

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
            e.printStackTrace();
        }
    }
}
