package org.cotato.tlinkserver.global.util;

import java.util.Random;

public class RandomUtil {

	private static final Random RANDOM = new Random();

	public static String generateRandomCode(final char leftBound, final char rightBound, final int limit) {
		return RANDOM.ints(leftBound, rightBound+1)
			.filter(c -> Character.isDigit(c) || Character.isAlphabetic(c))
			.limit(limit)
			.collect(
				StringBuilder::new,
				StringBuilder::appendCodePoint,
				StringBuilder::append
			)
			.toString();
	}

}
