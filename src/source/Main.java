package source;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("-d")) { // видалення файла по id. треба запустити програму з параметром -d (delete)
            String fileName = "";
            String deletedLine = "";
            System.out.println("Enter full file path of file where rooms are stored: ");
            try {
                fileName = reader.readLine(); // введення шляху до файлу в якому зберігаються розміри кімнат
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Enter id of room to be deleted: ");
            int id = -7;
            try {
                id = Integer.parseInt(reader.readLine()); // ввід айді для подальшого видалення елементу з таким айді
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader inStream = new BufferedReader(new FileReader(fileName));
                ArrayList<String> list = new ArrayList<>();
                int arrayNumber = 0;

                while (inStream.ready()) {   // переписуємо у колекцію усі строки крім видаленої
                    String line = inStream.readLine();
                    list.add(arrayNumber, line);
                    String number = line.substring(0, 8).trim();
                    int checked = Integer.parseInt(number);
                    if (id == checked) {
                        deletedLine = line;
                        list.remove(arrayNumber--);
                    }
                    arrayNumber++;
                }
                inStream.close();

                System.out.println("The line " + "\'" + deletedLine + "\'" + " was deleted. DONE.");

                BufferedWriter outStream = new BufferedWriter(new FileWriter(fileName));
                for (String line : list) {  // переписуємо усі строки колекції у файл
                    outStream.write(line);
                    outStream.flush();
                    outStream.newLine();
                }
                outStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            List<Pair> pairList = new ArrayList<>();

            System.out.println("1. Enter amount of dots: (must be pair and positive value)");
            int dotsAmount = 0;
            try {
                dotsAmount = Integer.parseInt(reader.readLine());
                if (dotsAmount < 0 || dotsAmount % 2 != 0)
                    throw new NumberFormatException();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("\n2. Enter dots values: (must be positive)");
            for (int i = 0; i < dotsAmount; i++) {
                System.out.format("Enter x and then y values for dot №%d: \n", i + 1);
                pairList.add(new Pair(enteringDotValue(), enteringDotValue()));
            }

            System.out.println();

            CartesianGrid cartesianGrid = new CartesianGrid(Pair.getMaxValue(), pairList);
            DotsValidator dotsValidator = new DotsValidator(pairList);


            if (dotsValidator.isGood()) {
                System.out.println("!!!This rectangle matches requirements!!!");
                System.out.println();
                System.out.println("Here's your dots on graph: (graphs pointers could be replaced with dots)");
                cartesianGrid.cartesianGridPrint();
                System.out.println();
                cartesianGrid.printValues();
                if (args.length > 0 && args[0].equals("-s")) { // щоб зберегти введену кімнату треба запустити програму з параметром -s (save)
                    String fileName = "";
                    try {
                        System.out.println();
                        System.out.println("Enter full file path to save room coordinates: "); // вводимо шлях до файлу у який хочемо хаписати наші дані
                        fileName = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        BufferedReader inStream = new BufferedReader(new FileReader(fileName));
                        int check;
                        int max = 0;
                        while (inStream.ready()) {   // автоматично визначаємо яким буде наступне айді
                            String number = inStream.readLine().substring(0, 8).trim();
                            check = Integer.parseInt(number);
                            max = Math.max(max, check);
                        }
                        inStream.close();
                        BufferedWriter outStream = new BufferedWriter(new FileWriter(fileName, true));
                        String id = String.valueOf(max + 1);

                        while (id.length() < 8) {
                            id = id + " ";
                        }
                        outStream.newLine();
                        outStream.write(id + cartesianGrid.valuesToSave());
                        outStream.close();
                        System.out.println("DONE.");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    public static int enteringDotValue() {
        int xory = 0;
        try {
            xory = Integer.parseInt(reader.readLine());
            if (xory < 0)
                throw new NumberFormatException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xory;
    }
}
