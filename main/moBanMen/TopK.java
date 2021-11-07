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
}
