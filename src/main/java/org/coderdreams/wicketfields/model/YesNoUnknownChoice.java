package org.coderdreams.wicketfields.model;

public enum YesNoUnknownChoice {
	YES(1, "Yes"),
	NO(2, "No"),
	UNKNOWN(3, "Unknown");

    private final int id;
    private final String description;

    YesNoUnknownChoice(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getDescription() { return description; }
    public int getId() { return id; }
}