package Project.UI;

import Project.Math.Instruction;
import Project.Math.SpaceCraft;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Shows instructions for a selected spacecraft.
 */
public class InstructionPanelManager {

    private JPanel root;
    private JPanel listPanel;
    private JScrollPane scrollPane;

    private SpaceCraft spaceCraft;

    private JTextField thrustField;
    private JTextField directionField;
    private JTextField waitField;
    private JTextField durationField;

    private JPanel inputCard;

    private final Map<Instruction, JPanel> cardMap = new LinkedHashMap<>();

    private static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font FIELD_FONT = new Font("Arial", Font.PLAIN, 18);
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 18);
    private static final Font CARD_FONT = new Font("Arial", Font.PLAIN, 18);

    public InstructionPanelManager(SpaceCraft spaceCraft) {
        this.spaceCraft = spaceCraft;
        init();
    }

    private void init() {
        root = new JPanel(new BorderLayout());

        listPanel = new JPanel();
        listPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));

        scrollPane = new JScrollPane(listPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        addInputCard();

        root.add(inputCard, BorderLayout.WEST);
        root.add(scrollPane, BorderLayout.CENTER);

        refreshUI();
    }

    private void addInputCard() {
        inputCard = new JPanel(new GridLayout(5, 2, 8, 8));
        inputCard.setPreferredSize(new Dimension(280, 180));
        inputCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        thrustField = new JTextField("10");
        directionField = new JTextField("0");
        waitField = new JTextField("0");
        durationField = new JTextField("5");

        styleField(thrustField);
        styleField(directionField);
        styleField(waitField);
        styleField(durationField);

        JLabel thrustLabel = new JLabel("Thrust");
        JLabel directionLabel = new JLabel("Direction");
        JLabel waitLabel = new JLabel("Wait");
        JLabel durationLabel = new JLabel("Duration");

        styleLabel(thrustLabel);
        styleLabel(directionLabel);
        styleLabel(waitLabel);
        styleLabel(durationLabel);

        inputCard.add(thrustLabel);
        inputCard.add(thrustField);

        inputCard.add(directionLabel);
        inputCard.add(directionField);

        inputCard.add(waitLabel);
        inputCard.add(waitField);

        inputCard.add(durationLabel);
        inputCard.add(durationField);

        JButton add = new JButton("Add");
        add.setFont(BUTTON_FONT);
        add.setPreferredSize(new Dimension(120, 40));
        add.addActionListener(e -> createInstruction());

        inputCard.add(new JLabel(""));
        inputCard.add(add);
    }

    private void createInstruction() {
        double thrust = Math.min(parse(thrustField.getText()), spaceCraft.getMaxThrust());
        double direction = parse(directionField.getText());
        double wait = parse(waitField.getText());
        double duration = parse(durationField.getText());

        Instruction instruction = new Instruction(thrust, direction, wait, duration);
        spaceCraft.addInstruction(instruction);

        refreshUI();
    }

    private double parse(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private JPanel createInstructionCard(Instruction instruction) {
        JPanel card = new JPanel(new BorderLayout(8, 8));
        card.setPreferredSize(new Dimension(240, 140));
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel label = new JLabel(format(instruction));
        label.setFont(CARD_FONT);

        JButton remove = new JButton("X");
        remove.setFont(BUTTON_FONT);
        remove.setPreferredSize(new Dimension(50, 40));
        remove.setForeground(Color.RED);

        remove.addActionListener(e -> {
            spaceCraft.removeInstruction(instruction);
            refreshUI();
        });

        card.add(label, BorderLayout.CENTER);
        card.add(remove, BorderLayout.EAST);

        return card;
    }

    public void refreshUI() {
        cardMap.entrySet().removeIf(entry -> {
            if (!spaceCraft.getInstructions().contains(entry.getKey())) {
                listPanel.remove(entry.getValue());
                return true;
            }
            return false;
        });

        for (Instruction instruction : spaceCraft.getInstructions()) {
            JPanel card = cardMap.get(instruction);

            if (card == null) {
                card = createInstructionCard(instruction);
                cardMap.put(instruction, card);
                listPanel.add(card);
            } else {
                JLabel label = (JLabel) card.getComponent(0);
                label.setText(format(instruction));
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    private String format(Instruction i) {
        return "<html>"
                + "<div style='font-size:18px;'>"
                + "T: " + i.getThrustString() + "<br>"
                + "D: " + i.getDirectionString() + "<br>"
                + "W: " + i.getWaitString() + "<br>"
                + "Dur: " + i.getDurationString()
                + "</div></html>";
    }

    private void styleLabel(JLabel label) {
        label.setFont(LABEL_FONT);
    }

    private void styleField(JTextField field) {
        field.setFont(FIELD_FONT);
        field.setPreferredSize(new Dimension(120, 32));
    }

    public JPanel getRoot() {
        return root;
    }
}