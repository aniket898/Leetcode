package com.aniket.test;

class PourWater {

    public static void main(String[] args) {
//            int[] output = pourWater(new int[] {2,1,1,2,1,2,2},4,3);
//            int[] output = pourWater(new int[] {14,9,10,9,7,9,7,5,3,2},7,9);
            int[] output = pourWater2(new int[] {1,2,3,4},2,2);
            for(int op: output){
                System.out.println(op);
            }
    }

    public static int[] pourWater2(int[] heights,int V,int K){
        while(V > 0){
            dropWater(heights,K);
            V--;
        }
        return heights;
    }

    private static void dropWater(int[] heights,int K){
        int best = K;
        for(int d = -1; d <= 1; d += 2){
            int i = K + d;
            while(i >= 0 && i < heights.length && heights[i] <= heights[i-d]){
                if(heights[i] < heights[best]) best = i;
                i += d;
            }
            if(best != K)break;
        }
        ++heights[best];
    }
}
