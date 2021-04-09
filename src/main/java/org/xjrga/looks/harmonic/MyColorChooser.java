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

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Jorge R Garcia de Alba <jorge.r.garciadealba@gmail.com>
 */
public class MyColorChooser {

    private JFrame frame;

    public MyColorChooser() {
        frame = new JFrame("MyColorChooser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JColorChooser chooser = new JColorChooser();
        FlowLayout layout = new FlowLayout();
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setPreferredSize(new Dimension(600, 200));
        panel.setLayout(layout);
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(200, 200));
        label.setOpaque(true);
        panel.add(label);
        JScrollPane jScrollPane = new JScrollPane(panel);  
        jScrollPane.setViewportView(panel);
        jScrollPane.setPreferredSize(new Dimension(590,300));
        chooser.setPreviewPanel(jScrollPane);
        frame.setContentPane(chooser);
        frame.setPreferredSize(new Dimension(600, 600));
        frame.pack();
        frame.setVisible(true);
        chooser.getSelectionModel().addChangeListener((ChangeEvent event) -> {
            ColorHarmonic colorHarmonic = new ColorHarmonic(chooser.getColor());
            Iterator<HarmonicColor> iterator = colorHarmonic.getIterator();
            new Thread() {
                public void run() {                    
                    while (iterator.hasNext()) {
                        HarmonicColor next = iterator.next();
                        JLabel lab = new JLabel();
                        lab.setOpaque(true);
                        lab.setText(next.getAngle()+"");
                        lab.setBackground(next.getColor());                        
                        panel.add(lab);
                        System.out.println(next.getName() + ":" + next.getAngle());
                        label.setText(next.getName());
                        //label.setBackground(Color.BLUE);
                        panel.setBackground(next.getBaseColor());                        
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MyColorChooser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }.start();            
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
