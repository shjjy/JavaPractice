package ds.hw4;

public class PascalSet {

    private char[] pascalArr = new char[256];

    public static void main(String[] args) {
        PascalSet set1 = new PascalSet("abcdef");
        PascalSet set2 = new PascalSet("chfeechi");
        PascalSet set3 = new PascalSet("abcdef");
        System.out.println("Union " + set1.getUnion(set2));
        System.out.println("Intersection " + set1.getIntersection(set2));
        System.out.println("Diff A-B " + set1.getDifference(set2));
        System.out.println("Diff B-A " + set2.getDifference(set1));
        System.out.println("A Contains B "+ set1.contains(set2));
        System.out.println("A Contains B "+ set1.contains(set3));
        System.out.println("h in A " + set1.in('h'));
        System.out.println("h in A " + set2.in('h'));
    }

    private PascalSet(String str) {
        for(char c : str.toCharArray()) {
            if(c < 256){
                pascalArr[c] = 1;
            }
        }
    }

    private PascalSet(char[] pascalArr) {
        this.pascalArr = pascalArr;
    }

    private PascalSet getUnion(PascalSet set) {
        char[] tmpArr = new char[256];
        for (int i = 0; i < 256; i++) {
            if(pascalArr[i] == 1 || set.pascalArr[i] == 1) {
                tmpArr[i] = 1;
            }
        }
        return new PascalSet(tmpArr);
    }

    private PascalSet getIntersection(PascalSet set) {
        char[] tmpArr = new char[256];
        for (int i = 0; i < 256; i++) {
            if(pascalArr[i] == 1 && set.pascalArr[i] == 1) {
                tmpArr[i] = 1;
            }
        }
        return new PascalSet(tmpArr);
    }

    private PascalSet getDifference(PascalSet set) {
        char[] tmpArr = new char[256];
        for (int i = 0; i < 256; i++) {
            if(pascalArr[i] == 1 && set.pascalArr[i] == 0) {
                tmpArr[i] = 1;
            }
        }
        return new PascalSet(tmpArr);
    }

    private boolean equals(PascalSet set) {
        boolean equals = true;
        for(int i = 0; i < 256; i++) {
            if(pascalArr[i] != set.pascalArr[i]) {
                equals = false;
                break;
            }
        }
        return equals;
    }

    private boolean contains(PascalSet set) {
        PascalSet IntersectionSet = this.getIntersection(set);
        return IntersectionSet.equals(set);
    }

    private boolean in(char c) {
        return pascalArr[c] == 1;
    }

    public String toString(){
        StringBuilder res = new StringBuilder("{");
        for(int i = 0; i < 256; i++) {
            if(pascalArr[i] == 1) {
                res.append((char) i).append(", ");
            }
        }
        if(res.length() > 1) {
            res.setLength(res.length() - 2);
        }
        res.append("}");
        return res.toString();
    }
}
