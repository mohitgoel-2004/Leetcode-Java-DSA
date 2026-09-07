class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totGas = 0, totCost = 0;
      
      for(int val : gas){
        totGas += val;
      }
       for(int val : cost){
        totCost += val;
      }
      if(totGas < totCost){
        return -1;
      }

      int start = 0, CurrGas =0;

      for(int i=0; i<gas.length; i++){
        CurrGas += (gas[i] - cost[i]);
          
         if(CurrGas < 0){
            start = i+1;
            CurrGas=0;
         }
      }
      return start;
    }
}