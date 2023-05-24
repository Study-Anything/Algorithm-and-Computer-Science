import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int arow = Integer.parseInt(stk.nextToken());
        int acol = Integer.parseInt(stk.nextToken());
        int [][] aData = new int[arow][acol];
        for(int i = 0 ; i < arow; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j = 0 ; j <acol; j++){
                aData[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        Matrix a = new Matrix(arow, acol, aData);

        stk = new StringTokenizer(br.readLine());
        int brow = Integer.parseInt(stk.nextToken());
        int bcol = Integer.parseInt(stk.nextToken());
        int [][] bData = new int[brow][bcol];
        for(int i = 0 ; i < brow; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j = 0 ; j <bcol; j++){
                bData[i][j] = Integer.parseInt(stk.nextToken());
            }
        }
        Matrix b = new Matrix(brow, bcol, bData);

        Matrix res = matMul(a, b);
        for(int i = 0 ; i < res.r; i++){
            for(int j = 0 ; j < res.c; j++){
                bw.write(res.get(i, j)+" ");
            } bw.newLine();
        }

        bw.flush();
        bw.close();
    }
    static class Matrix{
        int r;
        int c;
        int [][] data;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
        public Matrix(int r, int c, int[][] data) {
            this.r = r;
            this.c = c;
            this.data = data;
        }
        public void setData(int [][] data){
            this.data = data;
        }
        public int get(int i, int j){
            return data[i][j];
        }
    }
    public static Matrix matMul(Matrix a, Matrix b){
        Matrix ret = new Matrix(a.r, b.c);
        int [][] data = new int[a.r][b.c];

        // processing
        for(int i = 0 ; i < a.r; i++){
            for(int j = 0 ; j < b.c; j++){
                int dot = 0;
                for(int k = 0 ; k < a.c; k++){
                    // calc dot product
                    dot += a.get(i, k)*b.get(k, j);
                }
                data[i][j] = dot;
            }
        }

        ret.setData(data);
        return ret;
    }
}
