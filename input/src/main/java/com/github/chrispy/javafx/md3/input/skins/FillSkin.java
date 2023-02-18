package com.github.chrispy.javafx.md3.input.skins;

import com.github.chrispy.javafx.md3.base.MdPseudoClass;
import com.github.chrispy.javafx.md3.input.MdInput;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The skin for Material Design Filled appearance.
 *
 * @author 2ChRisPY5
 */
public class FillSkin extends InputSkin
{
	private static final String CSS = "fill.css";

	private final ChangeListener<Boolean> onFocus = (obs, old, nev) -> {
		MdPseudoClass.FOCUSED.state(this.input, nev);
		MdPseudoClass.FOCUSED.state(this.label, nev);
	};

	/**
	 * @see InputSkin#InputSkin(MdInput, TextField, Label)
	 */
	public FillSkin(final MdInput input, final TextField textField, final Label label)
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
		this.textField.focusedProperty().addListener(this.onFocus);
	}

	/**
	 * @see InputSkin#apply()
	 */
	@Override
	protected void disposeInternal()
	{
		this.input.getStylesheets().removeAll(CSS);
		this.textField.focusedProperty().removeListener(this.onFocus);
	}
}
