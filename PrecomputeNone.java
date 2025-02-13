/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package last;

class PrecomputeNone {
    private int[] arr;

    public PrecomputeNone(int[] arr) {
        this.arr = arr;
    }

    public int query(int i, int j) {
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            min = Math.min(min, arr[k]);
        }
        return min;
    }
}