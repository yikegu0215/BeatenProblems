package moBanMen;

public class MergeK {
    //merge K linked list
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        int left = 0;
        int right = lists.length - 1;
        return mergeList(lists,left,right);
    }

    private ListNode mergeList(ListNode[] lists, int left, int right)   {
        if (left >= right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode cur1 = mergeList(lists, left, mid);
        ListNode cur2 = mergeList(lists,mid + 1,right);
        return mergeTwo(cur1, cur2);
    }
    private ListNode mergeTwo(ListNode cur1, ListNode cur2) {
        if (cur1 == null) {
            return cur2;
        }
        if (cur2 == null) {
            return cur1;
        }
        ListNode k = new ListNode(0);
        ListNode dummy = k;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                k.next = cur1;
                cur1 = cur1.next;
            }else{
                k.next = cur2;
                cur2 = cur2.next;
            }
            k = k.next;
        }
        if (cur1 != null) {
            k.next = cur1;
        }
        if (cur2 != null) {
            k.next = cur2;
        }
        return dummy.next;
    }

    //mergeSort
    public static int[] mergeSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        merge(nums, left, right);
        return nums;
    }
    private static void merge(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        merge(nums, left, mid);
        merge(nums, mid + 1, right);
        int i = left;
        int j = mid + 1;
        int p = 0;
        int[] temp = new int[right - left + 1];
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                temp[p++] = nums[i++];
            }else {
                temp[p++] = nums[j++];
            }
        }
        while (i <= mid) {
            temp[p++] = nums[i++];
        }
        while (j <= right) {
            temp[p++] = nums[j++];
        }
        for (int k = 0; k < temp.length; k++) {
            nums[k + left] = temp[k];
        }

    }

}
