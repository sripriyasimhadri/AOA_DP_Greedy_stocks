import java.util.*;
class DP6b {
    public static void main(String []args)
    {
        int k = 3;
        int[][] price = { {8,1,13,4,5 }, { 6, 12, 8, 17, 13}, {9, 8, 6, 4, 7}, {1, 4, 3, 2,1} };
        int n = price[0].length;
        int m = price.length;
        int[][] profit = new int[k+1][n];;
        int selling =0;

        for (int i = 0; i <= k; i++)
            profit[i][0] = 0;

        for (int j = 0; j < n; j++)
            profit[0][j] = 0;

        for (int a = 1; a < profit.length; a++) {
            int[] currentDif = new int[m];
            for (int b = 0; b < m; b++)
            {
                currentDif[b] = -price[b][0];
            }
            for (int z = 1; z < n; z++) {
                profit[a][z] = profit[a][z-1];
                for(int c = 0; c < m; c++)
                {
                    currentDif[c] = Math.max(currentDif[c], profit[a-1][z] - price[c][z]);
                    profit[a][z] = Math.max(profit[a][z], price[c][z] + currentDif[c]);
                }

            }
        }
        for(int a = 0; a <= k; a++){
            for(int b = 0; b< n ;b++){
                System.out.printf("%4d", profit[a][b]);
            }
            System.out.print("\n");
        }
        System.out.println("Maximum Profit is: "+profit[k][n - 1]);

        int i = profit.length - 1;
        int j = profit[0].length - 1;
        Deque<Integer> stack = new LinkedList<>();
        Deque<Integer> stock_ind = new LinkedList<>();
        while(true && i!=0 && j!=0) {
            if (profit[i][j] == profit[i][j-1]) {
                j = j - 1;
            } else {
                stack.addFirst(j);

                int maxDiff[] = new int[price.length];
                for (int l = 0; l < price.length; l++)
                {
                    maxDiff[l] = profit[i][j] - price[l][j];
                }
                int flag = 0;
                for (int c = j-1; c >= 0; c--)
                {
                    for (int p = 0; p < price.length; p++)
                    {
                        for(int l = 0; l< price.length; l++)
                        {
                            if ((profit[i-1][c] - price[p][c] == maxDiff[l]) && ((profit[i-1][c] + price[p][j]- price[p][c])==profit[i][j])) {
                                i = i - 1;
                                j = c;
                                stack.addFirst(j);
                                stock_ind.addFirst(l);
                                flag = 1;
                                break;
                            }
                        }
                        if( flag == 1)
                            break;
                    }
                    if( flag == 1)
                        break;
                }

            }
        }

        while(!stack.isEmpty()) {
            int st_ind = stock_ind.pollFirst();
            System.out.println((st_ind)+ " "+(stack.pollFirst())+" "+(stack.pollFirst()));
        }
    }
}
