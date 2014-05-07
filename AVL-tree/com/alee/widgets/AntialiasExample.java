package com.alee.widgets;

import javax.swing.*;
import java.awt.*;

/**
 * User: mgarin Date: 25.04.11 Time: 15:21
 */

public class AntialiasExample
{
    public static void main ( String[] args )
    {
        try
        {
            // Устанавливаем нативный стиль компонентов
            UIManager.setLookAndFeel ( UIManager.getSystemLookAndFeelClassName () );
        }
        catch ( Throwable e )
        {
            //
        }

        final JFrame f = new JFrame ();

        f.getRootPane ().setOpaque ( true );
        f.getRootPane ().setBackground ( Color.WHITE );
        f.getRootPane ().setBorder ( BorderFactory.createEmptyBorder ( 10, 10, 10, 10 ) );

        f.getContentPane ().setBackground ( Color.WHITE );
        f.getContentPane ().setLayout ( new BorderLayout ( 5, 5 ) );

        f.getContentPane ().add ( new JComponent()
        {
            public void paintComponent ( Graphics g )
            {
                super.paintComponent ( g );

                Graphics2D g2d = ( Graphics2D ) g;
                g2d.setPaint ( Color.BLACK );
                Stroke oldStroke = g2d.getStroke ();
                Stroke newStroke = new BasicStroke ( 2f );
                FontMetrics fm = g2d.getFontMetrics ();

                //

                g2d.setStroke ( oldStroke );
                g2d.drawRoundRect ( 5, 5, getWidth () / 2 - 10, getHeight () / 2 - 10, 20, 20 );
                String s1 = "1px stroke, AA off";
                g2d.drawString ( s1, getWidth () / 4 - fm.stringWidth ( s1 ) / 2,
                        getHeight () / 4 + fm.getAscent () / 2 );

                g2d.setStroke ( newStroke );
                g2d.drawRoundRect ( 5, getHeight () / 2 + 5, getWidth () / 2 - 10,
                        getHeight () / 2 - 10, 20, 20 );
                String s2 = "2px stroke, AA off";
                g2d.drawString ( s2, getWidth () / 4 - fm.stringWidth ( s2 ) / 2,
                        getHeight () * 3 / 4 + fm.getAscent () / 2 );

                //

                g2d.setRenderingHint ( RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON );
                g2d.setRenderingHint ( RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON );

                g2d.setStroke ( oldStroke );
                g2d.drawRoundRect ( getWidth () / 2 + 5, 5, getWidth () / 2 - 10,
                        getHeight () / 2 - 10, 20, 20 );
                String s3 = "1px stroke, AA on";
                g2d.drawString ( s3, getWidth () * 3 / 4 - fm.stringWidth ( s3 ) / 2,
                        getHeight () / 4 + fm.getAscent () / 2 );

                g2d.setStroke ( newStroke );
                g2d.drawRoundRect ( getWidth () / 2 + 5, getHeight () / 2 + 5, getWidth () / 2 - 10,
                        getHeight () / 2 - 10, 20, 20 );
                String s4 = "2px stroke, AA on";
                g2d.drawString ( s4, getWidth () * 3 / 4 - fm.stringWidth ( s4 ) / 2,
                        getHeight () * 3 / 4 + fm.getAscent () / 2 );
            }
        } );

        f.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
        f.setSize ( 400, 200 );
        f.setLocationRelativeTo ( null );
        f.setVisible ( true );
    }
}
