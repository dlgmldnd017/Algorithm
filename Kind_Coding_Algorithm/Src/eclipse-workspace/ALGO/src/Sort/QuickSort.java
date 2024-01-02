package Sort;

public class QuickSort {
	private static int[] src = {38, 27, 43, 9, 3, 82, 10};

	public static void main(String[] args) {
		System.out.print("정렬 전: ");
		printArray(src);
		
		quickSort(src, 0, src.length-1);
		
		System.out.print("정렬 후: ");
		printArray(src);
	}

    private static void quickSort(int[] A, int low, int high) {
    	
    	// 원소가 더이상 쪼개지지 않는다면, 리턴
        if (low >= high) return;

        int mid = partition(A, low, high);
        quickSort(A, low, mid - 1);
        quickSort(A, mid, high);
    }

    private static int partition(int[] A, int low, int high) {
        
    	// 피봇 값을 제일 왼쪽에 있는 원소로 지정한다.
    	int pivot = A[low];
        
        while (low <= high) {
        	
        	// 만약 원소 값이 피봇 값보다 작다면 옮길 필요 없으므로, 다음 원소 비교
            while (A[low] < pivot) low++;
            
         // 만약 원소 값이 피봇 값보다 크다면 옮길 필요 없으므로, 다음 원소 비교
            while (A[high] > pivot) high--;
            
            // 만약 옮길 대상이 존재한다면, 바로 swap()으로 원소 교체
            if (low <= high) {
                swap(A, low, high);
                low++;
                high--;
            }
        }
        return low;
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    
    private static void printArray(int[] A) {
		for(int i:A) {
			System.out.print(i+" ");
		}
		System.out.println();
	}
}