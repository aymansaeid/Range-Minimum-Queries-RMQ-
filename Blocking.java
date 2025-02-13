/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package last;

class Blocking {
    private int[] blockMins;
    private int blockSize;
    private int[] arr;

    public Blocking(int[] arr) {
        this.arr = arr;
        int n = arr.length;
        blockSize = (int) Math.sqrt(n);
        int blockCount = (n + blockSize - 1) / blockSize;
        blockMins = new int[blockCount];

        for (int i = 0; i < blockCount; i++) {
            blockMins[i] = Integer.MAX_VALUE;
            for (int j = i * blockSize; j < Math.min((i + 1) * blockSize, n); j++) {
                blockMins[i] = Math.min(blockMins[i], arr[j]);
            }
        }
    }

    public int query(int i, int j) {
        int min = Integer.MAX_VALUE;
        while (i <= j && i % blockSize != 0) {
            min = Math.min(min, arr[i++]);
        }
        while (i + blockSize - 1 <= j) {
            min = Math.min(min, blockMins[i / blockSize]);
            i += blockSize;
        }
        while (i <= j) {
            min = Math.min(min, arr[i++]);
        }
        return min;
    }
}