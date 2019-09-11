package Factory;

import java.util.function.Function;

import GenericHashMap.GenericHashMap;

/**
 * @author Avishai
 *
 * to create a factory class, that can get a method to create a new instance of a type,
 * and can run the function whenever it requestwd.
 * @param <K> - key - the key of the type
 * @param <T> - type
 * @param <A> - the argument to the function
 */
public class Factory<K, T, A> {
	private GenericHashMap<K, Function<A, ? extends T>> hash;
	
	/**
	 * constructor
	 */
	public Factory() {
		this.hash = new GenericHashMap<>(10);
	}

	/**
	 * to create the new instance.
	 * @param key - the key of the type
	 * @param arg - argument to the function
	 * @return a new instance of the type
	 */
	public T create(K key, A arg) {
		return this.hash.get(key).apply(arg);
	}
	
	/**
	 * @param key - the name of the type
	 * @param func - the function to store in the hash map (return T)
	 * @return the function
	 */
	public Function<A, ? extends T> add(K key, Function<A, ? extends T> func) {
		return this.hash.put(key, func);
	}
}