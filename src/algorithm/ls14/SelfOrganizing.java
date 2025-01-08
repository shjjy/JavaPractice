package algorithm.ls14;

import java.util.*;

public class SelfOrganizing {

    public static void main(String[] args) {
        String[] initialList = {"A", "B", "C", "D", "E"};
        String[] searchList = {"E", "D", "E", "A", "E", "D"};

        OnlineSelfOrganizingList onlineList = new OnlineSelfOrganizingList();
        onlineList.initialize(initialList);
        int onlineTotalCost = processSearch(onlineList, searchList, "Online");

        OfflineSelfOrganizingList offlineList = new OfflineSelfOrganizingList();
        offlineList.initialize(initialList);
        offlineList.recordAccess(searchList);
        offlineList.reorganizeList();
        int offlineTotalCost = processSearch(offlineList, searchList, "Offline");

        double costRatio = (double) onlineTotalCost / offlineTotalCost;
        System.out.printf("\nOnline 與 Offline total cost 差 %.2f 倍。\n", costRatio);
    }

    private static int processSearch(SelfOrganizingList list, String[] searchList, String label) {
        System.out.println("\n=== " + label + " Self-Organizing ===");
        System.out.println("init list：" + list.getList());

        int totalCost = 0;
        for (String element : searchList) {
            int cost = list.access(element);
            System.out.println("access " + element + "：cost " + cost);
            totalCost += cost;
        }

        System.out.println("final list：" + list.getList());
        System.out.println(label + " total cost：" + totalCost);

        return totalCost;
    }
}

interface SelfOrganizingList {
    int access(String element);
    List<String> getList();
}

class OnlineSelfOrganizingList implements SelfOrganizingList {
    private final LinkedList<String> list;

    public OnlineSelfOrganizingList() {
        list = new LinkedList<>();
    }

    public void initialize(String[] elements) {
        list.clear();
        list.addAll(Arrays.asList(elements));
    }

    public int access(String element) {
        int searchSteps = 0;

        for (String item : list) {
            searchSteps++;
            if (item.equals(element)) {
                break;
            }
        }

        list.remove(element);
        list.addFirst(element);

        return searchSteps;
    }

    public List<String> getList() {
        return new ArrayList<>(list);
    }

}

class OfflineSelfOrganizingList implements SelfOrganizingList {
    private final LinkedHashMap<String, Integer> frequencyMap;
    private final LinkedList<String> list;

    public OfflineSelfOrganizingList() {
        frequencyMap = new LinkedHashMap<>();
        list = new LinkedList<>();
    }

    public void initialize(String[] elements) {
        frequencyMap.clear();
        list.clear();

        for (String element : elements) {
            frequencyMap.put(element, 0);
            list.add(element);
        }
    }

    public void recordAccess(String[] searchList) {
        for (String element : searchList) {
            frequencyMap.put(element, frequencyMap.getOrDefault(element, 0) + 1);
        }
    }

    public void reorganizeList() {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());
        entries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue())); // 按次數降序排序
        list.clear();
        for (Map.Entry<String, Integer> entry : entries) {
            list.add(entry.getKey());
        }
    }

    public int access(String element) {
        int searchSteps = 0;

        for (String item : list) {
            searchSteps++;
            if (item.equals(element)) {
                break;
            }
        }

        return searchSteps;
    }

    public List<String> getList() {
        return new ArrayList<>(list);
    }

}
