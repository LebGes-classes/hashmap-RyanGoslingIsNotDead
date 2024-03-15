import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHashMap {
    private HashMap<String, Integer> map;


    @BeforeEach
    void setUp(){
        map = new HashMap<>(4);
    }

    @Test
    void resizing(){
        map.put("KingOne", 1);
        map.put("KingTwo", 2);
        map.put("KingThree", 3);
        map.put("KingFour", 4);
        Assertions.assertEquals(8, map.getCapacity());
    }

    @Test
    void put(){
        map.put("KingOne", 1);
        Assertions.assertTrue(map.containsValue(1));
    }

    @Test
    void remove(){
        map.put("KingOne", 1);
        map.remove("KingOne");
        Assertions.assertFalse(map.containsValue(1));
    }

    @Test
    void clear(){
        map.put("KingOne", 1);
        map.put("KingTwo", 2);
        map.clear();
        Assertions.assertTrue(map.isEmpty());
    }

    @Test
    void getValue(){
        map.put("KingOne", 1);
        Assertions.assertEquals(1, map.get("KingOne"));
    }

    @Test
    void defaultCapacity(){
        HashMap<String, Integer> newMap = new HashMap<>();
        Assertions.assertEquals(16, newMap.getCapacity());
    }

    @Test
    void size(){
        map.put("KingOne", 1);
        Assertions.assertEquals(1, map.size());
    }
}
