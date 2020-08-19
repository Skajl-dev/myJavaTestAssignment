package source;

import java.util.ArrayList;
import java.util.List;

public class CartesianGrid {
    private int size;
    private int[][] array;
    private List<Pair> pairList;

    public CartesianGrid(int size, List<Pair> pairList) {
        this.size = size + 1; // щоб елементи нормально поміщались у масив
        this.pairList = pairList;
        array = new int[this.size][this.size];
    }


    private void putDecorationsOn() {
        // наносимо точки на наш масив (на графіку буде видно цифру, яка відображає послідовність її введення
        int number = 1;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0)
                    array[i][j] = j;

                if (j == 0)
                    array[i][j] = i;

            }
        }


        for (Pair pair : pairList) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (pair.y == j && pair.x == i) {
                        array[i][j] = number++;
                    }
                }
            }
        }

    }

    public void cartesianGridPrint() {
        // виводимо наш масив з точками та позначеними осями координат на екран
        putDecorationsOn();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(array[i][j] + " ");
            }
            if (i == 0)
                System.out.print("  Y");
            System.out.println();
        }
        System.out.println();
        System.out.println("X");

    }

    public void printValues() {
        System.out.println("\'room\':[");
        for (int i = 0; i < pairList.size(); i++) {
            if (i != pairList.size() - 1)
                System.out.format("{x : %d, y : %d},\n", pairList.get(i).x, pairList.get(i).y);
            else
                System.out.format("{x : %d, y : %d}\n", pairList.get(i).x, pairList.get(i).y);
        }

        System.out.println("]");

    }

    public String valuesToSave() {
        String result = "\'room\':[";
        StringBuilder sb = new StringBuilder(result);

        for (int i = 0; i < pairList.size(); i++) {
            if (i != pairList.size() - 1)
                sb.append("{x : " + pairList.get(i).x + ", y : " + pairList.get(i).y + "},");
            else
                sb.append("{x : " + pairList.get(i).x + ", y : " + pairList.get(i).y + "}.");
        }
        return sb.toString();
    }


    public int[][] getArray() {
        return array;
    }
}
