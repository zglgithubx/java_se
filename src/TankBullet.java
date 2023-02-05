import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 * 此类是子弹类，实现子弹四个方向发射，实现消灭敌方坦克和障碍物等方法
 * @author 86150
 *
 */
public class TankBullet {
	private TankGame t;
	int x, y;
	public final int zdspeed = 3;
	private String bullet_dir;
	Image ButtletL = new ImageIcon("images/zdL.png").getImage();
	Image ButtletD = new ImageIcon("images/zdD.png").getImage();
	Image ButtletR = new ImageIcon("images/zdR.png").getImage();
	Image ButtletU = new ImageIcon("images/zdU.png").getImage();
	Image kooo = ButtletU;

	// 子弹存在与否
	private boolean live = true;

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	// 子弹的构造方法，传位置，方向等形参
	public TankBullet(int x, int y, String bullet_dir, TankGame t) {
		this.x = x;
		this.y = y;
		this.bullet_dir = bullet_dir;
		this.t = t;
	}

	// 画子弹
	public void draw(Graphics g) {
		if (!live)
			return;
		g.drawImage(this.kooo, x, y, 10, 10, null);
		move();
	}

	// 子弹移动的方法
	public void move() {
		if (this.bullet_dir.equals("UP")) {
			kooo = ButtletU;
			y -= zdspeed;
		} else if (this.bullet_dir.equals("DOWN")) {
			kooo = ButtletD;
			y += zdspeed;
		} else if (this.bullet_dir.equals("RIGHT")) {
			kooo = ButtletR;
			x += zdspeed;
		} else if (this.bullet_dir.equals("LEFT")) {
			kooo = ButtletL;
			x -= zdspeed;
		}
		if (x < 0 || y < 0 || x > 640 || y > 640) {
			live = false;
		}

	}

	// 得到子弹的矩形
	public Rectangle getRect() {
		return new Rectangle(x, y, 10, 10);

	}

	/*
	 * 子弹打坦克的碰撞检测
	 */
	// 子弹打中敌方坦克，撞上返回true.
	public boolean hitdtanks(ArrayList<EnemyType> diren) {
		for (int i = 0; i < diren.size(); i++) {
			if (diren.get(i).isLive() && this.getRect().intersects(diren.get(i).getRect())) {
				this.live = false;
				diren.get(i).setLive(false);
				t.setScore(t.getScore() + 4);
				Explode e=new Explode(x,y,t);
				t.explode.add(e);
				return true;
			}
		}
		return false;
	}

	/*
	 * 子弹打草坪
	 */
	public boolean hitdmap(ArrayList<Map> mm) {
		for (int i = 0; i < mm.size(); i++) {
			if (this.getRect().intersects(mm.get(i).getRect())) {
				this.live = false;
				mm.get(i).setLive(false);
				t.setScore(t.getScore() + 1);  
				Explode e=new Explode(x,y,t);
				t.explode.add(e);
				return true;
			}
		}
		return false;
	}
}
