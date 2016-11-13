package fr.upmc.dar.enums;

public enum EventVisibility {

	
	PUBLIC ,
	PRIVATE ,
	INTRA_UNI ;
	
	/**
	 * Retourne un {@link EventVisibility} en fonction d'une {@link String} �quivalente.
	 * 
	 * @param visibility
	 * @return
	 */
	
	public static EventVisibility stringToEventVisibility(String visibility) {
		switch (visibility) {
		case "public":
			return EventVisibility.PUBLIC;
		case "priv�":
			return EventVisibility.PRIVATE;
		case "universit�":
			return EventVisibility.INTRA_UNI;
		}
		return null;
	}
	
	public static String eventVisibilityToString(EventVisibility visibility) {
		switch (visibility) {
		case PUBLIC:
			return "public";
		case PRIVATE:
			return "priv�";
		case INTRA_UNI:
			return "universit�";
		}
		return null;
	}
}
