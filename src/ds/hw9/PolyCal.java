package ds.hw9;

import ds.hw4.SelectionSort;

import java.util.Arrays;

public class PolyCal {
    public static void main(String[] args) {
        Poly[][] req = new Poly[][]{
                {getReq1A(), getReq1B()},
                {getReq2A(), getReq2B()},
                {getReq3A(), getReq3B()}
        };
        for (int i = 0; i < req.length; i++) {
            System.out.println("Req: " + i);
            doTest(req[i][0], req[i][1]);
        }
    }

    private static void doTest(Poly p1, Poly p2) {
        System.out.print(" P1 is :");
        p1.print();
        System.out.println();
        System.out.print(" P2 is :");
        p2.print();
        System.out.println();
        System.out.print(" P1 add P2 is :");
        Poly p1PlusP2 = add(p1, p2);
        p1PlusP2.print();
        System.out.println();
        Poly p1MultiplyP2 = multiply(p1, p2);
        System.out.print(" P1 multiply P2 is :");
        p1MultiplyP2.print();
        System.out.println();
    }

    private static Poly multiply(Poly p1, Poly p2) {
        if (p1 == null || p2 == null) {
            return null;
        }
        Poly result = new Poly(0, 0, null);
        for (Poly i = p1; i != null; i = i.next) {
            for (Poly j = p2; j != null; j = j.next) {
                int productConf = i.conf * j.conf;
                int productExp = i.exp + j.exp;
                result = new Poly(productConf, productExp, result);
            }
        }
        return normalize(result);
    }

    private static Poly add(Poly p1, Poly p2) {
        Poly ans = new Poly(0, 0, null);
        Poly p1Tmp = p1;
        Poly p2Tmp = p2;
        while (p1Tmp != null && p2Tmp != null) {
            if (p1Tmp.exp > p2Tmp.exp) {
                ans = new Poly(p1Tmp.conf, p1Tmp.exp, ans);
                p1Tmp = p1Tmp.next;
            } else if (p1Tmp.exp < p2Tmp.exp) {
                ans = new Poly(p2Tmp.conf, p2Tmp.exp, ans);
                p2Tmp = p2Tmp.next;
            } else {
                int sumConf = p1Tmp.conf + p2Tmp.conf;
                if (sumConf != 0) {
                    ans = new Poly(sumConf, p1Tmp.exp, ans);
                }
                p1Tmp = p1Tmp.next;
                p2Tmp = p2Tmp.next;
            }
        }
        return normalize(ans);
    }

    private static Poly getReq1A() {
        return normalize(new Poly(0, 0, null));
    }

    private static Poly getReq1B() {
        return normalize(new Poly(1, 2, new Poly(1, 1, new Poly(1, 0, null))));
    }

    private static Poly getReq2A() {
        return normalize(new Poly(1, 1, new Poly(3, 5, new Poly(1, 1, null))));
    }

    private static Poly getReq2B() {
        return normalize(new Poly(1, 2, new Poly(-2, 1, null)));
    }

    private static Poly getReq3A() {
        return normalize(new Poly(5, 1, new Poly(-7, 0, null)));
    }

    private static Poly getReq3B() {
        return normalize(new Poly(-5, 1, new Poly(7, 0, null)));
    }

    private static Poly normalize(Poly oriPloy) {
        Poly[] arr = toArray(oriPloy);
        int size = arr.length;
        SelectionSort.selectionSortGen(arr);
        Poly dummyHead = new Poly(0, 0, null);
        Poly current = dummyHead;
        for (int i = 0; i < size; i++) {
            int conf = arr[i].conf;
            int exp = arr[i].exp;
            while (i + 1 < size && exp == arr[i + 1].exp) {
                conf += arr[i + 1].conf;
                i++;
            }
            if (conf != 0) {
                current.next = new Poly(conf, exp, null);
                current = current.next;
            }
        }
        return dummyHead.next == null ? new Poly(0, 0, null) : dummyHead.next;
    }

    private static Poly[] toArray(Poly poly) {
        int size = 0;
        Poly temp = poly;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        temp = poly;
        Poly[] arr = new Poly[size];
        for (int i = 0; i < size && temp != null; i++) {
            arr[i] = temp;
            temp = temp.next;
        }
        return arr;
    }

}

class Poly implements Comparable<Poly> {
    int conf, exp;
    Poly next;

    public Poly(int conf, int exp, Poly next) {
        this.conf = conf;
        this.exp = exp;
        this.next = next;
    }

    void print() {
        printLoop(true);
    }

    void printLoop(boolean isFirst) {
        int tmpConf = isFirst?conf:Math.abs(conf);
        switch (exp) {
            case 0:
                System.out.print(tmpConf);
                break;
            case 1:
                System.out.print((tmpConf ==1?"":tmpConf) + "x");
                break;
            default:
                System.out.print((tmpConf ==1?"":tmpConf) + "x^" + exp);
                break;
        }
        if (next != null) {
            System.out.print(next.conf > 0 ? " + " : " - ");
            next.printLoop(false);
        }
    }

    @Override
    public int compareTo(Poly other) {
        return Integer.compare(other.exp, this.exp);
    }
}
