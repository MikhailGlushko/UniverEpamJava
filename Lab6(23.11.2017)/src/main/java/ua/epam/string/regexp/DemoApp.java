package ua.epam.string.regexp;

import java.io.InputStream;
import java.util.Scanner;

public class DemoApp {

    public static void main(String[] args) {

        URLChecker urlChecker = new URLChecker();
        readDataFromStream(urlChecker,System.in);

    }

    public static void readDataFromStream(URLChecker urlChecker, InputStream stream) {
        Scanner scanner = new Scanner(stream);
        System.out.print("Введіть адрес для перевірки (q-Quit): ");
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.print(line);
            if(line.equals("q")) {
                break;
            }
            boolean b = urlChecker.isValidUrl(line);
            if(b) {
                System.out.println(" - Адреса корректна");
            }
            else {
                System.out.println(" - Адреса не корректна");
            }
            System.out.print("Введіть адрес для перевірки (q-Quit): ");
        }
        System.out.println("отримано команду завершення роботи");
    }
}
