package com.github.chrispy.javafx.md3.input.pwd;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.UnaryOperator;

import com.github.chrispy.javafx.md3.base.utils.StringUtils;

import javafx.scene.control.TextFormatter.Change;

/**
 * Special {@link javafx.scene.control.TextFormatter} filter for passwords masking the actual user input.
 *
 * @author 2ChRisPY5
 */
public class PasswordFilter implements UnaryOperator<Change>
{
	private char[] password;

	/**
	 * @see UnaryOperator#apply(Object)
	 */
	@Override
	public Change apply(final Change change)
	{
		// if final value would not exist
		if(StringUtils.nullOrBlank(change.getControlNewText()))
		{
			erase();
			return change;
		}

		// delete text part
		final var text = change.getText();
		if(StringUtils.nullOrBlank(text))
		{
			final var start = change.getRangeStart();
			final var end = change.getRangeEnd();
			final var result = new char[this.password.length - (end - start)];
			System.arraycopy(this.password, 0, result, 0, start);
			System.arraycopy(this.password, end, result, start, this.password.length - end);

			// reassign new password
			erase();
			this.password = result;
			return change;
		}

		// if text was added
		if(Objects.isNull(this.password))
		{
			this.password = text.toCharArray();
		}
		else
		{
			// concat the string
			final var length = text.length();
			final var result = Arrays.copyOf(this.password, this.password.length + length);
			System.arraycopy(text.toCharArray(), 0, result, this.password.length, length);

			erase();
			this.password = result;
		}

		// push *
		change.setText("\u2022".repeat(text.length()));
		return change;
	}

	/**
	 * @return the actual password
	 */
	public char[] getPassword()
	{
		return this.password;
	}

	/**
	 * Set the new internal password
	 *
	 * @param pwd the password or null
	 */
	public void setPassword(final char[] pwd)
	{
		erase();
		if(Objects.nonNull(pwd))
		{
			this.password = Arrays.copyOf(pwd, pwd.length);
		}
	}

	/**
	 * Erase the password
	 */
	private void erase()
	{
		if(Objects.isNull(this.password))
			return;

		Arrays.fill(this.password, '0');
		this.password = null;
	}
}
