import java.util.ArrayList;
import java.util.Arrays;

public class Bingo {
    public int[] play(int[][] calledSquares, int[][][] cardData) {
        int numCards = cardData.length;
        ArrayList<Integer> result = new ArrayList<>();
        if (numCards < 1)
        {
            throw new IllegalArgumentException("No card data provided");
        }
        int numRows = cardData[0].length;
        int numColumns = cardData[0][0].length;

        int turn = 0;
        for (int sqIdx = 0; sqIdx < calledSquares.length; sqIdx++) {
            if ((turn != calledSquares[sqIdx][0]) && (result.size() > 0))
            {
                // New turn started and there is at least one winner
                break;
            }

            turn = calledSquares[sqIdx][0];
            int sqVal = calledSquares[sqIdx][1];

            for (int card = 0; card < numCards; card++) {
                boolean hasMatch = false;
                int matchRow = -1;
                int matchCol = -1;

                for (int row = 0; (row < numRows) && (hasMatch == false); row++) {
                    for (int col = 0; (col < numColumns) && (hasMatch == false); col++) {
                        // Check if the called value is present
                        if (cardData[card][row][col] == sqVal) {
                            hasMatch = true;
                            matchRow = row;
                            matchCol = col;
                            // set found number to "-1" as marked (center should have -1 at start)
                            cardData[card][row][col] = -1;
                            break;
                        }
                    }
                }
            
                // If a match is found, check for win condition
                if (hasMatch) {
                    if (checkWin(cardData[card], matchRow, matchCol))
                    {
                        result.add(card);
                    }
                }
            }
        }

        int[] intArray = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            intArray[i] = result.get(i);
        }
        return intArray;
    }

    public boolean checkWin(int[][] card, int row, int col) {

        int numRows = card.length;
        int numCols = card[0].length;
        if (numRows != numCols)
        {
            throw new IllegalArgumentException("Bingo card dimensions are wrong");
        }
        
        // check if the row is done
        boolean win = true;
        for (int i = 0; i < numCols; i++)
        {
            if (card[row][i] != -1)
            {
                win = false;
                break;
            }
        }
        if (win == true)
        {
            return true;
        }

        // check if the column is done
        win = true;
        for (int i = 0; i < numRows; i++)
        {
            if (card[i][col] != -1)
            {
                win = false;
                break;
            }
        }
        if (win == true)
        {
            return true;
        }

        // check if the \ diagonal is done
        if (col == row)
        {
            win = true;
            for (int i = 0; i < numRows; i++)
            {
                if (card[i][i] != -1)
                {
                    win = false;
                    break;
                }
            }
            if (win == true)
            {
                return true;
            }
        }

        // check if the / diagonal is done
        if ((col + row + 1) == numRows)
        {
            win = true;
            for (int i = 0; i < numRows; i++)
            {
                if (card[i][numRows - i - 1] != -1)
                {
                    win = false;
                    break;
                }
            }
            if (win == true)
            {
                return true;
            }
        }

        // No win condition found;
        return false;
    }

    public static void test0(int[][] calledSquares, int[][][] cardData, int[] expRes) {
        Bingo game = new Bingo();
        int[] result = game.play(calledSquares, cardData);
    
        System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName() + " result: " 
        + (Arrays.equals(result, expRes) ? "Pass" : "Fail"));
    }

    public static void test1() {
        int[][] calledSquares = new int[][] {
            new int[]{1, 21},
            new int[]{1, 19},
            new int[]{1, 24},
            new int[]{1, 20},
            new int[]{1, 30}
        };
        int[][][] cardData = new int[][][] {
            new int[][]{
                new int[]{6, 21, 36, 55, 61},
                new int[]{12, 19, 43, 56, 69},
                new int[]{9, 24, -1, 46, 71},
                new int[]{3, 20, 44, 52, 67},
                new int[]{1, 30, 34, 57, 65}
            },
            new int[][]{
                new int[]{4, 16, 40, 46, 72},
                new int[]{10, 17, 41, 58, 62},
                new int[]{2, 26, -1, 48, 66},
                new int[]{7, 18, 37, 60, 63},
                new int[]{14, 30, 35, 59, 73}
            }
        };
        int[] expRes = new int[]{0};
        test0(calledSquares, cardData, expRes);
    }

    public static void test2() {
    
        int[][] calledSquares = new int[][]{
            new int[]{0, 40},
            new int[]{1, 41},
            new int[]{3, 37},
            new int[]{4, 35}
        };
        int[][][] cardData = new int[][][]{
            new int[][]{
                new int[]{6, 21, 36, 55, 61},
                new int[]{12, 19, 43, 56, 69},
                new int[]{9, 24, -1, 46, 71},
                new int[]{3, 20, 44, 52, 67},
                new int[]{1, 30, 34, 57, 65}
            },
            new int[][]{
                new int[]{4, 16, 40, 46, 72},
                new int[]{10, 17, 41, 58, 62},
                new int[]{2, 26, -1, 48, 66},
                new int[]{7, 18, 37, 60, 63},
                new int[]{14, 30, 35, 59, 73}
            }
        };
        int[] expRes = new int[]{1};
        test0(calledSquares, cardData, expRes);
    }

    public static void test3() {
    
        // Both cards win in one turn
        int[][] calledSquares = new int[][]{
            new int[]{0, 21},
            new int[]{0, 19},
            new int[]{0, 24},
            new int[]{1, 20},
            new int[]{1, 30},
            new int[]{1, 40},
            new int[]{1, 41},
            new int[]{1, 37},
            new int[]{1, 35},
            new int[]{1, 69},
            new int[]{2, 71},
            new int[]{2, 67}
        };
        int[][][] cardData = new int[][][]{
            new int[][]{
                new int[]{6, 21, 36, 55, 61},
                new int[]{12, 19, 43, 56, 69},
                new int[]{9, 24, -1, 46, 71},
                new int[]{3, 20, 44, 52, 67},
                new int[]{1, 30, 34, 57, 65}
            },
            new int[][]{
                new int[]{4, 16, 40, 46, 72},
                new int[]{10, 17, 41, 58, 62},
                new int[]{2, 26, -1, 48, 66},
                new int[]{7, 18, 37, 60, 63},
                new int[]{14, 30, 35, 59, 73}
            }
        };
        int[] expRes = new int[]{0, 1};
        test0(calledSquares, cardData, expRes);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
