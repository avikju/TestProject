import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 */

/**
 * @author Avik
 *
 */
public class SortHashMapByValues {

	public static <K, V> Map<K, V> getSortedHashMapByValues(Map<K, ? extends Comparable<V>> map) {

		List<Map.Entry<K, ? extends Comparable<V>>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, ? extends Comparable<V>>>() {

			@Override
			public int compare(Entry<K, ? extends Comparable<V>> o1, Entry<K, ? extends Comparable<V>> o2) {
				return o1.getValue().compareTo((V) o2.getValue());
			}
		});

		Map<K, V> sortedMap = new LinkedHashMap<>();
		for (Map.Entry<K, ? extends Comparable<V>> e : list) {
			sortedMap.put(e.getKey(), (V) e.getValue());
		}
		return sortedMap;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("d", 89);
		map.put("x", 10);
		map.put("y", 3);
		map.put("m", 7);

		Map<String, Integer> sortedMap = getSortedHashMapByValues(map);
		sortedMap.forEach((k, v) -> {
			System.out.println("Key=" + k + " and Value=" + v);
		});

	}

}
