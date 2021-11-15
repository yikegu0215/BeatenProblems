package moBanMen;

import java.util.*;

public class TopK {
    //Heap
    public int[] topKFrequent(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i],0) + 1);
        }

        Queue<Integer> pq = new PriorityQueue<>((o1,o2) -> freqMap.get(o1) - freqMap.get(o2));

        for(int n : freqMap.keySet()) {
            pq.offer(n);
            if(pq.size() > k) {
                pq.poll();
            }
        }

        int[] result = new int[k];
        for(int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }
        return result;
    }


//QuickSelect with Duplicates, Kth largest element in Array
public int findKthLargest(int[] nums, int k) {
    if(nums == null || nums.length == 0 || k <= 0) {
        throw new IllegalArgumentException();
    }
    int left = 0;
    int right = nums.length - 1;

    return quickselect(nums, k, left, right);
}

    private int quickselect(int[] nums, int k, int left, int right) {
        int mid = left + (right - left ) / 2;
        int pivot = nums[mid];

        int i = left;
        int j = right;
        int t = left;
        while(t <= j) {
            if(nums[t] < pivot) {
                swap(nums,i++,t++);
            }else if (nums[t] > pivot) {
                swap(nums,j--,t);
            }else{
                t++;
            }
        }

        if(right - j >= k) {
            return quickselect(nums, k,j + 1, right);
        }else if (right - i + 1 >= k) {
            return pivot;
        }else{
            return quickselect(nums, k -(right - i + 1), left, i - 1);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //QuickSelect
    public int[] topKFrequent2(int[] nums, int k) {
        if (k == nums.length) {
            return nums;
        }
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        //build unique array
        int[] uniqueArray = new int[n];
        int i = 0;
        for (int num : freqMap.keySet()) {
            uniqueArray[i] = num;
            i++;
        }
        int unique = uniqueArray.length - 1;
        quickSelect(uniqueArray, freqMap, 0, unique, k);
        int[] result = new int[k];
        for (int p = 0; p < k; p++) {
            result[p] = uniqueArray[p];
        }
        return result;
    }
    private void quickSelect(int[] uniqueArray, Map<Integer, Integer> frequencyMap, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        int cur = frequencyMap.get(uniqueArray[mid]);
        swap(uniqueArray, mid, right);
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (frequencyMap.get(uniqueArray[i]) > cur) {
                i++;
            }else if (frequencyMap.get(uniqueArray[j]) <= cur) {
                j--;
            }else {
                swap(uniqueArray, i , j);
            }
        }
        swap(uniqueArray, i , right);
        if (i == k) {
            return;
        }else if (i < k) {
            quickSelect(uniqueArray, frequencyMap, i + 1, right, k);
        }else{
            quickSelect(uniqueArray, frequencyMap, left, i - 1, k);
        }

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //bucket sort (words- trie)
    public static int[] bucketSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] bucket = new int[max - min + 1];
        for (int num : nums) {
            bucket[num - min] ++;
        }

        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i] > 0) {
                nums[index] = i + min;
                index++;
                bucket[i]--;
            }
        }
        return nums;
    }

    //quicksort
    public static int[] quickSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        quick(nums, left, right);
        return nums;
    }

    private static void quick(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        int target = nums[mid];
        swap(nums, left, mid);
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (nums[i] <= target) {
                i++;
            }else if (nums[j] > target) {
                j--;
            }else {
                swap(nums,i,j);
            }
        }
        swap(nums, left, j);
        quick(nums, left, j);
        quick(nums, i, right);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
