package com.github.chrispy.javafx.md3.input.formatter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import com.github.chrispy.javafx.md3.base.utils.StringUtils;

import javafx.scene.control.TextFormatter.Change;

/**
 * Only allows numbers as input characters.
 *
 * @author 2ChRisPY5
 */
public enum NumberFilter implements UnaryOperator<Change>
{
	INSTANCE;

	private static final Pattern NO_DIGITS = Pattern.compile("[^\\d,]+");

	/**
	 * @see UnaryOperator#apply(Object)
	 */
	@Override
	public Change apply(final Change change)
	{
		if(!StringUtils.nullOrBlank(change.getText()))
		{
			change.setText(NO_DIGITS.matcher(change.getText()).replaceAll(""));
		}
		return change;
	}
}
