/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package last;
class SparseTable {
    private int[][] table;
    private int[] log;

    public SparseTable(int[] arr) {
        int n = arr.length;
        int k = (int) (Math.log(n) / Math.log(2)) + 1;
        table = new int[n][k];
        log = new int[n + 1];

        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        for (int i = 0; i < n; i++) {
            table[i][0] = arr[i];
        }

        for (int j = 1; j < k; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                table[i][j] = Math.min(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int query(int i, int j) {
        int len = j - i + 1;
        int k = log[len];
        return Math.min(table[i][k], table[j - (1 << k) + 1][k]);
    }
}