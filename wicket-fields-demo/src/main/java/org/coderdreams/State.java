package org.coderdreams;

import java.util.List;

public enum State {
	ALABAMA("Alabama", "AL"),
	ALASKA("Alaska", "AK"),
	ARIZONA("Arizona", "AZ"),
	ARKANSAS("Arkansas", "AR"),
	CALIFORNIA("California", "CA"),
	COLORADO("Colorado", "CO"),
	CONNECTICUT("Connecticut", "CT"),
	DELAWARE("Delaware", "DE"),
	DISTRICT_OF_COLUMBIA("District of Columbia", "DC"),
	FLORIDA("Florida", "FL"),
	GEORGIA("Georgia", "GA"),
	HAWAII("Hawaii", "HI"),
	IDAHO("Idaho", "ID"),
	ILLINOIS("Illinois", "IL"),
	INDIANA("Indiana", "IN"),
	IOWA("Iowa", "IA"),
	KANSAS("Kansas", "KS"),
	KENTUCKY("Kentucky", "KY"),
	LOUISIANA("Louisiana", "LA"),
	MAINE("Maine", "ME"),
	MARYLAND("Maryland", "MD"),
	MASSACHUSETTS("Massachusetts", "MA"),
	MICHIGAN("Michigan", "MI"),
	MINNESOTA("Minnesota", "MN"),
	MISSISSIPPI("Mississippi", "MS"),
	MISSOURI("Missouri", "MO"),
	MONTANA("Montana", "MT"),
	NEBRASKA("Nebraska", "NE"),
	NEVADA("Nevada", "NV"),
	NEW_HAMPSHIRE("New Hampshire", "NH"),
	NEW_JERSEY("New Jersey", "NJ"),
	NEW_MEXICO("New Mexico", "NM"),
	NEW_YORK("New York", "NY"),
	NORTH_CAROLINA("North Carolina", "NC"),
	NORTH_DAKOTA("North Dakota", "ND"),
	OHIO("Ohio", "OH"),
	OKLAHOMA("Oklahoma", "OK"),
	OREGON("Oregon", "OR"),
	PENNSYLVANIA("Pennsylvania", "PA"),
	PUERTO_RICO("Puerto Rico", "PR"),
	RHODE_ISLAND("Rhode Island", "RI"),
	SOUTH_CAROLINA("South Carolina", "SC"),
	SOUTH_DAKOTA("South Dakota", "SD"),
	TENNESSEE("Tennessee", "TN"),
	TEXAS("Texas", "TX"),
	UTAH("Utah", "UT"),
	VERMONT("Vermont", "VT"),
	VIRGIN_ISLANDS("Virgin Islands", "VI"),
	VIRGINIA("Virginia", "VA"),
	WASHINGTON("Washington", "WA"),
	WEST_VIRGINIA("West Virginia", "WV"),
	WISCONSIN("Wisconsin", "WI"),
	WYOMING("Wyoming", "WY"),
	AMERICAN_SAMOA("American Samoa", "AS"),
	FEDERATED_STATES_OF_MICRONESIA("Federated States of Micronesia", "FM"),
	GUAM("Guam", "GU"),
	MARSHALL_ISLANDS("Marshall Islands", "MH"),
	NORTHERN_MARIANA_ISLANDS("Northern Mariana Islands", "MP"),
	PALAU("Palau", "PW"),
	
	
	//Provinces
	ALBERTA("Alberta", "AB"),
	BRITISH_COLUMBIA("British Columbia", "BC"),
	MANITOBA("Manitoba", "MB"),
	NEW_BRUNSWICK("New Brunswick", "NB"),
	NEWFOUNDLAND_AND_LABRADOR("Newfoundland and Labrador", "NL"),
	NOVA_SCOTIA("Nova Scotia", "NS"),
	ONTARIO("Ontario", "ON"),
	PRINCE_EDWARD_ISLAND("Prince Edward Island", "PE"),
	QUEBEC("Québec", "QC"),
	SASKATCHEWAN("Saskatchewan", "SK"),
	
	//Territories
	NORTHWEST_TERRITORIES("Northwest Territories", "NT"),
	NUNAVUT("Nunavut", "NU"),
	YUKON("Yukon", "YT")	
	
	;
	
	private final String name;
	private final String abbreviation;

    public static final List<State> VALUES = List.of(State.values());

	State(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }
    
    public String getName() { return name; }
    public String getAbbreviation() { return abbreviation; }
}