package moBanMen;

public class BinarySearch {
    //左右越过,找specific
    public int binarySearch(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(array[mid] == target) {
                return mid;
            }else if(array[mid] > target) {
                left = mid + 1;;
            }else{
                right = mid - 1;
            }
        }
        return -1;
    }
    //左右相邻，找分界线
    public int binarySearch2(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;
        while(left + 1 < right) {
            int mid = left + (right - left) / 2;
            if(array[mid] == target) {
                return mid;
            }else if(array[mid] > target) {
                left = mid;
            }else{
                right = mid;
            }
        }
        if (array[left] == target) return left;
        if (array[right] == target) return right;
        return -1;
    }

    //search in matrix, search space reduction
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length-1;
        int col = 0;

        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else if (matrix[row][col] < target) {
                col++;
            } else {
                return true;
            }
        }

        return false;
    }
}
