/*
 * Copyright (C) 2021 Jorge R Garcia de Alba <jorge.r.garciadealba@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package org.xjrga.looks.harmonic;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Jorge R Garcia de Alba &lt;jorge.r.garciadealba@gmail.com&gt;
 */
public class MyColorChooser {

    private final JFrame frame;
    private final JRadioButton option01;
    private final JRadioButton option02;
    private final JRadioButton option03;
    private Color fontColor;
    private Categorizer categorizer;

    public MyColorChooser() {
        frame = new JFrame("MyColorChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JColorChooser chooser = new JColorChooser();
        Categorizer categorizer = new Categorizer();
        chooser.addChooserPanel(new MyChooserPanel());
        JPanel panelColors = new JPanel();
        panelColors.setOpaque(true);
        //panelColors.setPreferredSize(new Dimension(600, 200));
        //panelColors.setLayout(new GridLayout(0,4));
        panelColors.setLayout(new FlowLayout());
        panelColors.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        panelColors.add(new JButton("Hello!"));
        JScrollPane jScrollPane = new JScrollPane(panelColors);
        jScrollPane.setViewportView(panelColors);
        jScrollPane.setPreferredSize(new Dimension(600, 200));
        chooser.setPreviewPanel(new JPanel());
        JPanel main = new JPanel();
        JPanel panel00 = new JPanel();
        JPanel panel01 = new JPanel();
        main.setLayout(new GridLayout(0, 1));
        panel00.setLayout(new FlowLayout());
        panel00.setBorder(new TitledBorder("00"));
        panel01.setLayout(new FlowLayout());
        panel01.setBorder(new TitledBorder("01"));
        option01 = new JRadioButton("Background");
        option02 = new JRadioButton("Font");
        option03 = new JRadioButton("Harmonic");
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(option01);
        group.add(option02);
        group.add(option03);
        panel00.add(option01);
        panel00.add(option02);
        panel00.add(option03);
        panel01.add(jScrollPane);
        main.add(chooser);
        main.add(panel00);
        main.add(panel01);
        frame.setContentPane(main);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setVisible(true);
        chooser.getSelectionModel().addChangeListener((var event) -> {
            ColorHarmonic colorHarmonic = new ColorHarmonic(chooser.getColor());
            if (option02.isSelected()) {
                fontColor = chooser.getColor();
                new Thread() {
                    public void run() {
                        Component[] components = panelColors.getComponents();
                        for (int i = 0; i < components.length; i++) {
                            if (components[i] instanceof JLabel) {
                                components[i].setForeground(fontColor);
                            }
                        }
                    }
                }.start();
            } else if (option01.isSelected()) {
                new Thread() {
                    public void run() {
                        panelColors.setBackground(chooser.getColor());
                    }
                }.start();
            } else if (option03.isSelected()) {
                Iterator<HarmonicColor> iterator = colorHarmonic.getIterator();
                new Thread() {
                    public void run() {
                        panelColors.removeAll();
                        while (iterator.hasNext()) {
                            HarmonicColor next = iterator.next();
                            JLabel lab = new JLabel();
                            lab.setOpaque(true);
                            lab.setPreferredSize(new Dimension(50, 50));
                            lab.setForeground(fontColor);
                            lab.setText(next.getAngle()+ "");
                            //lab.setText(next.getAngleChange()+ "");
                            lab.setBackground(next.getColor());
                            lab.setBorder(new LineBorder(Color.BLACK));
                            lab.setHorizontalAlignment(SwingConstants.CENTER);
                            lab.setVerticalAlignment(SwingConstants.CENTER);
                            panelColors.add(lab);
                            categorizer.setHarmonicColor(next);                            
                            System.out.println(next.getName() + ":" + next.getAngle()+":"+categorizer.getColorTemperature());
                            panelColors.revalidate();
                        }
                    }
                }.start();
            }

        });
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                exit();
            }
        });
    }

    public void exit() {
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyColorChooser myColorChooser = new MyColorChooser();
        });
    }

}
