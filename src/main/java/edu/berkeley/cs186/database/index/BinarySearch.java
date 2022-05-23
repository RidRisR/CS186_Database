package edu.berkeley.cs186.database.index;

import edu.berkeley.cs186.database.databox.DataBox;

import java.util.List;

public final class BinarySearch {

    private enum WhenEqual {Right,Left}

    private static <T extends Comparable> int[] binarySearch(List<T> keys, T key, WhenEqual whenEqual) {
        int len = keys.size();
        int left = 0;
        int right = len - 1;

        while(left <= right){
            int mid = left + ((right - left)>>1);

            switch (whenEqual){
                case Left:
                    if(keys.get(mid).compareTo(key) > 0)
                        right = mid - 1;
                    else
                        left = mid + 1;
                    break;
                case Right:
                    if(keys.get(mid).compareTo(key) >= 0)
                        right = mid - 1;
                    else
                        left = mid + 1;
                    break;
            }
        }
        return new int[]{right, left};
    }

    public static <T extends Comparable> Integer equalTo(List<T> keys, T key){
        int len = keys.size();
        int left = binarySearch(keys, key, WhenEqual.Right)[1];
        if(left<len && keys.get(left).equals(key))
            return left;
        else
            return null;
    }

    public static <T extends Comparable> Integer largerThan(List<T> keys, T key){
        int left = binarySearch(keys, key, WhenEqual.Right)[1];
        return left;
    }

    public static <T extends Comparable> Integer lessThan(List<DataBox> keys,DataBox key){
        int right = binarySearch(keys, key, WhenEqual.Left)[0];
        return right;
    }

}
