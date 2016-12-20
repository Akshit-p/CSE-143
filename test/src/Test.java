import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[] b = {5, 10, 15, 20, 21, 22, 4, 1};
        Tree a = new Tree(b);
        System.out.println(a.nodesAtLevel(2,5));

    }

    public static void mystery(Map<String, String> m) {
        Set<String> s = new TreeSet<>();
        for (String key : m.keySet()) {
            if (!m.get(key).equals(key)) {
                s.add(m.get(key));
            } else {
                s.remove(m.get(key));
            }
        }
        System.out.println(s);
    }
}