public class SymbolTable {
    private static final int INIT_CAPACITY = 7;

    /* Number of key-value pairs in the symbol table */
    private int N;
    /* Size of linear probing table */
    private int M;
    /* The keys */
    private String[] keys;
    /* The values */
    private Character[] vals;

    /**
     * Create an empty hash table - use 7 as default size
     */
    public SymbolTable() {
	this(INIT_CAPACITY);
    }

    /**
     * Create linear probing hash table of given capacity
     */
    public SymbolTable(int capacity) {
	N = 0;
	M = capacity;
	keys = new String[M];
	vals = new Character[M];
    }

    /**
     * Return the number of key-value pairs in the symbol table
     */
    public int size() {
	return N;
    }

    /**
     * Is the symbol table empty?
     */
    public boolean isEmpty() {
	return size() == 0;
    }

    /**
     * Does a key-value pair with the given key exist in the symbol table?
     */
    public boolean contains(String key) {
	return get(key) != null;
    }

    /**
     * Hash function for keys - returns value between 0 and M-1
     */
    public int hash(String key) {
	int i;
	int v = 0;

	for (i = 0; i < key.length(); i++) {
	    v += key.charAt(i);
	}
	return v % M;
    }

    /**
     * Insert the key-value pair into the symbol table
     */
    public void put(String key, Character val) {
	int hKey = hash(key);
	if (contains(key)) {
	    // Delete the key if the value is null
	    if (val == null) {
		delete(key);
		return;
	    }
	    // Replace the value at the specified key
	    for (int i = 0; i < M; i++) {
		if(keys[(hKey + i) % M].equals(key)){
		    vals[(hKey + i) % M] = val;
		    return;
		}
	    }
	}
	// Add a new key- value pair to the table if no such key is found
	for (int i = 0; i < M; i++) {
	    if (keys[(hKey + i) % M] == null) {
		keys[(hKey + i) % M] = key;
		vals[(hKey + i) % M] = val;
		N++;
		return;
	    }
	}
    }

    /**
     * Return the value associated with the given key, null if no such value
     */
    public Character get(String key) {
	int hKey = hash(key);
	for (int i = 0; i < M; i++) {
	    if (keys[(hKey + i) % M] != null && keys[(hKey + i) % M].equals(key)) {
		return vals[(hKey + i) % M];
	    }
	}
	return null;
    }

    /**
     * Delete the key (and associated value) from the symbol table
     */
    public void delete(String key) {
	if (!contains(key)) return;

	int hKey = hash(key);

	// Iterate until the key is found
	while (!key.equals(keys[hKey])) {
	    hKey = (hKey + 1) % M;
	}

	// Delete the key and value
	keys[hKey] = null;
	vals[hKey] = null;

	hKey = (hKey + 1) % M;
	// Delete and reinsert keys/values nearby
	while (keys[hKey] != null) {
	    String keyTemp = keys[hKey];
	    Character valTemp = vals[hKey];
	    keys[hKey] = null;
	    vals[hKey] = null;
	    N--;
	    put(keyTemp, valTemp);
	    hKey = (hKey + 1) % M;
	}
	N--;
    }

    /**
     * Print the contents of the symbol table
     */
    public void dump() {
	String str = "";

	for (int i = 0; i < M; i++) {
	    str = str + i + ". " + vals[i];
	    if (keys[i] != null) {
		str = str + " " + keys[i] + " (";
		str = str + hash(keys[i]) + ")";
	    } else {
		str = str + " -";
	    }
	    System.out.println(str);
	    str = "";
	}
    }
}
