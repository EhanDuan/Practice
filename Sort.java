import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program: Practice
 * @description: BubbleSort
 * @author: Yihan Duan
 * @create: 2021-05-19 21:44
 **/


import java.util.Arrays;
import java.util.Random;

public class Sort {
    public static int[] bubbleSort(int[] array){
        if(array.length == 0 || array == null)
            return null;

        for(int i = 0; i < array.length - 1; i++){
            for(int j = 0; j < array.length - 1 - i; j++){
                if(array[j] > array[j + 1]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static int[] quickSort(int[] array){
        if(array.length == 0 || array == null)
            return null;

        int[] res;
        res = quickSortHelper(array, 0, array.length - 1);
        return res;
    }

    public static int[] quickSortHelper(int[] array, int start, int end){
        if(start >= end)
            return null;
        
        int mid = (start + end)/2;
        int pivot = array[mid];
        int left = start;
        int right = end;

        // End condition, left ptr < right ptr
        while(left <= right){
            //traverse the left part and right part, bigger to right, smaller to left
            while(left <= right && array[left] < pivot){
                left++;
            }
            while(left <= right && array[right] > pivot){
                right--;
            }
            if(left <= right){
                // swap
                int temp =  array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }

        }
        quickSortHelper(array,start,right);
        quickSortHelper(array,left, end);

        // deep copy
        int[] res = new int[array.length];
        for(int i = 0; i < array.length; i++){
            res[i] = array[i];
        }

        return res;

    }

    public static int[] insertSort(int[] array){
        if(array.length == 0 || array == null)
            return null;

        int[] res;
        res = insertSortHelper(array);
        return res;

    }

    public static int[] insertSortHelper(int[] array) {
        for(int i = 1; i < array.length; i++){
            for(int j = i - 1; j >= 0; j--){
                // if number at i < number at j, swap and i = j
                if(array[i] < array[j]){
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                    i = j;
                }
            }
        }
        return array;
    }

    public static void insertSortBiliBili(int[] array){
        for(int i = 1; i < array.length; i++){
            if(array[i] < array[i - 1]){
                int temp = array[i];
                int j;
                for(j = i - 1; j >= 0 && temp < array[j]; j--){
                    array[j + 1] = array[j];
                }
                array[j + 1] =temp;
            }
        }

    }

    public static int[] shellSort(int[] array){
        if(array.length == 0 || array == null)
            return null;

        // Traverse all the gap
        for(int gap = array.length/2; gap > 0; gap /= 2 ){
            // Traverse different groups with different starting point
            for(int i = gap; i < array.length; i++){
                //Traverse single group to insert sort
                for(int j = i - gap; j >= 0; j -= gap){
                    if(array[j] > array[j + gap]){
                        //swap
                        int temp = array[j + gap];
                        array[j + gap] = array[j];
                        array[j] = temp;
                    }
                }
            }
        }
        return array;

    }

    public static int[] selectionSort(int[] array){
        if(array.length == 0 || array == null){
            return null;
        }

        int minLoc;
        int temp;
        // traverse each element
        for(int i = 0; i < array.length; i++){
            minLoc = i;
            //for each element, traverse element after, find min and swap to current head
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[minLoc])
                    minLoc = j;
            }
            // check if i == min
            if(i != minLoc){
                temp = array[i];
                array[i] = array[minLoc];
                array[minLoc] = temp;
            }
        }
        return array;

    }

    /**
     * The method to generate the random array
     * @return int[] of random size of random numbers
     */
    public static int[] randomArray(){
        Random random = new Random();
        int size = random.nextInt(40);
        int[] array = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = random.nextInt(100);
        }
        return array;
    }

    public static int[] mergeSort(int[] array){
        if(array.length == 0 || array == null)
            return null;

        int[] temp = new int[array.length];
        mergeSortPartition(array, 0, array.length - 1, temp);
        return array;
    }

    private static void mergeSortPartition(int[] array, int start, int end, int[] temp) {
        if(start >= end)
            return;

        int mid = (start + end) / 2;

        mergeSortPartition(array,start, mid, temp);
        mergeSortPartition(array,mid + 1, end, temp);
        // Merge
        mergeSortMerge(array,start,end,temp);
    }

    private static void mergeSortMerge(int[] array, int start, int end, int[] temp) {
        if(start >= end)
            return;

        int mid = (start + end) / 2;
        int left = start, right = mid + 1, index = start;
        while(left <= mid && right <= end){
            if(array[left] < array[right]){
                temp[index] = array[left];
                index ++;
                left ++;
            }else{
                temp[index] = array[right];
                index ++;
                right ++;
            }
        }
        while(left <= mid){
            temp[index] = array[left];
            index ++;
            left ++;
        }
        while(right <= end){
            temp[index] = array[right];
            index ++;
            right ++;
        }
        //copy
        for(int i = start; i <= end; i++){
            array[i] = temp[i];
        }
    }

    public static int[][] randomSquareArray(){
        Random random = new Random();
        int rowSize = random.nextInt(10);
        int colSize = random.nextInt(10);
        int[][] array = new int[rowSize][colSize];

        for(int i = 0; i < rowSize; i++){
            for(int j = 0; j < colSize; j++){
                array[i][j] = random.nextInt(100);
            }
        }
        return array;
    }

    public static void printSquareArray(int[][] array){
        for(int i = 0; i < array.length; i++){
            System.out.print("[ ");
            for(int j = 0; j < array[i].length; j++){
                System.out.print(array[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        int[] test = Sort.randomArray();
//        System.out.println(Arrays.toString(test));
//        Sort.insertSort(test);
        System.out.println(Arrays.toString(test));
//        Sort.insertSortBiliBili(test);
        Sort.mergeSort(test);
        System.out.println(Arrays.toString(test));
//        int[][] square = Sort.randomSquareArray();
//        Sort.printSquareArray(square);

    }


}
