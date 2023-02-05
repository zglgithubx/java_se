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
 *��������Ϸ��ʼ�࣬ʵ���߳̿����ػ�ʱ��ʹ�����ת��ʵ��������
 */
public class TankGame extends JPanel implements KeyListener{
	private static final long serialVersionUID = 1L;
	//�������������
	JFrame jfr;
	TankType owntank;
	Map map;
	EnemyType dtank;
	TankBullet tc;
	int score;  
	//ʵ�����̶߳���
	PaintThread paintThread = new PaintThread();
	//ʵ����������
	ArrayList<EnemyType> dtankArrayList = new ArrayList<>(); // ʵ�����з�̹����������
	ArrayList<TankBullet> buttletList = new ArrayList<>();   // ʵ�����ӵ���������
	ArrayList<Map> mapList=new ArrayList<>();                //ʵ������ͼ����
	ArrayList<Explode> explode=new ArrayList<>();            //ʵ������ը����
	/**
	 * 
	 * ���췽��
	 * 
	 */
	public TankGame() {
		jfr=new JFrame("��Ϸ��");
		this.setBackground(Color.BLACK);
		jfr.add(this);
		jfr.setLayout(null);
        jfr.addWindowListener(new  window());
        jfr.setSize(665,687);
        //ʹ���ھ���
        jfr.setLocationRelativeTo(null);
        jfr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// �رմ����ͷ���Դ
        jfr.setResizable(false);
        jfr.setVisible(true);
        jfr.addKeyListener(this);                            //�ڴ���ע����̼���
        this.setLayout(null);
		this.setBounds(0, 0, 650, 650);
		//ʵ�����ҷ�̹�˲���ʵ��
		owntank=new TankType(300,300,1);
		//�����������̹��
		for(int i=0;i<=20;i++) {
			dtankArrayList.add(new EnemyType());
		}
		//����������ӵ�ͼ
		for(int i=mapList.size();i<=36;i++) {
			mapList.add(new Map());
		}
		//�߳̿�ʼ
		new Thread(paintThread).start();   
	}
	public <E> E eliminate(Class<E> obj) throws IllegalAccessException, InstantiationException {
		E e=obj.newInstance();
		return e;
	}
	/**
	 * ���ʹ���
	 * 
	 */
	public void paint(Graphics g) {
		super.paint(g);                                       //����֮ǰ��ͼƬ
		System.out.println("ִ����paint");
		//�û�������ӵ�������
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
		//�ж�̹�������ٽ��е��û���
		if(owntank.isLive()) {
			owntank.paint(g); 
		}
		//�ҷ�̹��ײ�ϵ�ͼ
		owntank.bumpmap(mapList);
		//�û�����ӵ�ͼ������
		for(int i=0;i<mapList.size();i++) {
			Map e=mapList.get(i);
			if(!e.isLive()) mapList.remove(e);
			e.paintmap(g); 
		}
		//��ӵз�̹�˵�����
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
		//���÷�
		g.setColor(Color.yellow);
		g.drawString("������"+score,10,20);
		//�����ҷ�̹�������͵÷��ж���Ϸ�Ƿ����,2�����˳���1��������һ��
		if (!owntank.isLive()) {
			int n = JOptionPane.showOptionDialog(this, "YOU LOST!\n"+"��ĵ÷��ǣ�" + score + "��", "��Ϸ����",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "�������˵�", "����һ��", "�˳���Ϸ" }, null);
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
			int n = JOptionPane.showOptionDialog(this, "YOU WIN!\n"+"�õ���  �֣�" + score + "��", "��Ϸ����",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
					new Object[] { "�������˵�", "����һ��", "�˳���Ϸ" }, null);
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
	//�õ����õķ���
	public int getScore() {
		return score;
	}
	//�������õķ���
	public void setScore(int score) {
		this.score=score;
	}
	//���̼���
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		owntank.KeyPressed(e);// ���°����ƶ�
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		owntank.KeyReleased(e);
		// ����f�����ӵ�
		if (e.getKeyCode() == KeyEvent.VK_F) {             
			TankBullet zidanshili = new TankBullet(owntank.getTank_x()+15,owntank.getTank_y()+15,owntank.direction,this);// �����ӵ�λ��
			buttletList.add(zidanshili);
		 
	    }
		
	}
	public void keyTyped(KeyEvent e) {
	}
	//�����¼��̳д����¼���������
	class  window  extends  WindowAdapter  {
         public  void  windowClosing(WindowEvent  e)    {
                 int  clo=JOptionPane.showConfirmDialog(jfr,"��Ҫ�˳���Ϸ������Ϸ��ʼ������","�˳���Ϸ",JOptionPane.YES_NO_OPTION);
                 if(clo==JOptionPane.YES_OPTION)  {
                         jfr.dispose();
                         new  TankStart();
                 }
         }
    }
	//����˽���ڲ��� ��Runnable�ӿ�ʵ���߳���
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
