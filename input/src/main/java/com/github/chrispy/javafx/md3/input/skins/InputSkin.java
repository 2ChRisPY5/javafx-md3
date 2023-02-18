package com.github.chrispy.javafx.md3.input.skins;

import java.util.Objects;

import com.github.chrispy.javafx.md3.input.MdInput;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

/**
 * Specialized {@link Skin} interface for {@link MdInput}.
 *
 * @author 2ChRisPY5
 */
public abstract class InputSkin implements Skin<MdInput>
{
	protected MdInput input;
	protected Label label;
	protected TextField textField;

	/**
	 * Constructor
	 *
	 * @param input the {@link MdInput}
	 * @param textField the internal {@link TextField}
	 * @param label the internal {@link Label}
	 */
	protected InputSkin(final MdInput input, final TextField textField, final Label label)
	{
		this.input = input;
		this.textField = textField;
		this.label = label;
	}

	/**
	 * Apply the skin.
	 */
	public abstract void apply();

	/**
	 * Dispose the style internally.
	 */
	protected abstract void disposeInternal();

	/**
	 * @see Skin#dispose()
	 */
	@Override
	public final void dispose()
	{
		if(Objects.nonNull(this.input))
		{
			disposeInternal();
		}
		this.input = null;
		this.textField = null;
		this.label = null;
	}

	/**
	 * @see Skin#getSkinnable()
	 */
	@Override
	public final MdInput getSkinnable()
	{
		return this.input;
	}

	/**
	 * @see Skin#getNode()
	 */
	@Override
	public final Node getNode()
	{
		return this.input;
	}
}
