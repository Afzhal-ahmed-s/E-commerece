package com.atishae;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import java.util.TreeMap;
import java.util.TreeSet;

public class Atishae {

	public static void main(String[] args) {

//		Create ranges for input data
//		Input
//		Input 1: The extent of each interval.
//		Input 2: A single string numbers which is a list of n space separated positive integers.
//		Output
//		One line for each interval from the first non-empty interval to the last non-empty interval. For each line, write the limits of the interval, the count and sorted list of integers in the interval, as in the example below. Take note that the low limit of an interval is inclusive, while its high limit is exclusive. Also, both limits of all intervals are always multiples of extent.
//		Example
//
//		Input
//		extent= 10
//		numbers= "28 60 30 35"
//		Output
//		20-30 : 1 (28)
//		30-40 : 2 (30, 35)
//		40-50 : 0
//		50-60 : 0
//		60-70 : 1 (60)

		Map<String, TreeSet<Integer>> map = new TreeMap();
		
		Integer extent = 10;
		String s = "28 60 30 35 45 09 3 56";
		
		String[] arr = s.trim().split(" ");
		
		int len = arr.length;
		

		for(int a=0; a<len; a++) {
			Integer e= Integer.parseInt(arr[a]);
			
			Integer base = e - e%extent;
			Integer high = base + extent;
			String key = base+ "-"+ high;
			
			if(map.containsKey(key)) {
				TreeSet<Integer> ts = map.get(key);
				ts.add(e);

			}
			else {
				TreeSet<Integer> ts = new TreeSet<>();
				ts.add(e);
				map.put(key, ts);
			}

		}
		
		Set<Map.Entry<String, TreeSet<Integer> >> set= map.entrySet();
		
		for(Map.Entry<String, TreeSet<Integer> > e: set) {
			TreeSet<Integer> eval = e.getValue();
			StringBuilder sb = new StringBuilder();
			sb.append('(');
			for(Integer tse : eval) {
				sb.append(tse);
				sb.append(',');
			}
			sb.append(')');
			String str = new String(sb);
			System.out.println(e.getKey()+ " : "+ eval.size()+" "+str);
		}
		
		System.out.println();
		System.out.println("End");
		
	}

}
