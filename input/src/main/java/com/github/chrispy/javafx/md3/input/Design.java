package com.github.chrispy.javafx.md3.input;

import java.util.function.Function;

import com.github.chrispy.javafx.md3.input.skins.FillSkin;
import com.github.chrispy.javafx.md3.input.skins.InputSkin;
import com.github.chrispy.javafx.md3.input.skins.OutlineSkin;

/**
 * Possible design types for input.
 *
 * @author 2ChRisPY5
 */
public enum Design
{
	FILL(input -> new FillSkin(input, input.textField(), input.label())),
	OUTLINE(input -> new OutlineSkin(input, input.textField(), input.label()));

	final Function<MdInput, InputSkin> skin;

	/**
	 * Constructor
	 *
	 * @param skin function to create the skin
	 */
	private Design(final Function<MdInput, InputSkin> skin)
	{
		this.skin = skin;
	}
}
