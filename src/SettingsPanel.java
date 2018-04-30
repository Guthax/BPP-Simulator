import javax.swing.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        PackagesPanel panel = new PackagesPanel();
        add(panel);

        BoxSelectionPanel boxSelectionPanel = new BoxSelectionPanel();
        add(boxSelectionPanel);

    }
}
