package engine.field.parts;


import java.util.Random;

/**
 * точка входа не может заходить в любйю угловую ячейку
 * 0 неактивная ячейка
 * 1 обычная ячейка трассы
 * 2 ячейка старта первого игрока (указывается только вместе с 3 или не указывается вообще)
 * 3 ячейка старта второго игрока (указывается только вместе с 2 или не указывается вообще)
 * 4 ячейка финиша
 * 5 ячейка входа (не может быть в углу)
 * 6 ячейка выхода (не может быть в углу)
 */
public class StandardPartMapFinish {

    public static final int COUNT_TOP_ENTER = 1;
    public static final int COUNT_DOWN_ENTER = 1;
    public static final int COUNT_LEFT_ENTER = 1;
    public static final int COUNT_RIGHT_ENTER = 1;

    private static byte[][] getInitPartFinishWhereEnterDown(Random random) {
        switch (random.nextInt(COUNT_DOWN_ENTER) + 1) {
            case 1:
                return initPartFinish1;
        }
        return null;
    }

    private static byte[][] getInitPartFinishWhereEnterTop(Random random) {
        switch (random.nextInt(COUNT_TOP_ENTER) + 1) {
            case 1:
                return initPartFinish2;
        }
        return null;
    }

    private static byte[][] getInitPartFinishWhereEnterLeft(Random random) {
        switch (random.nextInt(COUNT_LEFT_ENTER) + 1) {
            case 1:
                return initPartFinish3;
        }
        return null;
    }

    private static byte[][] getInitPartFinishWhereEnterRight(Random random) {
        switch (random.nextInt(COUNT_RIGHT_ENTER) + 1) {
            case 1:
                return initPartFinish4;
        }
        return null;
    }


    /**
     * top - 1
     * down - 2
     * left - 3
     * right - 4
     */
    public static byte[][] getInitPartFinish(Random random, byte directionExit) {
        switch (directionExit) {
            case 1:
                return getInitPartFinishWhereEnterDown(random);
            case 2:
                return getInitPartFinishWhereEnterTop(random);
            case 3:
                return getInitPartFinishWhereEnterRight(random);
            case 4:
                return getInitPartFinishWhereEnterLeft(random);
        }
        return null;
    }

    public static byte[][] initPartFinish1 = {
            {0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0},
    };

    public static byte[][] initPartFinish2 = {
            {0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0},
    };

    public static byte[][] initPartFinish3 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 6},
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 6},
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 6},
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 6},
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 6},
            {5, 1, 1, 1, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 6},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    public static byte[][] initPartFinish4 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {6, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 5},
            {6, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 5},
            {6, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 5},
            {6, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 5},
            {6, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 5},
            {6, 1, 1, 1, 1, 1, 4, 1, 1, 1, 1, 1, 1, 1, 1, 5},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
}
