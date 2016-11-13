package fr.upmc.dar.enums;

public enum EventVisibility {

	
	PUBLIC ,
	PRIVATE ,
	INTRA_UNI ;
	
	/**
	 * Retourne un {@link EventVisibility} en fonction d'une {@link String} équivalente.
	 * 
	 * @param visibility
	 * @return
	 */
	
	public static EventVisibility stringToEventVisibility(String visibility) {
		switch (visibility) {
		case "public":
			return EventVisibility.PUBLIC;
		case "privé":
			return EventVisibility.PRIVATE;
		case "université":
			return EventVisibility.INTRA_UNI;
		}
		return null;
	}
	
	public static String eventVisibilityToString(EventVisibility visibility) {
		switch (visibility) {
		case PUBLIC:
			return "public";
		case PRIVATE:
			return "privé";
		case INTRA_UNI:
			return "université";
		}
		return null;
	}
}
