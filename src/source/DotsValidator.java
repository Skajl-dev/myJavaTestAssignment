package source;

import source.exceptions.DiagonalWallsException;
import source.exceptions.LackOfCornersException;
import source.exceptions.NOTClockwiseGoingException;
import source.exceptions.WallsIntersectException;

import java.util.List;

public class DotsValidator {
    private List<Pair> pairList;


    public DotsValidator(List<Pair> pairList) {
        this.pairList = pairList;
    }

    public boolean isGood() {
        /* перевіряємо чи не утворюються діагональні стіни. перевірка буде пройдена, якщо
        хоча б одне значення наступної точки буде співпадати з попереднім*/
        int x = pairList.get(0).x;
        int y = pairList.get(0).y;
        for (Pair pair : pairList) {
            if (x != pair.x) {
                if (y != pair.y) {
                    try {
                        throw new DiagonalWallsException();
                    } catch (DiagonalWallsException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            x = pair.x;
            y = pair.y;
        }

        // перевіряємо чи у нас є достатньо кутів. перевірка буде пройдена, якщо у листі пар буде 4 і більше пари
        if (pairList.size() < 4)
            try {
                throw new LackOfCornersException();
            } catch (LackOfCornersException e) {
                e.printStackTrace();
                return false;
            }


        // перевіряємо чи сформувався у нас прямокутник. перевірка буде пройдена, якщо кожного разу одне з значень пари буде
        //збігатись з значенням з попередньої (90 градусів кут)
        x = pairList.get(0).x;
        y = pairList.get(0).y;
        for (Pair pair : pairList) {
            if (x == pair.x) {
                y = pair.y;
            } else if (y == pair.y) {
                x = pair.x;
            } else
                try {
                    throw new LackOfCornersException();
                } catch (LackOfCornersException e) {
                    e.printStackTrace();
                    return false;
                }
        }

        // перевірка на те чи не ставиться 1 точка повторно, в цьому нам допоможе попередня і ця перевірка
        x = pairList.get(0).x;
        y = pairList.get(0).y;

        for (int i = 1; i < pairList.size(); i++) {
            if (x == pairList.get(i).x && y == pairList.get(i).y)
                try {
                    throw new LackOfCornersException();
                } catch (LackOfCornersException e) {
                    e.printStackTrace();
                    return false;
                }
        }


        // перевіряємо на те, чи розміщені точки в годинниковому порядку, якщо х останнього елемента буде > за х першого
        // то така послідовність буде нарушеною
        if (pairList.get(0).x > pairList.get(pairList.size() - 1).x) {
            try {
                throw new NOTClockwiseGoingException();
            } catch (NOTClockwiseGoingException e) {
                e.printStackTrace();
                return false;
            }
        }

        // якщо мін. значення y буде нижче значення y початкової пари, то ми йдемо не по часовій стрілці
        if (Pair.getMinY() < pairList.get(0).y)
            try {
                throw new NOTClockwiseGoingException();
            } catch (NOTClockwiseGoingException e) {
                e.printStackTrace();
                return false;
            }

        // для того щоб рух був по годинниковій стрілці, поворот вправо повинен бути в найвищій точці y
        int maxY = Pair.getMaxY();
        int[] arr = new int[2]; // може бути тільки 2 найвищі точки
        int count = 0;
        for (Pair pair : pairList) {
            if (maxY == pair.y) {
                arr[count++] = pair.x;
            }
        }
        if (arr[0] > arr[1])
            try {
                throw new NOTClockwiseGoingException();
            } catch (NOTClockwiseGoingException e) {
                e.printStackTrace();
                return false;
            }

        // остання точка повинна лежати навпроти першої
        if (pairList.get(0).x != pairList.get(pairList.size() - 1).x && pairList.get(0).y != pairList.get(pairList.size() - 1).y) {
            try {
                throw new LackOfCornersException();
            } catch (LackOfCornersException e) {
                e.printStackTrace();
                return false;
            }
        }


// якщо пройдені всі провірки то прямокутник пройшов тест
        return true;
    }


}
