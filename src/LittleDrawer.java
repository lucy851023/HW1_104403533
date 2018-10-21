//HW1--小畫家              莊于萱_104403533_資管2B
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane; 
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class LittleDrawer extends JFrame{
	private JLabel location;//放游標位置
	private JLabel draw1;//【繪圖工具】的label
	private JLabel draw2;//【筆刷大小】的label
	private JPanel panel;//放按鈕的panel
	private JPanel canvas;//畫作區
	private JComboBox drawingTool;
	private static String[] drawingToolNames ={"筆刷","直線","橢圓形","矩形","圓角矩形"};
	private JRadioButton small;//筆刷"小"
	private JRadioButton middle;//筆刷"中"
	private JRadioButton large;//筆刷"大"
	private ButtonGroup radioGroup;
	private JCheckBox full;//是否填滿
	private JButton foreground;//"前景色"按鈕
	private JButton background;//"背景色"按鈕
	private JButton delete;//"清除畫面"按鈕
	
	public LittleDrawer(){
		super("小畫家");
		panel = new JPanel();
		canvas = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(10,1));//建立按鈕排列方式
		add(panel,BorderLayout.WEST);//把panel放到整個JFrame的西側
		canvas.setBackground(Color.ORANGE);//設定畫作區顏色
		add(canvas,BorderLayout.CENTER);//把畫作區放入整個JFrame中間(中+右)
		
		draw1 = new JLabel("【繪圖工具】");
		panel.add(draw1);//將draw1加進panel
		
		drawingTool = new JComboBox<String>(drawingToolNames);
		drawingTool.setMaximumRowCount(5);//一次顯示5列
		drawingTool.addItemListener(
				new ItemListener() // anonymous inner class
				{   //handle JComboBox event
					public void itemStateChanged(ItemEvent event){
					if(event.getStateChange()== ItemEvent.SELECTED)// determine whether item selected
						JOptionPane.showMessageDialog(LittleDrawer.this,String.format("你點選了:%s",event.getItem()));
								
					}
				}
				);

		panel.add(drawingTool);//將drawingTool加進panel
		
		draw2 = new JLabel("【筆刷大小】");
		panel.add(draw2);//將draw2加進panel
		
		small = new JRadioButton("小",true);
		middle = new JRadioButton("中",false);
		large = new JRadioButton("大",false);
		panel.add(small);//將small,middle,large加進panel
		panel.add(middle);
		panel.add(large);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(small); //將small,middle,large加進 radioGroup
		radioGroup.add(middle);
		radioGroup.add(large);
		RadioButtonHandler radioButtonHandler = new RadioButtonHandler(); 
		small.addActionListener(radioButtonHandler);
		middle.addActionListener(radioButtonHandler);
		large.addActionListener(radioButtonHandler);
		
		full = new JCheckBox("填滿");
		full.addItemListener(new CheckBoxHandler());
		panel.add(full);//將full加進 panel
		
		foreground = new JButton("前景色");
		background = new JButton("背景色");
		delete = new JButton("清除畫面");
		ButtonHandler buttonHandler = new ButtonHandler();
		foreground.addActionListener(buttonHandler);
		background.addActionListener(buttonHandler);
		delete.addActionListener(buttonHandler);
		panel.add(foreground); //將foreground,background,delete加進panel
		panel.add(background);
		panel.add(delete);
		
		location = new JLabel("游標位置:");
		add(location,BorderLayout.SOUTH); //把location放到整個JFrame的下方
		
		MouseHandler mouseHandler = new MouseHandler();
		canvas.addMouseMotionListener(mouseHandler);
		
		
	}
		
	private class RadioButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event){ //handle radio button event
			JOptionPane.showMessageDialog(LittleDrawer.this,String.format("你點選了:%s",event.getActionCommand()));
		}
	}
	
	private class CheckBoxHandler implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)//handle checkBox event
		{
			if(full.isSelected())
			  System.out.println("你選擇了填滿");
			else
			  System.out.println("你取消了填滿");	
		}
	}
		
	private class ButtonHandler implements ActionListener 
	   {
	      public void actionPerformed(ActionEvent event)//handle button event
	      {
	         JOptionPane.showMessageDialog(LittleDrawer.this, String.format(
	            "你點選了:%s", event.getActionCommand()));
	      }
	   } 
	private class MouseHandler extends MouseMotionAdapter{ //handle Mouse event
		public void mouseMoved(MouseEvent event){
			location.setText(String.format("游標位置:(%d,%d)",event.getX(),event.getY()));
		}
	
	}
	
}
