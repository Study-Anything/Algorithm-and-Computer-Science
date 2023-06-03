import java.io.*;
import java.util.*;
// boj 12099
public class Main {
    static Menu[] menus;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stk;

        stk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(stk.nextToken()); // 점심 메뉴 수
        int q = Integer.parseInt(stk.nextToken()); // 쿼리 수
        menus = new Menu[n];
        for (int i = 0; i < n; i++) {
            stk = new StringTokenizer(br.readLine());
            int spicy = Integer.parseInt(stk.nextToken());
            int sweet = Integer.parseInt(stk.nextToken());
            menus[i] = new Menu(spicy, sweet);
        }

        Arrays.sort(menus, Comparator.comparingInt(Menu::getSpicy));
        for (int i = 0; i < q; i++) {
            stk = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(stk.nextToken());
            int v = Integer.parseInt(stk.nextToken());
            int x = Integer.parseInt(stk.nextToken());
            int y = Integer.parseInt(stk.nextToken());
            int cnt = 0;
            int[] range = {lower_bound_spicy(menus, u), upper_bound_spicy(menus, v)};
            for (int j = range[0]; j < range[1]; j++) {
                if (x <= menus[j].sweet && menus[j].sweet <= y) {
                    cnt++;
                }
            }
            bw.write(cnt+"\n");
        }
        bw.flush();
        bw.close();
    }

    public static int upper_bound_spicy(Menu[] menus, int target) {
        int lo = -1;
        int hi = menus.length;
        int mid = 0;
        while (lo + 1 < hi) {
            mid = (lo + hi) / 2;
            if (!(menus[mid].spicy > target)) { // menus[mid].spicy <= target
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }

    public static int lower_bound_spicy(Menu[] menus, int target) {
        int lo = -1;
        int hi = menus.length;
        int mid = 0;
        while (lo + 1 < hi) {
            mid = (lo + hi) / 2;
            if (!(menus[mid].spicy >= target)) { // menus[mid].spicy < target
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return hi;
    }


    public static class Menu {
        int spicy;
        int sweet;

        public Menu(int spicy, int sweet) {
            this.spicy = spicy;
            this.sweet = sweet;
        }

        public int getSpicy() {
            return spicy;
        }

        public int getSweet() {
            return sweet;
        }
    }
}
