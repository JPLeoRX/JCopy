package io.github.jpleorx.jcopy.ui.root.copying_procedure_section.button_and_estimation;

import javax.swing.*;
import java.awt.*;

public class ProcedureButtonAndEstimationPanel extends JPanel {

    private ControlButtonsPanel controlButtonsPanel;
    private EstimationInfoPanel estimationInfoPanel;

    public ProcedureButtonAndEstimationPanel() {
        this.init();
    }

    private void init() {
        this.setLayout(new BorderLayout());

        controlButtonsPanel = new ControlButtonsPanel();
        estimationInfoPanel = new EstimationInfoPanel();

        this.add(controlButtonsPanel, BorderLayout.NORTH);
        this.add(estimationInfoPanel, BorderLayout.SOUTH);
    }
}
