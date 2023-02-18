package com.github.chrispy.javafx.md3.app;

import com.github.chrispy.javafx.md3.icon.MdIcon;
import com.github.chrispy.javafx.md3.input.Design;
import com.github.chrispy.javafx.md3.input.Input;
import com.github.chrispy.javafx.md3.input.MdInput;

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
			new MdIcon("search", 24),
			new MdInput("Plain text"),
			new MdInput("Number", Input.NUMBER),
			new MdInput("Password", Input.PASSWORD),
			new MdInput("Outline", Input.TEXT, Design.OUTLINE));
		box.setStyle("-fx-background-color: #ffffff;");

		// pre / suffix
		final var preSuffix = new MdInput("Pre & Suffix");
		preSuffix.setPrefix("+49");
		preSuffix.setSuffix(new MdIcon("call", 22));
		box.getChildren().add(preSuffix);

		primaryStage.setScene(new Scene(box, 200D, 200D));
		primaryStage.show();
	}
}
