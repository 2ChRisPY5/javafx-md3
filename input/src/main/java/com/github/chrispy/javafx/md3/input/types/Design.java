package com.github.chrispy.javafx.md3.input.types;

import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Possible design types for input.
 *
 * @author 2ChRisPY5
 */
public enum Design
{
	FILL,
	OUTLINE;

	/**
	 * Apply the style class to given node.
	 *
	 * @param node the {@link Node}
	 */
	public void applyStyle(final Parent node)
	{
		node.getStylesheets().add(name().toLowerCase() + ".css");
	}
}
