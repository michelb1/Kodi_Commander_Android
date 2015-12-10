package de.imichelb.kodicmd;

public interface Persistance {

	public void save(String key, String value);
	public String load(String key);
}
