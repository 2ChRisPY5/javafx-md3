package com.github.chrispy.javafx.md3.icon;

import java.io.IOException;
import java.util.Properties;

import javafx.beans.NamedArg;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Showing a material design icon
 *
 * @author 2ChRisPY5
 */
public class MdIcon extends Text
{
	private static final Properties CODE_POINTS = new Properties();
	private static final Font FONT = Font.loadFont(MdIcon.class.getResourceAsStream("/icons.otf"), 20);

	static
	{
		try
		{
			CODE_POINTS.load(MdIcon.class.getResourceAsStream("/codepoints.properties"));
		}
		catch(final IOException ex)
		{
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Size = 24px; color = rgba(0, 0, 0, 0.87)
	 *
	 * @param icon the name of the icon
	 */
	public MdIcon(final String icon)
	{
		this(icon, 24);
	}

	/**
	 * Color = rgba(0, 0, 0, 0.87)
	 *
	 * @param icon the name of the icon
	 * @param size the icon size
	 */
	public MdIcon(final String icon, final int size)
	{
		this(icon, size, "rgba(0,0,0,0.87)");
	}

	/**
	 * Constructor
	 *
	 * @param icon the name of the icon
	 * @param size the icon size
	 * @param fill the color of the icon (can be any valid CSS expression)
	 */
	public MdIcon(@NamedArg("icon") final String icon,
		@NamedArg(value = "size", defaultValue = "20") final int size,
		@NamedArg(value = "fill", defaultValue = "") final String fill)
	{
		setFont(FONT);
		setStyle(new StringBuilder(64)
			.append("-fx-font-size:").append(size).append("px;")
			.append("-fx-fill:").append(fill).append(';')
			.toString());
		setText(fromName(icon));
	}

	/**
	 * Gets the correct codepoint for arbitrary name and converts it actual icon.
	 *
	 * @param name the arbitrary name
	 * @return the icon in its' string representation
	 */
	private static final String fromName(final String name)
	{
		return Character.toString(Integer.parseInt(CODE_POINTS.getProperty(name), 16));
	}
}
