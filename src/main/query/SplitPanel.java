package main.query;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class SplitPanel extends JSplitPane{

	public SplitPanel() {
		super(VERTICAL_SPLIT, new TopPanel(), new BottomPanel());
		setResizeWeight(.5);
	}
}
