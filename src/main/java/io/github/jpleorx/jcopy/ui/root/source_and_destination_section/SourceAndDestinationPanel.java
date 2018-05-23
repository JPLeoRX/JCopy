package io.github.jpleorx.jcopy.ui.root.source_and_destination_section;

import io.github.jpleorx.jcopy.ui.root.source_and_destination_section.destination.DestinationPanel;
import io.github.jpleorx.jcopy.ui.root.source_and_destination_section.source.SourcePanel;

import javax.swing.*;
import java.awt.*;

public class SourceAndDestinationPanel extends JPanel {
    public SourceAndDestinationPanel() {
        super(new GridLayout(2, 1));

        this.add(new SourcePanel());
        this.add(new DestinationPanel());
    }
}
