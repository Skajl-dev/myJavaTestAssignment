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
        хоча б одне значення наступної точки буде співпадати з попереднім */
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

        /* перевіряємо чи сформувався у нас прямокутник. перевірка буде пройдена, якщо кожного разу одне з значень пари буде
        збігатись з значенням з попередньої (90 градусів кут) */
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

        // перевірка на те чи не ставиться 1 точка повторно
        for (int j = 0; j < pairList.size(); j++) {
            x = pairList.get(j).x;
            y = pairList.get(j).y;

            for (int i = j + 1; i < pairList.size(); i++) {
                if (x == pairList.get(i).x && y == pairList.get(i).y)
                    try {
                        throw new LackOfCornersException();
                    } catch (LackOfCornersException e) {
                        e.printStackTrace();
                        return false;
                    }
            }
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

       /* 121 - 191 - перевірка на те чи не перетинаються стіни , відбувається заповнення ліній
        між точками значеннями та перевіркою чи не пробує інша лінія змінити уже встановленні значення */
        int test[][] = new int[Pair.getMaxValue() + 1][Pair.getMaxValue() + 1];
        for (Pair pair : pairList) {
            for (int i = 0; i < test.length; i++) {
                for (int j = 0; j < test.length; j++) {
                    test[pair.x][pair.y] = 200;
                }
            }

        }

        int counter = 1;
        for (int i = 0; i < pairList.size(); i++) {

            if (counter == (pairList.size() - 1))
                counter = 0;

            if (pairList.get(i).x == pairList.get(counter).x) {
                int from = pairList.get(i).y;
                int to = pairList.get(counter).y;

                if (from > to) {
                    int z = to;
                    to = from;
                    from = z;
                }

                for (int j = from; j <= to; j++) {
                    if (test[pairList.get(i).x][j] == 200)
                        continue;
                    if (test[pairList.get(i).x][j] == 300) {
                        try {
                            throw new WallsIntersectException();
                        } catch (WallsIntersectException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }

                    test[pairList.get(i).x][j] = 300;
                }
            }

            if (pairList.get(i).y == pairList.get(counter).y) {
                int from = pairList.get(i).x;
                int to = pairList.get(counter).x;

                if (from > to) {
                    int z = to;
                    to = from;
                    from = z;
                }

                for (int j = from; j <= to; j++) {
                    if (test[j][pairList.get(i).y] == 200)
                        continue;
                    if (test[j][pairList.get(i).y] == 300) {

                        try {
                            throw new WallsIntersectException();
                        } catch (WallsIntersectException e) {
                            e.printStackTrace();
                            return false;
                        }
                    }

                    test[j][pairList.get(i).y] = 300;

                }
            }
            counter++;
        }
        // якщо пройдені всі провірки то прямокутник пройшов тест
        return true;
    }


}
