module chrispy.javafx.md3_input
{
	requires transitive chrispy.javafx.md3_base;
	requires javafx.fxml;
	requires javafx.controls;

	exports com.github.chrispy.javafx.md3.input;
	exports com.github.chrispy.javafx.md3.input.types;

	opens com.github.chrispy.javafx.md3.input to javafx.fxml;
}
