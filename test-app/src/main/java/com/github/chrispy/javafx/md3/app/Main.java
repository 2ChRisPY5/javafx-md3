package com.github.chrispy.javafx.md3.app;

import com.github.chrispy.javafx.md3.icon.MdIcon;
import com.github.chrispy.javafx.md3.input.MdInput;
import com.github.chrispy.javafx.md3.input.types.Input;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX application
 *
 * @author 2ChRisPY5
 */
public class Main extends Application
{
	public static void main(final String... args)
	{
		Application.launch(Main.class, args);
	}

	/**
	 * @see Application#start(Stage)
	 */
	@Override
	public void start(final Stage primaryStage) throws Exception
	{
		final var box = new VBox(8D,
			new MdInput("Plain text"),
			new MdInput("Number", Input.NUMBER),
			new MdInput("Password", Input.PASSWORD),
			new MdIcon("search"));
		box.setStyle("-fx-background-color: #ffffff;");

		primaryStage.setScene(new Scene(box, 200D, 200D));
		primaryStage.show();
	}
}
