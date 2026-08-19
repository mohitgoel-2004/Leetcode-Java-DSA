class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Integer> rowMasks = new HashMap<>();

        for(int[] seat : reservedSeats){
            int row = seat[0];
            int col = seat[1];

            if(col >= 2 && col<=9){
                rowMasks.put(row,  rowMasks.getOrDefault(row, 0) | (1<<(col-2)));
            }
        } 
        int t = (n - rowMasks.size()) * 2;

        int l = 0b00001111;
        int m = 0b00111100;
        int r = 0b11110000;

        for(int mask :  rowMasks.values()){
            boolean canL = (mask & l) == 0;
            boolean canR = (mask & r) == 0;
            boolean canM = (mask & m) == 0;

            if(canL && canR){
                t +=2;
            } else if(canL || canR || canM){
                t +=1;
            }
        }
        return t;
    }
}