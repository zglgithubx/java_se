import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * �������ҷ�̹���࣬ʵ����̹�˵��ĸ������ƶ�����ײ���
 * 
 * @author 86150
 *
 */
public class TankType {
	int tank_x, tank_y, speed;
	// ��̹�˵�״̬��ö�ٷ��г�
	boolean dir_up, dir_down, dir_left, dir_right, kaihuo;
	// ���̹��ͼƬ
	Image tankU = new ImageIcon("images/tankU.png").getImage();
	Image tankD = new ImageIcon("images/tankD.png").getImage();
	Image tankR = new ImageIcon("images/tankR.png").getImage();
	Image tankL = new ImageIcon("images/tankL.png").getImage();
	Image nowImg = tankU;
	// ����һ���ַ������͵ı���
	String direction = "UP";
	// �ҷ�̹�˵Ĵ���
	private boolean live = true;

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	// ��¼��һ����̹������
	private int oldx, oldy;

	// �ҷ�̹�˹�����������
	public TankType(int tank_x, int tank_y, int speed) {
		this.tank_x = tank_x;
		this.tank_y = tank_y;
		this.speed = speed;
		this.oldx = tank_x;
		this.oldy = tank_y;

	}

	// ����̹��
	public void paint(Graphics g) {
		if (!live)
			return;
		g.drawImage(this.nowImg, tank_x, tank_y, 40, 40, null);
		move();
	}

	// �õ�̹�˵�λ������,�ӵ��ཫ�����
	public int getTank_x() {
		return tank_x;
	}

	public int getTank_y() {
		return tank_y;
	}

	// ̹���ƶ���ʽmove����
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

	// ���̼�������������
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

	// �����ɿ�
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

	// �õ��ҷ�̹�˾���
	public Rectangle getRect() {
		return new Rectangle(tank_x, tank_y, 40, 40);
	}

	// ̹��ײǽ
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

