package com.github.chrispy.javafx.md3.base;

import javafx.css.PseudoClass;
import javafx.scene.Node;

/**
 * Enum specifying commonly used CSS pseudo classes.
 *
 * @author 2ChRisPY5
 */
public enum MdPseudoClass
{
	NOT_EMPTY,
	FOCUS;

	private final PseudoClass pseudo;

	/**
	 * Constructor
	 */
	private MdPseudoClass()
	{
		this.pseudo = PseudoClass.getPseudoClass(name().toLowerCase().replace('_', '-'));
	}

	/**
	 * Change the state of this pseudo class on given node.
	 *
	 * @param node the {@link Node} to change the state of this pseudo class
	 * @param active if state is active or not
	 */
	public void state(final Node node, final boolean active)
	{
		if(active)
		{
			apply(node);
		}
		else
		{
			remove(node);
		}
	}

	/**
	 * Apply this pseudo class to given node.
	 *
	 * @param node the {@link Node} to apply to
	 */
	public void apply(final Node node)
	{
		node.pseudoClassStateChanged(this.pseudo, true);
	}

	/**
	 * Remove this pseudo class from given node.
	 *
	 * @param node the {@link Node} to remove from
	 */
	public void remove(final Node node)
	{
		node.pseudoClassStateChanged(this.pseudo, false);
	}
}
