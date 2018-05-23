package io.github.jpleorx.jcopy.ui.root.copying_procedure_section;

import io.github.jpleorx.jcopy.ui.root.copying_procedure_section.button_and_estimation.EstimationInfoPanel;
import io.github.jpleorx.jcopy.ui.root.copying_procedure_section.button_and_estimation.ProcedureButtonAndEstimationPanel;

import javax.swing.*;
import java.awt.*;

public class ProcedurePanel extends JPanel {


    public ProcedurePanel() {
        super(new BorderLayout());
        this.setBorder(BorderFactory.createTitledBorder("Copying procedure"));

        this.add(new ProcedureButtonAndEstimationPanel(), BorderLayout.NORTH);
        this.add(new ProgressPanel(), BorderLayout.CENTER);
    }
}
