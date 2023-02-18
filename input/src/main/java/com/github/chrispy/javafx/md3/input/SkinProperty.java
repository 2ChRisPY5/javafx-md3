package com.github.chrispy.javafx.md3.input;

import java.util.Objects;

import com.github.chrispy.javafx.md3.input.skins.InputSkin;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Skin;

/**
 * The object property implementation for {@link MdInput} skin.
 *
 * @author 2ChRisPY5
 */
public class SkinProperty extends SimpleObjectProperty<Skin<?>>
{
	/**
	 * Constructor
	 *
	 * @param skin the {@link Skin}
	 * @param name the name of the skin
	 */
	SkinProperty(final InputSkin skin, final String name)
	{
		super(skin.getSkinnable(), name, skin);
		skin.apply();
	}

	/**
	 * @see SimpleObjectProperty#set(Object)
	 */
	@Override
	public void set(final Skin<?> newValue)
	{
		final InputSkin newSkin;
		if(Objects.isNull(newValue))
		{
			newSkin = Design.FILL.skin.apply((MdInput) get().getSkinnable());
		}
		else if(newValue instanceof InputSkin)
		{
			newSkin = (InputSkin) newValue;
		}
		else
		{
			throw new IllegalArgumentException(
				"Skin is not an instance of InputSkin. MdInput skin can only be changed using its' designProperty().");
		}

		// save old skin
		final var oldSkin = get();

		// try setting new
		super.set(newSkin);

		// dispose old and apply new
		oldSkin.dispose();
		newSkin.apply();
	}
}
