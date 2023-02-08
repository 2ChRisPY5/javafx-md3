package com.github.chrispy.javafx.md3.base.utils;

import java.util.Objects;

/**
 * Helper functions for strings.
 *
 * @author 2ChRisPY5
 */
public final class StringUtils
{
	private StringUtils()
	{
	}

	/**
	 * Check if a string value is null or blank.
	 *
	 * @param value the string or null
	 * @return true or false
	 */
	public static boolean nullOrBlank(final String value)
	{
		return Objects.isNull(value) || value.isBlank();
	}
}
