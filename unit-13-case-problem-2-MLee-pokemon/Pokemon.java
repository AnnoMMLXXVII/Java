
/**
 * Model class that will represent a Pokemon
 * Characteristics are: 
 * id, name, type1, type2, total, 
 * Health Power, Attack, Defense, Sp. Attack, 
 * Sp. Defense, Speed, Generation, Legendary status. 
 * 
 *
 */
public class Pokemon {

	private int id;
	private String name;
	private String primary;
	private String secondary;
	private int total;
	private int hp;
	private int attk;
	private int def;
	private int spAtk;
	private int spDef;
	private int speed;
	private GENERATION generation;
	private boolean isLegendary;

	/**
	 * @param id
	 * @param name
	 * @param primary
	 * @param secondary
	 * @param total
	 * @param hp
	 * @param attk
	 * @param def
	 * @param spAtk
	 * @param spDef
	 * @param speed
	 * @param generation
	 * @param isLegendary
	 */
	public Pokemon(int id, String name, String primary, String secondary, int total, int hp, int attk, int def,
			int spAtk, int spDef, int speed, int generation, String isLegendary) {
		
		
		
		this.id = id;
		this.name = name;
		
		
		
		
		
		
		
		
		setId(id);
		setName(name);
		setPrimary(primary);
		setSecondary(secondary);
		setTotal(total);
		setHp(hp);
		setAttk(attk);
		setDef(spDef);
		setSpAtk(spAtk);
		setSpDef(spDef);
		setSpeed(speed);
		setGeneration(generation);
		setLegendary(isLegendary);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the primary
	 */
	public String getPrimary() {
		return primary;
	}

	/**
	 * @return the secondary
	 */
	public String getSecondary() {
		return secondary;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @return the hp
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * @return the attk
	 */
	public int getAttk() {
		return attk;
	}

	/**
	 * @return the def
	 */
	public int getDef() {
		return def;
	}

	/**
	 * @return the spAtk
	 */
	public int getSpAtk() {
		return spAtk;
	}

	/**
	 * @return the spDef
	 */
	public int getSpDef() {
		return spDef;
	}

	/**
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @return the generation
	 */
	public GENERATION getGeneration() {
		return generation;
	}

	/**
	 * @return the isLegendary
	 */
	public boolean isLegendary() {
		return isLegendary;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param primary the primary to set
	 */
	public void setPrimary(String primary) {
		this.primary = primary;
	}

	/**
	 * @param secondary the secondary to set
	 */
	public void setSecondary(String secondary) {
		if (secondary == null) {
			this.secondary = "";
		} else {
			this.secondary = secondary;
		}
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

	/**
	 * @param hp the hp to set
	 */
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * @param attk the attk to set
	 */
	public void setAttk(int attk) {
		this.attk = attk;
	}

	/**
	 * @param def the def to set
	 */
	public void setDef(int def) {
		this.def = def;
	}

	/**
	 * @param spAtk the spAtk to set
	 */
	public void setSpAtk(int spAtk) {
		this.spAtk = spAtk;
	}

	/**
	 * @param spDef the spDef to set
	 */
	public void setSpDef(int spDef) {
		this.spDef = spDef;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * @param generation the generation to set
	 */
	public void setGeneration(int generation) {
		this.generation = convertGenNumberToText(generation);
	}

	/**
	 * @param isLegendary the isLegendary to set
	 */
	public void setLegendary(String isLegendary) {
		if (isLegendary.equalsIgnoreCase("FALSE")) {
			this.isLegendary = false;
		} else {
			this.isLegendary = true;
		}
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", name=" + name + ", primary=" + primary + ", secondary=" + secondary + ", total="
				+ total + ", hp=" + hp + ", attk=" + attk + ", def=" + def + ", spAtk=" + spAtk + ", spDef=" + spDef
				+ ", speed=" + speed + ", generation=" + generation.name() + ", isLegendary=" + isLegendary + "]";
	}

	/**
	 * Helper method that will take number Generation and convert to the GENERATION enum
	 * 
	 * @param number
	 * @return GENERATION
	 */
	private static GENERATION convertGenNumberToText(int number) {
		for (int i = 0; i < GENERATION.values().length; i++) {
			if (GENERATION.values()[i].getValue() == number) {
				return GENERATION.values()[i];
			}
		}
		return null;
	}

	/**
	 * Enum holding the generation count and the Regions in which the Pokemon reside. 
	 * Each Pokemon belongs to a generation and their respective Regions. 
	 * This GENERATION Enum shall represent this relationship
	 */
	private enum GENERATION {
		Kanto(1), Johto(2), Hoenn(3), Sinnoh(4), Unova(5), Kalos(6), Alola(7), Galar(8);
		
		private int generationNumber;

		GENERATION(int generationNumber) {
			this.generationNumber = generationNumber;
		}
		
		public int getValue() {
			return generationNumber;
		}

	}

}
