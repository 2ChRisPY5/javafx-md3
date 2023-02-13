package com.github.chrispy.javafx.md3.input;

import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import com.github.chrispy.javafx.md3.base.MdPseudoClass;
import com.github.chrispy.javafx.md3.base.fn.UnsafeRunnable;
import com.github.chrispy.javafx.md3.base.utils.StringUtils;
import com.github.chrispy.javafx.md3.icon.MdIcon;
import com.github.chrispy.javafx.md3.input.formatter.NumberFilter;
import com.github.chrispy.javafx.md3.input.formatter.PasswordFilter;
import com.github.chrispy.javafx.md3.input.types.Design;
import com.github.chrispy.javafx.md3.input.types.Input;

import javafx.animation.TranslateTransition;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;

/**
 * Material Design Input Field
 *
 * @author 2ChRisPY5
 */
public class MdInput extends GridPane
{
	private static final String PRE_SUFFIX_STYLE = "-fx-fill: rgba(0,0,0,0.87)";
	private static final URL FXML = MdInput.class.getResource("/md-input.fxml");

	// view childs
	@FXML
	private TextField textField;
	@FXML
	private Label label;

	private final TranslateTransition transition = new TranslateTransition(Duration.millis(100));
	private final Design design;
	private final Input input;
	private Optional<Text> prefix = Optional.empty();
	private Optional<Text> suffix = Optional.empty();

	/**
	 * Constructor
	 *
	 * @param label the label text
	 * @param input the {@link Input} type
	 * @param design the {@link Design}
	 */
	public MdInput(@NamedArg(value = "label") final String label,
		@NamedArg(value = "type", defaultValue = "text") final String input,
		@NamedArg(value = "design", defaultValue = "fill") final String design,
		@NamedArg("iconPrefix") final String iconPrefix,
		@NamedArg("iconSrefix") final String iconSuffix)
	{
		this(label, Input.valueOf(input.toUpperCase()), Design.valueOf(design.toUpperCase()));

		// handle icon prefix
		if(!StringUtils.nullOrBlank(iconPrefix))
		{
			setInternalPrefix(new MdIcon(iconPrefix, 22));
		}
		// handle icon suffix
		if(!StringUtils.nullOrBlank(iconSuffix))
		{
			setInternalSuffix(new MdIcon(iconSuffix, 22));
		}
	}

	/**
	 * Input = TEXT; Design = FILL
	 *
	 * @param label the label text
	 */
	public MdInput(final String label)
	{
		this(label, Input.TEXT);
	}

	/**
	 * Design = FILL
	 *
	 * @param label the label text
	 * @param input the {@link Input} type
	 */
	public MdInput(final String label, final Input input)
	{
		this(label, input, Design.FILL);
	}

	/**
	 * Constructor
	 *
	 * @param label the label text
	 * @param input the {@link Input} type
	 * @param design the {@link Design}
	 */
	public MdInput(final String label, final Input input, final Design design)
	{
		this.design = design;
		this.input = input;

		// load fxml
		final var loader = new FXMLLoader(FXML);
		loader.setRoot(this);
		loader.setController(this);
		UnsafeRunnable.run(loader::load);

		// initialize stuff
		this.label.setText(StringUtils.nullOrBlank(label) ? null : label);
		this.transition.setNode(this.label);
		initialize();
	}

	/**
	 * Set a text prefix for this input.
	 *
	 * @param text the text to show
	 */
	public void setPrefix(final String text)
	{
		if(StringUtils.nullOrBlank(text))
		{
			setInternalPrefix(null);
			return;
		}

		final var txt = new Text(text);
		txt.setStyle(PRE_SUFFIX_STYLE);
		setInternalPrefix(txt);
	}

	/**
	 * Set an icon prefix for this input.
	 *
	 * @param icon the {@link MdIcon}
	 */
	public void setPrefx(final MdIcon icon)
	{
		setInternalPrefix(icon);
	}

	/**
	 * Set a text suffix for this input.
	 *
	 * @param text the text to show
	 */
	public void setSuffix(final String text)
	{
		if(StringUtils.nullOrBlank(text))
		{
			setInternalSuffix(null);
			return;
		}

		final var txt = new Text(text);
		txt.setStyle(PRE_SUFFIX_STYLE);
		setInternalSuffix(txt);
	}

	/**
	 * Set an icon suffix for this input.
	 *
	 * @param icon the {@link MdIcon}
	 */
	public void setSuffix(final MdIcon icon)
	{
		setInternalSuffix(icon);
	}

	/**
	 * Set the value of this input. Blank/empty string will be set to null. Even if this method does also
	 * handle password type, you should use the char[] implementation.
	 *
	 * @param value the string value or null
	 */
	public void setValue(final String value)
	{
		setValue(() -> {
			if(!StringUtils.nullOrBlank(value))
			{
				// special case if password
				if(this.input == Input.PASSWORD)
				{
					setValue(value.toCharArray());
				}
				else
				{
					this.textField.setText(value);
				}
			}
		});
	}

	/**
	 * Internally save the password and mask it for UI.
	 *
	 * @param value the password or null
	 */
	public void setValue(final char[] value)
	{
		setValue(() -> {
			if(Objects.nonNull(value))
			{
				this.textField.setText("\u2022".repeat(value.length));
				((PasswordFilter) this.textField.getTextFormatter().getFilter()).setPassword(value);
			}
		});
	}

	/**
	 * Set the number value for this input. Number will be converted using toString().
	 *
	 * @param value the number or null
	 */
	public void setValue(final Number value)
	{
		setValue(() -> {
			if(Objects.nonNull(value))
			{
				this.textField.setText(value.toString());
			}
		});
	}

	/**
	 * Get the current value correctly typed or null.
	 *
	 * @param <T> String | Number | char[]
	 * @return the typed value or null
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue()
	{
		return (T) switch(this.input)
		{
			case TEXT -> this.textField.getText();
			case NUMBER -> this.textField.getTextFormatter().getValue();
			case PASSWORD -> ((PasswordFilter) this.textField.getTextFormatter().getFilter()).getPassword();
		};
	}

	// ========================= internal ==================== //

	/**
	 * Set the prefix text for this input. Necessary stylings are also applied.
	 *
	 * @param prefix the {@link Text} or null
	 */
	private void setInternalPrefix(final Text prefix)
	{
		// remove old
		this.prefix.ifPresent(getChildren()::remove);

		// add new
		this.prefix = Optional.ofNullable(prefix);
		this.prefix.ifPresent(pf -> add(pf, 0, 0, 1, REMAINING));
	}

	/**
	 * Set the suffix for this input. Necessary stylings are also applied.
	 *
	 * @param suffix the {@link Text} or null
	 */
	private void setInternalSuffix(final Text suffix)
	{
		// remove old
		this.suffix.ifPresent(getChildren()::remove);

		// add new
		this.suffix = Optional.ofNullable(suffix);
		this.suffix.ifPresent(sf -> add(sf, 2, 0, 1, REMAINING));
	}

	/**
	 * Convenient method for updating state when setting text field value.
	 *
	 * @param setter logic for setting the value
	 */
	private void setValue(final Runnable setter)
	{
		this.textField.setText(null);
		setter.run();
		handleNotBlank();
	}

	/**
	 * Initialize stuff
	 */
	private void initialize()
	{
		initDesign();
		initTypeSpecifics();

		// register focus changed
		this.textField.focusedProperty().addListener((obs, old, nev) -> {
			// change focus state
			MdPseudoClass.FOCUSED.state(this, nev);
			MdPseudoClass.FOCUSED.state(this.label, nev);

			// move label up and mark as :focus && :not-empty
			if(nev.booleanValue())
			{
				MdPseudoClass.NOT_EMPTY.apply(this.label);
				this.transition.setToY(0D);
				this.transition.play();
			}
			else
			{
				// handle not blank
				handleNotBlank();
			}
		});
	}

	/**
	 * Initialize input type specific stuff.
	 */
	private void initTypeSpecifics()
	{
		switch(this.input)
		{
			case PASSWORD:
				this.textField.setTextFormatter(new TextFormatter<>(new PasswordFilter()));
				break;
			case NUMBER:
				this.textField.setTextFormatter(new TextFormatter<>(new NumberStringConverter(), null, NumberFilter.INSTANCE));
				break;
			default:
				break;
		}
	}

	/**
	 * Initialize styling.
	 */
	private void initDesign()
	{
		this.design.applyStyle(this);
	}

	/**
	 * Moves the label up if a text exists; else move to middle
	 */
	private void handleNotBlank()
	{
		if(StringUtils.nullOrBlank(this.textField.getText()))
		{
			// shift to center
			MdPseudoClass.NOT_EMPTY.remove(this.label);
			this.transition.setToY(16D);
		}
		else
		{
			// move up
			MdPseudoClass.NOT_EMPTY.apply(this.label);
			this.transition.setToY(0D);
		}
		this.transition.play();
	}
}
