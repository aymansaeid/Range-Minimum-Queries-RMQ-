/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package last;

class PrecomputeAll {
    private int[][] table;

    public PrecomputeAll(int[] arr) {
        int n = arr.length;
        table = new int[n][n];
        for (int i = 0; i < n; i++) {
            table[i][i] = arr[i];
            for (int j = i + 1; j < n; j++) {
                table[i][j] = Math.min(table[i][j - 1], arr[j]);
            }
        }
    }

    public int query(int i, int j) {
        return table[i][j];
    }
}