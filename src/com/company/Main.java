package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private static int taskQuantity;
    private static int maxNumber;
    private static JFrame jFrame;
    private static MathGame mathGame;
    private static int counter = 0;
    private JPanel jPanel;
    private JTextField jTextField;


    public static void main(String[] args) {
        jFrame = new JFrame("Mатематична гра");
        jFrame.setSize(800, 500);
        jFrame.add(menuPanel());
        jFrame.setVisible(true);
        jFrame.setLocation(400, 150);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static void playMathGame() {
        jFrame = new JFrame("Mатематична гра");
        jFrame.setSize(800, 500);
        jFrame.add(menuPanel());
        jFrame.setVisible(true);
        jFrame.setLocation(400, 150);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private static JPanel menuPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.WHITE);
        JLabel name = new JLabel("Математична гра");
        name.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(name);
        JButton start = new JButton("Почати");
        panel.add(start);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
                jFrame.add(settingsPanel());
                settingsPanel().setVisible(true);
            }
        });
        return panel;
    }

    private static JPanel settingsPanel() {
        JPanel exampl = new JPanel(new GridLayout(5, 1));
        exampl.setBackground(Color.LIGHT_GRAY);
        JLabel jLabel = new JLabel("Встановіть кількість прикладів(від 1 до 20)");
        JSlider jSlider = new JSlider(JSlider.HORIZONTAL, 0, 20, 10);
        jSlider.setMajorTickSpacing(2);
        jSlider.setMinorTickSpacing(1);
        jSlider.setPaintTicks(true);
        jSlider.setPaintLabels(true);
        jLabel.setLabelFor(jSlider);
        exampl.add(jLabel);
        exampl.add(jSlider);

        JLabel maxForExampl = new JLabel("Встановіть максимальне число для прикладів(від 0 до 100)");
        JSlider jSlider1 = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        jSlider1.setMajorTickSpacing(5);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintTicks(true);
        jSlider1.setPaintLabels(true);
        maxForExampl.setLabelFor(jSlider1);
        exampl.add(maxForExampl);
        exampl.add(jSlider1);
        JButton ok = new JButton("ОK");

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exampl.setVisible(false);
                taskQuantity = jSlider.getValue();
                maxNumber = jSlider1.getValue();
                mathGame = new MathGame(taskQuantity, maxNumber);
                jFrame.add(mathGamePanel(mathGame, 0));
            }
        });
        exampl.add(ok);
        return exampl;
    }


    private static JPanel mathGamePanel(MathGame tasks, int i) {
        SpinnerModel value = new SpinnerNumberModel(0, 0, maxNumber, 1);
        JPanel jPanel = new JPanel(new GridLayout(5, 1));
        JLabel task = new JLabel("" + tasks.getTask(i));
        task.setHorizontalAlignment(SwingConstants.CENTER);
        jPanel.add(task);
        JSpinner jSpinner = new JSpinner(value);
        jPanel.add(jSpinner);
        JButton ok = new JButton("Продовжити");
        jPanel.add(ok);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int answer = (int) jSpinner.getValue();
                if (answer == tasks.getAnswer(i)) {
                    JOptionPane.showMessageDialog(jFrame, "          Правильно!!!", "Answer", JOptionPane.INFORMATION_MESSAGE);
                    counter++;
                } else if (answer != tasks.getAnswer(i)) {
                    JOptionPane.showMessageDialog(jFrame, "Невірно!!! Правильну відповідь : " + tasks.getAnswer(i), "Answer", JOptionPane.INFORMATION_MESSAGE);
                }
                jPanel.setVisible(false);
                if (i < taskQuantity - 1) {
                    jFrame.add(mathGamePanel(tasks, i + 1));
                } else if (i == taskQuantity - 1) {
                    jFrame.add(finish());
                }
            }
        });
        return jPanel;
    }

    private static JPanel finish() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(Color.WHITE);
        JLabel label = new JLabel("Ви набрали " + counter + " бали з " + taskQuantity);
        counter = 0;
        panel.add(label);
        JButton restart = new JButton("Спробувати ще раз");
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finish().setVisible(false);
                jFrame.setVisible(false);
                playMathGame();
            }
        });
        panel.add(restart);
        return panel;
    }
}
