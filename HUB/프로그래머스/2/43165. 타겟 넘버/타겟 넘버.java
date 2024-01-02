class Solution {
    static int ans;
    
    public int solution(int[] numbers, int target) {
        solve(0, 0, numbers, target);
        return ans;
    }
    
    static void solve(int depth, int sum, int[] numbers, int target) {
		if(depth==numbers.length) {
			if(sum==target) ans++;
			return;
		}
		
		solve(depth+1, sum+numbers[depth], numbers, target);
		solve(depth+1, sum-numbers[depth], numbers, target);
	}
}