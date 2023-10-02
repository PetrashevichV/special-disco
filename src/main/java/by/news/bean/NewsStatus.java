package by.news.bean;

public enum NewsStatus {
	APPROVED, PROPOSED, DELETED;

	public static NewsStatus fromValue(int value) {
		for (NewsStatus ns : NewsStatus.values()) {
			if (ns.ordinal() == value) {
				return ns;
			}
		}
		// TODO
		throw new RuntimeException("Unknown value" + value);
	}

}
