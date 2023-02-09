module chrispy.javafx.md3_icon
{
	requires transitive javafx.controls;

	exports com.github.chrispy.javafx.md3.icon;

	opens com.github.chrispy.javafx.md3.icon to javafx.fxml;
}
