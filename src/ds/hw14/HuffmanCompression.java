package ds.hw14;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class HuffmanCompression {

    @SuppressWarnings("CallToPrintStackTrace")
    public static void main(String[] args) {
        String inputFile = "res/example.txt";
        String compressedFile = "res/example_compressed.huff";
        String decompressedFile = "res/example_decompressed.txt";

        try {
            compress(inputFile, compressedFile);
            decompress(compressedFile, decompressedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compress(String inputFile, String compressedFile) throws IOException {
        // 讀檔
        File file = new File(inputFile);
        byte[] fileBytes = Files.readAllBytes(file.toPath());
        //計算每個字的頻率，每次+1，不存在就給0+1
        Map<Byte, Integer> frequencyMap = new HashMap<>();
        for (byte b : fileBytes) {
            frequencyMap.put(b, frequencyMap.getOrDefault(b, 0) + 1);
        }

        // 用 Priority Queue 實現 Huffman Tree 每次取最小
        Node root = getMinNode(frequencyMap);

        // 產生 Huffman Encoding Table
        Map<Byte, String> huffmanTable = new HashMap<>();
        buildEncodingTable(root, "", huffmanTable);

        // 編碼
        StringBuilder encodedData = new StringBuilder();
        for (byte b : fileBytes) {
            encodedData.append(huffmanTable.get(b));
        }

        // 寫入資料
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(compressedFile))) {
            // frequency table size
            dos.writeInt(frequencyMap.size());
            //  frequency table
            for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
                dos.writeByte(entry.getKey());
                dos.writeInt(entry.getValue());
            }
            // encoded data as bits
            BitSet bitSet = new BitSet(encodedData.length());
            for (int i = 0; i < encodedData.length(); i++) {
                if (encodedData.charAt(i) == '1') {
                    bitSet.set(i);
                }
            }
            byte[] encodedBytes = bitSet.toByteArray();
            dos.writeInt(encodedData.length()); // bit length for decoding
            dos.write(encodedBytes);
        }

        // 印出檔頭
        int originalSize = fileBytes.length;
        int compressedSize = (int) new File(compressedFile).length();
        double compressionRatio = (double) originalSize / compressedSize;

        System.out.println("原始檔案大小: " + originalSize + " bytes");
        System.out.println("壓縮後檔案大小: " + compressedSize + " bytes");
        System.out.println("壓縮率: " + compressionRatio);
        System.out.println("編碼表:");
        for (Map.Entry<Byte, String> entry : huffmanTable.entrySet()) {
            System.out.println((char) entry.getKey().byteValue() + ": " + entry.getValue());
        }
    }

    public static void decompress(String compressedFile, String decompressedFile) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(compressedFile))) {
            // 取得 frequency table
            int tableSize = dis.readInt();
            Map<Byte, Integer> frequencyMap = new HashMap<>();
            for (int i = 0; i < tableSize; i++) {
                byte data = dis.readByte();
                int frequency = dis.readInt();
                frequencyMap.put(data, frequency);
            }

            // Rebuild Huffman Tree
            Node root = getMinNode(frequencyMap);

            // Read encoded data
            int bitLength = dis.readInt();
            byte[] encodedBytes = dis.readAllBytes();
            BitSet bitSet = BitSet.valueOf(encodedBytes);

            // Decode data
            ByteArrayOutputStream decodedData = new ByteArrayOutputStream();
            Node current = root;
            for (int i = 0; i < bitLength; i++) {
                current = bitSet.get(i) ? current.right : current.left;
                if (current.isLeaf()) {
                    decodedData.write(current.data);
                    current = root;
                }
            }

            // Write decoded data to file
            try (FileOutputStream fos = new FileOutputStream(decompressedFile)) {
                fos.write(decodedData.toByteArray());
            }
        }
    }

    @SuppressWarnings("DataFlowIssue")
    private static Node getMinNode(Map<Byte, Integer> frequencyMap) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Byte, Integer> entry : frequencyMap.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left.frequency + right.frequency, left, right));
        }
        return pq.poll();
    }

    private static void buildEncodingTable(Node node, String code, Map<Byte, String> huffmanTable) {
        if (node.isLeaf()) {
            huffmanTable.put(node.data, code);
        } else {
            buildEncodingTable(node.left, code + "0", huffmanTable);
            buildEncodingTable(node.right, code + "1", huffmanTable);
        }
    }


}
class Node implements Comparable<Node> {
    byte data;
    int frequency;
    Node left, right;

    Node(byte data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    Node(int frequency, Node left, Node right) {
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node other) {
        if (this.frequency == other.frequency) {
            return Byte.compare(this.data, other.data);
        }
        return Integer.compare(this.frequency, other.frequency);
    }

    boolean isLeaf() {
        return left == null && right == null;
    }
}