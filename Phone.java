
package strings;

import java.util.HashMap;

public class Phone {

	public static void main(String[] args) {
		
		 String S = "00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090";
		 int num = solution(S);
		 System.out.println(num);
		// TODO Auto-generated method stub

	}
	
	private static int solution(String S) {
		
		String[] singleEntryArray = S.split("\n");
		
		HashMap<String, Integer> billMap = new HashMap<String, Integer>();
		
		int totalBill = 0;
		for(int i=0; i<singleEntryArray.length; i++) {
			//00:01:07,400-234-090
			int thisBill =0;
			String duration = singleEntryArray[i].split(",")[0];
			String number = singleEntryArray[i].split(",")[1];
			
				int hour = Integer.parseInt(duration.split(":")[0]);
				int min = Integer.parseInt(duration.split(":")[1]);
				int sec = Integer.parseInt(duration.split(":")[2]);
				
				int thisTotalSec = ((hour*3600) + (min*60) + sec);
				
				if( thisTotalSec < 300) {
					thisBill = thisBill + (((hour*3600)+(min*60)+sec)*3);
				} else {
					if(sec >= 01) {
						thisBill = thisBill + ((min +1) * 150) + ((hour * 60) * 150); 
					}
					else {
						thisBill = thisBill + ((min) * 150) + ((hour * 60) * 150);
					}
				}
				// add into hashmap
				
				if(!billMap.containsKey(number)) {
					billMap.put(number, thisBill);
				} else {
					int prevBill = billMap.get(number);
					billMap.put(number, prevBill + thisBill); 
				}
								
			}
		
		int maxValue = 0;
		for (int value : billMap.values()) {
		    if (value > maxValue) {
		        maxValue = value;
		    }
		}
		for (String key : billMap.keySet()) {
		    Integer value = billMap.get(key);
		    totalBill = totalBill + value;
		}
		
		totalBill = totalBill - maxValue;
		return totalBill;
	}

}