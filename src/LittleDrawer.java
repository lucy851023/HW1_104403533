//HW1--�p�e�a              ���_��_104403533_���2B
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
	private JLabel location;//���Ц�m
	private JLabel draw1;//�iø�Ϥu��j��label
	private JLabel draw2;//�i����j�p�j��label
	private JPanel panel;//����s��panel
	private JPanel canvas;//�e�@��
	private JComboBox drawingTool;
	private static String[] drawingToolNames ={"����","���u","����","�x��","�ꨤ�x��"};
	private JRadioButton small;//����"�p"
	private JRadioButton middle;//����"��"
	private JRadioButton large;//����"�j"
	private ButtonGroup radioGroup;
	private JCheckBox full;//�O�_��
	private JButton foreground;//"�e����"���s
	private JButton background;//"�I����"���s
	private JButton delete;//"�M���e��"���s
	
	public LittleDrawer(){
		super("�p�e�a");
		panel = new JPanel();
		canvas = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(10,1));//�إ߫��s�ƦC�覡
		add(panel,BorderLayout.WEST);//��panel�����JFrame���谼
		canvas.setBackground(Color.ORANGE);//�]�w�e�@���C��
		add(canvas,BorderLayout.CENTER);//��e�@�ϩ�J���JFrame����(��+�k)
		
		draw1 = new JLabel("�iø�Ϥu��j");
		panel.add(draw1);//�Ndraw1�[�ipanel
		
		drawingTool = new JComboBox<String>(drawingToolNames);
		drawingTool.setMaximumRowCount(5);//�@�����5�C
		drawingTool.addItemListener(
				new ItemListener() // anonymous inner class
				{   //handle JComboBox event
					public void itemStateChanged(ItemEvent event){
					if(event.getStateChange()== ItemEvent.SELECTED)// determine whether item selected
						JOptionPane.showMessageDialog(LittleDrawer.this,String.format("�A�I��F:%s",event.getItem()));
								
					}
				}
				);

		panel.add(drawingTool);//�NdrawingTool�[�ipanel
		
		draw2 = new JLabel("�i����j�p�j");
		panel.add(draw2);//�Ndraw2�[�ipanel
		
		small = new JRadioButton("�p",true);
		middle = new JRadioButton("��",false);
		large = new JRadioButton("�j",false);
		panel.add(small);//�Nsmall,middle,large�[�ipanel
		panel.add(middle);
		panel.add(large);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(small); //�Nsmall,middle,large�[�i radioGroup
		radioGroup.add(middle);
		radioGroup.add(large);
		RadioButtonHandler radioButtonHandler = new RadioButtonHandler(); 
		small.addActionListener(radioButtonHandler);
		middle.addActionListener(radioButtonHandler);
		large.addActionListener(radioButtonHandler);
		
		full = new JCheckBox("��");
		full.addItemListener(new CheckBoxHandler());
		panel.add(full);//�Nfull�[�i panel
		
		foreground = new JButton("�e����");
		background = new JButton("�I����");
		delete = new JButton("�M���e��");
		ButtonHandler buttonHandler = new ButtonHandler();
		foreground.addActionListener(buttonHandler);
		background.addActionListener(buttonHandler);
		delete.addActionListener(buttonHandler);
		panel.add(foreground); //�Nforeground,background,delete�[�ipanel
		panel.add(background);
		panel.add(delete);
		
		location = new JLabel("��Ц�m:");
		add(location,BorderLayout.SOUTH); //��location�����JFrame���U��
		
		MouseHandler mouseHandler = new MouseHandler();
		canvas.addMouseMotionListener(mouseHandler);
		
		
	}
		
	private class RadioButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent event){ //handle radio button event
			JOptionPane.showMessageDialog(LittleDrawer.this,String.format("�A�I��F:%s",event.getActionCommand()));
		}
	}
	
	private class CheckBoxHandler implements ItemListener
	{
		public void itemStateChanged(ItemEvent event)//handle checkBox event
		{
			if(full.isSelected())
			  System.out.println("�A��ܤF��");
			else
			  System.out.println("�A�����F��");	
		}
	}
		
	private class ButtonHandler implements ActionListener 
	   {
	      public void actionPerformed(ActionEvent event)//handle button event
	      {
	         JOptionPane.showMessageDialog(LittleDrawer.this, String.format(
	            "�A�I��F:%s", event.getActionCommand()));
	      }
	   } 
	private class MouseHandler extends MouseMotionAdapter{ //handle Mouse event
		public void mouseMoved(MouseEvent event){
			location.setText(String.format("��Ц�m:(%d,%d)",event.getX(),event.getY()));
		}
	
	}
	
}
