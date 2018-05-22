package io.github.jpleorx.jcopy.ui.root;

import io.github.jpleorx.jcopy.ui.ProcedurePanel;
import io.github.jpleorx.jcopy.ui.names.NamesPanel;
import io.github.jpleorx.jcopy.ui.root.source_and_destination_section.SourceAndDestinationPanel;

import javax.swing.*;
import java.awt.*;

/**
 * The main frame (window) of this app
 *
 * @author Leo Ertuna
 * @since 23.05.2018 20:56
 */
public class RootFrame extends JFrame  {
    public RootFrame() {
        this.init();
    }

    private void init() {
        // Title, close operation, size, location
        this.setTitle("JCopy");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);

        // Layout
        this.setLayout(new GridLayout(1, 3));

        // Add main sections
        this.add(new NamesPanel());
        this.add(new SourceAndDestinationPanel());
        this.add(new ProcedurePanel());

        // Make frame visible
        this.setVisible(true);
    }
}