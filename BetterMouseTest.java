
package bettermousetest;

//
// This example program was adapted from an example program provided with
//
//     Horstmann & Cornell
//     _Core Java, Volume 1 - Fundamentals_
//     Prentice Hall
//


import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;


public class BetterMouseTest
{

    public static void main(String [] commandLineArguments)
    {

        EventQueue.invokeLater(
            () ->
                {

                    ProgramFrame frame = new ProgramFrame();

                    frame.setTitle("Better Mouse Test");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);

                    }
            );

        }

    }


class ProgramFrame extends JFrame
{

    public ProgramFrame()
    {

        add(new MouseComponent());
        pack();

        }

    }


class MouseComponent extends JComponent
{

    private static final int OUR_DEFAULT_WIDTH = 300;
    private static final int OUR_DEFAULT_HEIGHT = 300;

    private static final int OUR_SQUARE_SIDE_LENGTH = 30;

    private ArrayList< Rectangle2D > mySquares;
    private Rectangle2D myCurrentSquare;

    private ArrayList< Rectangle2D > squares()
    {

        return mySquares;

        }

    private Rectangle2D currentSquare()
    {

        return myCurrentSquare;

        }

    private void setSquares(ArrayList< Rectangle2D > other)
    {

        mySquares = other;

        }

    private void setCurrentSquare(Rectangle2D other)
    {

        myCurrentSquare = other;

        }

    public MouseComponent()
    {

        setSquares(new ArrayList< Rectangle2D >());
        setCurrentSquare(null);

        addMouseListener(new MouseHandler());
        addMouseMotionListener(new MouseMotionHandler());

        }

    @Override
    public Dimension getPreferredSize()
    {

        return new Dimension(OUR_DEFAULT_WIDTH, OUR_DEFAULT_HEIGHT);

        }


    @Override
    public void paintComponent(Graphics canvas)
    {

        for (Rectangle2D squareOnCanvas : squares())
            ((Graphics2D) canvas).draw(squareOnCanvas);

        }

    public Rectangle2D findSquareContainingPoint(Point2D clickPoint)
    {

        int squareNumber;

        for (squareNumber = 0;
                squareNumber < squares().size()
                    && ! squares().get(squareNumber).contains(clickPoint);
                ++ squareNumber)
                //
                // inv:
                //     No square in squares()[0 ... squareNumber - 1] spatially contains clickpoint
                //
            ; // do nothing

        return squareNumber < squares().size() ? squares().get(squareNumber) : null;

        }

    public void placeAdditionalSquare(Point2D clickPoint)
    {

        setCurrentSquare(
            new Rectangle2D.Double(
                clickPoint.getX() - OUR_SQUARE_SIDE_LENGTH / 2,
                clickPoint.getY() - OUR_SQUARE_SIDE_LENGTH / 2,
                OUR_SQUARE_SIDE_LENGTH,
                OUR_SQUARE_SIDE_LENGTH
                )
            );

        squares().add(currentSquare());

        repaint();

        System.out.println(
            "New square placed at (" + clickPoint.getX() +", " + clickPoint.getY() + ")"
            );

        }

    public void removeExistingSquare(Rectangle2D existingSquare)
    {

        if (existingSquare != null) {

            if (existingSquare == currentSquare())
                setCurrentSquare(null);

            squares().remove(existingSquare);

            repaint();

            System.out.println("Existing square removed");

            }

        }


    private class MouseHandler extends MouseAdapter
    {

        int myPresses = 0;
        int myClicks = 0;
        int clickCounter = 0;

        @Override
        public void mousePressed(MouseEvent event)
        {
            Rectangle2D previousSquare = currentSquare();

            ++ myPresses;
            System.out.println(
                "Mouse press " + myPresses + " at (" + event.getX() + ", " + event.getY() + ")"
                );

            setCurrentSquare(findSquareContainingPoint(event.getPoint()));
            if (previousSquare != currentSquare())
                clickCounter = 1;

            if (currentSquare() == null) {
                placeAdditionalSquare(event.getPoint());
                clickCounter = 0;
            }
            ++ clickCounter;

            }

        @Override
        public void mouseClicked(MouseEvent event)
        {

            ++ myClicks;


            if (event.getClickCount() < 2)
                System.out.println(
                    "Mouse click " + myClicks + " at (" + event.getX() + ", " + event.getY() + ")"
                    );
            else
                System.out.println(
                    "Mouse double-click " + myClicks + " at (" + event.getX() + ", " + event.getY() + ")"
                    );


            setCurrentSquare(findSquareContainingPoint(event.getPoint()));

            System.out.println("[DEBUG] " + "getClickCount : " + event.getClickCount());

            System.out.print("[DEBUG] ");
            System.out.print((currentSquare() != null) + " : ");
            System.out.print((event.getClickCount() >= 2) + " : ");
            System.out.println(clickCounter > 2);
            if (currentSquare() != null && event.getClickCount() >= 2 && clickCounter > 2) {
                removeExistingSquare(currentSquare());
                clickCounter = 0;
            }


            System.out.println(clickCounter);
            }


        }


    private class MouseMotionHandler implements MouseMotionListener
    {

        int myMoves = 0;
        int myDrags = 0;

        @Override
        public void mouseMoved(MouseEvent event)
        {

            ++ myMoves;
//            System.out.println(
//                "Mouse move " + myMoves + " to (" + event.getX() + ", " + event.getY() + ")"
//                );

            if (findSquareContainingPoint(event.getPoint()) == null)
                setCursor(Cursor.getDefaultCursor());
            else
                setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

            }

        @Override
        public void mouseDragged(MouseEvent event)
        {

            ++ myDrags;
//            System.out.println(
//                "Mouse drag " + myDrags + " to (" + event.getX() + ", " + event.getY() + ")"
//                );

            if (currentSquare() != null) {

                currentSquare().setFrame(
                    event.getX() - OUR_SQUARE_SIDE_LENGTH / 2,
                    event.getY() - OUR_SQUARE_SIDE_LENGTH / 2,
                    OUR_SQUARE_SIDE_LENGTH,
                    OUR_SQUARE_SIDE_LENGTH
                    );

                repaint();

                }

            }

        }

    }
