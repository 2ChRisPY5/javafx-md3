package com.github.chrispy.javafx.md3.input.skins;

import com.github.chrispy.javafx.md3.input.MdInput;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The skin for Material Design Filled appearance.
 *
 * @author 2ChRisPY5
 */
public class OutlineSkin extends InputSkin
{
	private static final String CSS = "outline.css";

	/**
	 * @see InputSkin#InputSkin(MdInput, TextField, Label)
	 */
	public OutlineSkin(final MdInput input, final TextField textField, final Label label)
	{
		super(input, textField, label);
	}

	/**
	 * @see InputSkin#apply()
	 */
	@Override
	public void apply()
	{
		this.input.getStylesheets().add(CSS);
	}

	/**
	 * @see InputSkin#apply()
	 */
	@Override
	protected void disposeInternal()
	{
		this.input.getStylesheets().removeAll(CSS);
	}
}
