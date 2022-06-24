package main.query;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class TopPanel extends JSplitPane{
	
	public TopPanel() {
		super(HORIZONTAL_SPLIT, TopLeftPanel.getIstance(), TopRightPanel.getIstance());
		setResizeWeight(.95);
		setEnabled(false);
        setDividerSize(1);
	}

}
