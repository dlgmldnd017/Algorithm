class Solution {
    public int[] solution(int brown, int yellow) {
        int y=3, x=3;
		int yArea=0, bArea=0;
		
		while(true) {
			yArea = (y-2) * (x-2);
			
			if(yArea!=yellow) {
				if(yArea>yellow) {
					x++;
					y=x;
				}else {
					y++;
				}
				continue;
			}
			
			bArea = y*2 + 2*(x-2);
			if(bArea!=brown) {
				x++;
				y=x;
			}else break;
		}
        
        int ans[] = {y, x};
        return ans;
    }
}