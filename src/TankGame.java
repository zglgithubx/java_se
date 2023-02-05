import  javax.swing.*;
import  java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import  java.awt.event.WindowAdapter;
import  java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author 86150
 *此类是游戏开始类，实现线程控制重画时间和窗口跳转，实例化容器
 */
public class TankGame extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	//声明类对象名称
	JFrame jfr;
	TankType owntank;
	Map map;
	EnemyType dtank;
	TankBullet tc;
	int score;  
	//实例化线程对象
	PaintThread paintThread = new PaintThread();
	//实例化容器类
	ArrayList<EnemyType> dtankArrayList = new ArrayList<>(); // 实例化敌方坦克容器容器
	ArrayList<TankBullet> buttletList = new ArrayList<>();   // 实例化子弹容器容器
	ArrayList<Map> mapList=new ArrayList<>();                //实例化地图容器
	ArrayList<Explode> explode=new ArrayList<>();            //实例化爆炸容器
	/**
	 * 
	 * 构造方法
	 * 
	 */
	public TankGame() {
		jfr=new JFrame("游戏中");
		this.setBackground(Color.BLACK);
		jfr.add(this);
		jfr.setLayout(null);
        jfr.addWindowListener(new  window());
        jfr.setSize(665,687);
        //使窗口居中
        jfr.setLocationRelativeTo(null);
        jfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 关闭窗体释放资源
        jfr.setResizable(false);
        jfr.setVisible(true);
        jfr.addKeyListener(this);                            //在窗口注册键盘监听
        this.setLayout(null);
		this.setBounds(0, 0, 650, 650);
		//实例化我方坦克并赋实参
		owntank=new TankType(300,300,1);
		//在容器里添加坦克
		for(int i=0;i<=20;i++) {
			dtankArrayList.add(new EnemyType());
		}
		//在容器中添加地图
		for(int i=mapList.size();i<=36;i++) {
			mapList.add(new Map());
		}
		//线程开始
		new Thread(paintThread).start();   
	}
	public <E> E eliminate(Class<E> obj) throws IllegalAccessException, InstantiationException {
		E e=obj.newInstance();
		return e;
	}
	/**
	 * 画笔工具
	 * 
	 */
	public void paint(Graphics g) {
		super.paint(g);                                       //擦除之前的图片
		System.out.println("执行了paint");
		//用画笔添加子弹到容器
		for(int i=0;i<buttletList.size();i++) {
			TankBullet e= buttletList.get(i);
			if(!e.isLive()) buttletList.remove(e);
			e.hitdtanks(dtankArrayList);
			e.hitdmap(mapList);
			if(!owntank.isLive()) {
				buttletList.remove(e);
			}
			else e.draw(g);
		}
		//判段坦克生死再进行调用画笔
		if(owntank.isLive()) {
			owntank.paint(g); 
		}
		//我方坦克撞上地图
		owntank.bumpmap(mapList);
		//用画笔添加地图到容器
		for(int i=0;i<mapList.size();i++) {
			Map e=mapList.get(i);
			if(!e.isLive()) mapList.remove(e);
			e.paintmap(g); 
		}
		//添加敌方坦克到容器
		for(int i=0;i<dtankArrayList.size();i++) {
			EnemyType e= dtankArrayList.get(i);
			e.bumpmap(mapList);
			e.bumpdtank(dtankArrayList);
			e.bumptank(owntank);
			e.enemytankpaint(g);
		}
		for(int i=0;i<explode.size();i++) {
			Explode e=explode.get(i);
			e.draw(g);
		}
		//画得分
		g.setColor(Color.yellow);
		g.drawString("分数："+score,10,20);
		//根据我方坦克生死和得分判断游戏是否结束,2代表退出，1代表再来一局
		if (!owntank.isLive()) {
			int n = JOptionPane.showOptionDialog(this, "YOU LOST!\n"+"你的得分是：" + score + "分", "游戏结束",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "返回主菜单", "再来一局", "退出游戏" }, null);
			if (n == 2) {
				System.exit(0);
			} else if (n == 1) {
			    jfr.dispose();
				new TankGame();
			} else {
				jfr.dispose();
				new TankStart();
			}
			paintThread.pause();
		}else if (score>=100) {
			paintThread.pause();
			int n = JOptionPane.showOptionDialog(this, "YOU WIN!\n"+"得到高  分：" + score + "分", "游戏结束",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "返回主菜单", "再来一局", "退出游戏" }, null);
			if (n == 2) {
				System.exit(0);
			} else if (n == 1) {
			    jfr.dispose();
				new TankGame();
			} else {
				jfr.dispose();
				new TankStart();
			}
		}
		
	}
	//拿到所得的分数
	public int getScore() {
		return score;
	}
	//设置所得的分数
	public void setScore(int score) {
		this.score=score;
	}
	//键盘监听
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		owntank.KeyPressed(e);// 按下按键移动
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		owntank.KeyReleased(e);
		// 按下f发射子弹
		if (e.getKeyCode() == KeyEvent.VK_F) {             
			TankBullet zidanshili = new TankBullet(owntank.getTank_x()+15,owntank.getTank_y()+15,owntank.direction,this);// 发射子弹位置
			buttletList.add(zidanshili);
		 
	    }
		
	}
	public void keyTyped(KeyEvent e) {
	}
	//处理事件继承窗口事件适配器类
	class  window  extends  WindowAdapter  {
         public  void  windowClosing(WindowEvent  e)    {
                 int  clo=JOptionPane.showConfirmDialog(jfr,"你要退出游戏返回游戏开始界面吗？","退出游戏",JOptionPane.YES_NO_OPTION);
                 if(clo==JOptionPane.YES_OPTION)  {
                         jfr.dispose();
                         new  TankStart();
                 }
         }
    }
	//定义私有内部类 用Runnable接口实现线程类
   private class PaintThread implements Runnable {
		private boolean pause = false;
		public void run() {
			while (owntank.isLive()) {
				if (pause) continue;
				else repaint();
				try {
					Thread.sleep(15);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		public void pause() {
			this.pause = true;
		}
	}
}
