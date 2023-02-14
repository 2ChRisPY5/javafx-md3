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
	private static final String COLOR = "#49454E";
	private static final Properties CODE_POINTS = new Properties();

	static
	{
		Font.loadFont(MdIcon.class.getResourceAsStream("/icons.otf"), 20);
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
	 * Size = 20px; color = rgba(0, 0, 0, 0.87)
	 *
	 * @param icon the name of the icon
	 */
	public MdIcon(final String icon)
	{
		this(icon, 20);
	}

	/**
	 * Color = rgba(0, 0, 0, 0.87)
	 *
	 * @param icon the name of the icon
	 * @param size the icon size
	 */
	public MdIcon(final String icon, final int size)
	{
		this(icon, size, COLOR);
	}

	/**
	 * Constructor
	 *
	 * @param icon the name of the icon
	 * @param size the icon size
	 * @param fill the color of the icon (can be any valid CSS expression)
	 */
	public MdIcon(@NamedArg("icon") final String icon,
		@NamedArg(value = "size", defaultValue = "24") final int size,
		@NamedArg(value = "fill", defaultValue = COLOR) final String fill)
	{
		setStyle(new StringBuilder(128)
			.append("-fx-font-family:\"Material Icons Outlined Regular\";")
			.append("-fx-font-size:").append(size).append("px;")
			.append("-fx-fill:").append(fill).append(';')
			.toString());
		setText(fromName(icon));
	}

	/**
	 * @param name the arbitrary name
	 * @return the icon in its' string representation
	 */
	private static final String fromName(final String name)
	{
		return Character.toString(Integer.parseInt(CODE_POINTS.getProperty(name), 16));
	}
}
