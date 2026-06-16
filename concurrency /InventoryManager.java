package concurrency;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Integer> stock = new HashMap<>();

    public InventoryManager() {
        stock.put("item_001", 1);
    }

    public boolean purchase(String itemId, String userId) {
        int available = stock.getOrDefault(itemId, 0);
        if (available > 0) {
            stock.put(itemId, available - 1);
            return true;
        }
        return false;
    }
}