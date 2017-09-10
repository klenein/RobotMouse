package com.awesome.cho;

import java.awt.AWTException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RobotMouse extends JFrame
{
	private static int loopCount = 10;
	private static int loopDelay = 2000;
	private static int start_x;
	private static int start_y;
	private static int end_x = 300;
	private static int end_y = 300;
	private boolean inputReceived = false;
	
	
	public RobotMouse() throws AWTException
	{
		
		JLabel labelLoopCount = new JLabel("Loop Count:");
		JLabel labelDelay = new JLabel("Delay Time:");
		JTextField textLoopCount = new JTextField(10);
		JTextField textDelay = new JTextField(10);
		JButton startButton = new JButton("START");
		JButton stopButton = new JButton("STOP");
		
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setLayout(new GridBagLayout());
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(labelLoopCount, constraints);
		
		constraints.gridx = 1;
		panel.add(textLoopCount, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(labelDelay, constraints);
		
		constraints.gridx = 1;
		panel.add(textDelay, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(startButton, constraints);
		
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(stopButton, constraints);
		
		/*constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(startButton, constraints);*/
		
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Robot Mouse"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textLoopCount.setText("20");
		textDelay.setText("2000");
		
		add(panel);
		
		pack();
		setLocationRelativeTo(null);
		
		
		setVisible(true);
		
		startButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						start_x = MouseInfo.getPointerInfo().getLocation().x;
						start_y = MouseInfo.getPointerInfo().getLocation().y;
						
						loopCount = Integer.parseInt(textLoopCount.getText());
						loopDelay = Integer.parseInt(textDelay.getText());
						
						System.out.println(loopCount + " " + loopDelay);
						
						inputReceived = true;
						
						try
						{
							mouseMove();
						} catch (AWTException e1)
						{
							e1.printStackTrace();
						}
					}
				});
		
		stopButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(1);
			}
		});
		
	}
	
	public static void mouseMove() throws AWTException
	{
		Robot robot = new Robot();
		Random rand = new Random();
		
		for (int x = 0; x < loopCount; x++)
		{
			end_x = rand.nextInt(1000);
			end_y = rand.nextInt(1000);
			
			for (int i=0; i<100; i++)
			{
				int mov_x = ((end_x * i)/100) + (start_x*(100-i)/100);
				int mov_y = ((end_y * i)/100) + (start_y*(100-i)/100);
				robot.mouseMove(mov_x,mov_y);
				robot.delay(10);
			}
			
			start_x = MouseInfo.getPointerInfo().getLocation().x;
			start_y = MouseInfo.getPointerInfo().getLocation().y;
			
			robot.delay(loopDelay);
		}
	}
	
	public static void main(String[] args) throws AWTException
	{
		new RobotMouse();
		//mouseMove();
		
		
	}
	

}