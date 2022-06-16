package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MaxDataUtil {

    public static List<Integer> getMaxData(SinglyLinkedList<Integer> list, int n) {
        Validation.checkNullListData(list);
        Validation.checkMaxNumberIsZero(n);
        Validation.checkMaxNumberWithinBoundaries(list, n);

        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Iterator<Integer> iterator = list.iterator();
            int currentData = iterator.next();
            int maxData = currentData;
            while (iterator.hasNext()) {
                int nextData = iterator.next();
                maxData = Math.max(currentData, nextData);
                currentData = maxData;
            }
            list.removeObject(maxData);
            resultList.add(0, maxData);
        }
        return resultList;
    }
}
