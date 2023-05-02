import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[][] sudoku;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stk;
        sudoku = new int[9][9];
        for(int i = 0; i < 9; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9 ; j++){
                sudoku[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        make(0, 0);
    }

    public static void make(int row, int col) throws IOException {
        if (col == 9) {
            make(row + 1, 0);
            return;
        }
        if (row == 9) {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    bw.write(sudoku[i][j] + " ");
                }
                bw.newLine();
            }
            bw.close();
            System.exit(0);
        }
        if (sudoku[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if(isitPossible(row, col, i)){
                    sudoku[row][col] = i;
                    make(row, col+1);
                }
            }
            sudoku[row][col] = 0;
            return;
        }

        make(row, col+1);
    }

    public static boolean isitPossible(int row, int col, int x) {
        // {row, col} 위치에 x 가 들어갈 수 있는가? -> 1. row check, 2. col check, 3. box check
        for (int i = 0; i < 9; i++) {
            if (sudoku[row][i] == x) {
                return false;
            }
        }
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][col] == x) {
                return false;
            }
        }
        for (int yy = (row / 3) * 3; yy < (row / 3) * 3 + 3; yy++) {
            for (int xx = (col / 3) * 3; xx < (col / 3) * 3 + 3; xx++) {
                if (sudoku[yy][xx] == x) {
                    return false;
                }
            }
        }
        return true;
    }
}
