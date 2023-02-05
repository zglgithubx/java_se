import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 * 
 * @author 86150 此类是敌方坦克类，主要包括坦克移动，坦克的随机出现，敌方坦克撞墙，敌方坦克之间的碰撞，敌方坦克和我方坦克的碰撞检测等方法。
 */
public class EnemyType {

	int[] temp = { 650, 550, 450, 350, 250, 150, 50 };
	int[] temp1 = { 0, 100, 200, 300, 400, 500, 600 };
	int x = (int) (Math.random() *7);
	int y = (int) (Math.random() * 7);
	int dtank_x = temp[x];
	int dtank_y = temp1[y];
	int dspeed = 3;
	Random F = new Random();
	int a = (int) (Math.random() * 4);
	int step, ff;
	String dt[][] = { { "dtimages/dt1U.png", "dtimages/dt1D.png", "dtimages/dt1R.png", "dtimages/dt1L.png" },
			{ "dtimages/dt2U.png", "dtimages/dt2D.png", "dtimages/dt2R.png", "dtimages/dt2L.png" },
			{ "dtimages/dt3U.png", "dtimages/dt3D.png", "dtimages/dt3R.png", "dtimages/dt3L.png" },
			{ "dtimages/dt4U.png", "dtimages/dt4D.png", "dtimages/dt4R.png", "dtimages/dt4L.png" } };
	Image dtU = new ImageIcon(dt[a][0]).getImage();
	Image dtR = new ImageIcon(dt[a][2]).getImage();
	Image dtL = new ImageIcon(dt[a][3]).getImage();
	Image dtD = new ImageIcon(dt[a][1]).getImage();
	//定义now为初始状态的图片
	Image now = dtD;
	// 声明私有数据成员oldx，oldy为前一步的位置
	private int oldx, oldy;

	// 敌方生死
	private boolean live = true;

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}
	//敌方坦克构造器
	public EnemyType() {
		dtank_x += dspeed;
		dtank_y += dspeed;
		this.oldx = dtank_x;
		this.oldy = dtank_y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtank_x, dtank_y);
	}

	//画笔画敌方坦克
	public void enemytankpaint(Graphics g) {
		if (!live)
			return;

		g.drawImage(this.now, dtank_x, dtank_y, 40, 40, null);
		dtmove();
	}
	
	public void dtmove() {
		this.oldx = dtank_x;
		this.oldy = dtank_y;
		//声明一个数组数字代表方向
		int dirs[] = { 0, 1, 2, 3, 4 };
		if (step == 0) {
			step = F.nextInt(30) + 5;
			int rn = F.nextInt(dirs.length);
			ff = dirs[rn];
		}
		step--;
		switch (ff) {
		//0代表Up，1代表down，2代表left，3代表right
		case 0:
			if (dtank_y > 0) {
				now = dtU;
				dtank_y -= dspeed;
			} else {
				now = dtD;
				dtank_y += dspeed;
			}
			break;
		case 1:
			if (dtank_y < 600) {
				now = dtD;
				dtank_y += dspeed;
			} else {
				now = dtU;
				dtank_y -= dspeed;
			}
			break;
		case 2:
			if (dtank_x > 0) {
				now = dtL;
				dtank_x -= dspeed;
			} else {
				now = dtR;
				dtank_x += dspeed;
			}
			break;
		case 3:
			if (dtank_x < 600) {
				now = dtR;
				dtank_x += dspeed;
			} else {
				now = dtL;
				dtank_x -= dspeed;
			}
			break;
		 }
		if (ff == 4) {
			dtank_x += 0;
			dtank_y += 0;
		}

	}
	//定义坦克撞墙后停止的方法
	private void stay() {
		dtank_x = oldx;
		dtank_y = oldy;
	}
	// 得到敌人矩形
	public Rectangle getRect() {
		return new Rectangle(dtank_x, dtank_y, 40, 40);
	}

	// 敌方坦克撞墙
	public boolean bumpmap(ArrayList<Map> mm) {
		for (int i = 0; i < mm.size(); i++) {
			if (this.getRect().intersects(mm.get(i).getRect())) {
				this.stay();
				return true;
			}
		}
		return false;
	}

	// 敌方坦克之间的碰撞
	public boolean bumpdtank(ArrayList<EnemyType> tk) {
		for (int i = 0; i < tk.size(); i++) {
			EnemyType t = tk.get(i);
			if (this != t) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();
					return true;
				}
			}
		
		}
		return false;
	}

	// 敌方和我方的碰撞
	public boolean bumptank(TankType mytank) {
		if (this.live && mytank.isLive() && this.getRect().intersects(mytank.getRect())) {
			mytank.setLive(false);
			return true;
		}
		return false;
	}
}
