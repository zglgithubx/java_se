import javax.swing.*;
import  java.awt.*;
import  java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
/**
 * @author 佚名
 *此类是游戏的主菜单类
 */
public class TankStart extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JFrame jf;                                              //声明jf类
	ImageIcon fm ;                                          //声明图片类
	JButton stat,help,tuchu;                                //声明按钮类
	Image im ;                                              //声明图标类
	JLabel jlab ;                                        //声明标签类
	public TankStart() {                                    //构造方法添加按钮和背景
		fm=new ImageIcon("封面.jpg");                 // 新建背景对象
		im = (new ImageIcon("老鹰.jpg")).getImage();          // 添加图标
		jlab = new JLabel(fm);
		jf=new JFrame("史上最难坦克大战");
		jf.setLayout(null);
		this.setBackground(Color.WHITE);
		//添加按钮
		stat=new JButton("开始游戏");
		stat.setBounds(300, 360, 100, 20);
		stat.setBorderPainted(false); // 不显示按钮边框
		stat.setToolTipText("兄弟，来吧玩一局");
		stat.addActionListener(this);
		help=new JButton("游戏介绍");
		help.setToolTipText("新手必看呦");
		help.setBounds(300, 460, 100, 20);
		help.setBorderPainted(false); // 不显示按钮边框
		help.addActionListener(this);
		tuchu=new JButton("退出游戏");
		tuchu.setBounds(300,560,100,20);
		tuchu.setBorderPainted(false); // 不显示按钮边框
		tuchu.setToolTipText("别点这个");
		tuchu.addActionListener(this);
		//将按钮注册到窗口
		jf.add(stat);
		jf.add(help);
		jf.add(tuchu);
		jf.add(this);
		//设置标签属性
		jlab.setOpaque(true);
		jlab.setSize(700, 700);
		jf.add(jlab);
		jf.setIconImage(im);
		jf.setSize(700,700);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {           //main方法
		new TankStart();
	}
	@Override
	public void actionPerformed(ActionEvent e) {        
		// TODO Auto-generated method stub
		  if  (e.getSource()  ==  stat){               //获得事件源单击stat按钮，显示Tankgame窗口
              jf.dispose();
              new TankGame();
		  }
		  else if (e.getSource() == help) { 
			 JOptionPane.showMessageDialog(jf, "↑↓←→控制坦克移动，F键开火消灭敌方坦克，否者你将会被消灭","游戏帮助",JOptionPane.INFORMATION_MESSAGE);
		  }
		  else if(e.getSource()==tuchu) {
			  int clo=JOptionPane.showConfirmDialog(jf," 你真的真的真的决定要离开吗？","退出游戏",JOptionPane.YES_NO_OPTION);
			  	if(clo==JOptionPane.YES_OPTION) {
					System.exit(0);
			    }
		  }
	}
}
