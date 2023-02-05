import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * 此类是我方坦克类，实现了坦克的四个方向移动和碰撞检测
 * 
 * @author 86150
 *
 */
public class TankType {
	int tank_x, tank_y, speed;
	// 把坦克的状态用枚举法列出
	boolean dir_up, dir_down, dir_left, dir_right, kaihuo;
	// 添加坦克图片
	Image tankU = new ImageIcon("images/tankU.png").getImage();
	Image tankD = new ImageIcon("images/tankD.png").getImage();
	Image tankR = new ImageIcon("images/tankR.png").getImage();
	Image tankL = new ImageIcon("images/tankL.png").getImage();
	Image nowImg = tankU;
	// 定义一个字符串类型的变量
	String direction = "UP";
	// 我方坦克的存亡
	private boolean live = true;

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	// 记录上一步的坦克坐标
	private int oldx, oldy;

	// 我方坦克构造器并传参
	public TankType(int tank_x, int tank_y, int speed) {
		this.tank_x = tank_x;
		this.tank_y = tank_y;
		this.speed = speed;
		this.oldx = tank_x;
		this.oldy = tank_y;

	}

	// 画出坦克
	public void paint(Graphics g) {
		if (!live)
			return;
		g.drawImage(this.nowImg, tank_x, tank_y, 40, 40, null);
		move();
	}

	// 得到坦克的位置坐标,子弹类将会调用
	public int getTank_x() {
		return tank_x;
	}

	public int getTank_y() {
		return tank_y;
	}

	// 坦克移动方式move方法
	public void move() {
		this.oldx = tank_x;
		this.oldy = tank_y;

		if (dir_up && (int) tank_y > 0) {
			nowImg = tankU;
			this.direction = "UP";
			tank_y -= speed;

		} else if (dir_down && (int) tank_y < 600) {
			nowImg = tankD;
			this.direction = "DOWN";
			tank_y += speed;

		} else if (dir_left && (int) tank_x > 0) {
			nowImg = tankL;
			this.direction = "LEFT";
			tank_x -= speed;

		} else if (dir_right && (int) tank_x < 600) {
			nowImg = tankR;
			this.direction = "RIGHT";
			tank_x += speed;

		}

		else {
			tank_x += 0;
			tank_y += 0;
		}

	}

	private void stay() {
		tank_x = oldx;
		tank_y = oldy;
	}

	// 键盘监听，键被按下
	public void KeyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			dir_up = true;
			break;
		case KeyEvent.VK_DOWN:
			dir_down = true;
			break;
		case KeyEvent.VK_LEFT:
			dir_left = true;
			break;
		case KeyEvent.VK_RIGHT:
			dir_right = true;
			break;
		case KeyEvent.VK_F:
			kaihuo = false;
			break;
		}
	}

	// 键盘松开
	public void KeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			dir_left = false;
			break;
		case KeyEvent.VK_UP:
			dir_up = false;
			break;
		case KeyEvent.VK_RIGHT:
			dir_right = false;
			break;
		case KeyEvent.VK_DOWN:
			dir_down = false;
			break;
		case KeyEvent.VK_F:
			kaihuo = true;
			break;
		}
	}

	// 得到我方坦克矩形
	public Rectangle getRect() {
		return new Rectangle(tank_x, tank_y, 40, 40);
	}

	// 坦克撞墙
	public boolean bumpmap(ArrayList<Map> mm) {
		for (int i = 0; i < mm.size(); i++) {
			if (this.getRect().intersects(mm.get(i).getRect())) {
				this.stay();
				return true;
			}
		}
		return false;
	}
}

