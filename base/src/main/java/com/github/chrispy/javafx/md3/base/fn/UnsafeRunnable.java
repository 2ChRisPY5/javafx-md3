package com.github.chrispy.javafx.md3.base.fn;

import java.util.Objects;

/**
 * {@link Runnable} wrapping any exception into a {@link RuntimeException}.
 *
 * @author 2ChRisPY5
 */
@FunctionalInterface
public interface UnsafeRunnable extends Runnable
{
	/**
	 * Same as {@link Runnable#run()} but with exception signiture.
	 */
	void runUnsafe() throws Exception;

	/**
	 * @see Runnable#run()
	 */
	@Override
	default void run()
	{
		try
		{
			runUnsafe();
		}
		catch(final Exception ex)
		{
			throw new RuntimeException(ex);
		}
	}

	/**
	 * Convenient wrapper to run given action unsafe.
	 *
	 * @param action the action to run
	 */
	static void run(final UnsafeRunnable action)
	{
		Objects.requireNonNull(action).run();
	}
}
