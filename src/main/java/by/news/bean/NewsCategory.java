package by.news.bean;

public enum NewsCategory {
	POLITICAL_NEWS("Political News"), ENTERTAINMENT_NEWS("Entertainment News"), BUSINESS_NEWS("Business News"),
	SPORTS_NEWS("Sport News");

	private String name;

	private NewsCategory(String name) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public static NewsCategory fromValue(int value) {
		for (NewsCategory nc : NewsCategory.values()) {
			if (nc.ordinal() == value) {
				return nc;
			}
		}
		// TODO
		throw new RuntimeException("Unknown value" + value);
	}

}
