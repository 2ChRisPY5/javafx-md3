module chrispy.javafx.md3_input
{
	requires transitive chrispy.javafx.md3_base;
	requires transitive chrispy.javafx.md3_icon;
	requires javafx.fxml;
	requires javafx.controls;

	exports com.github.chrispy.javafx.md3.input;

	opens com.github.chrispy.javafx.md3.input to javafx.fxml;
}
