public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("KING", 100);
        System.out.println(map.get("KING"));
        System.out.println(map.containsKey("KING"));
        System.out.println(map.containsValue(100));
        System.out.println(map.size());
        map.remove("KING");
        System.out.println(map.containsKey("KING"));
    }
}