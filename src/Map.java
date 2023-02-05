import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
/**
 * 此类是地图类，它可以实现绘画不同的障碍物
 * @author 86150
 *
 */
public class Map {
	int x = (int) (Math.random() * 7);
	int y = (int) (Math.random() * 7);
	int tempx[] = { 0, 100, 200, 200, 400, 500, 600 };
	int tempy[] = { 50, 550, 450, 350, 250, 150, 50 };
	int dx = tempx[x];
	int dy = tempx[y];
	// 定义随机数F，它的变化代表图片的不同
	Random F = new Random();
	int ff = F.nextInt(6);
	Image cp = new ImageIcon("mapimages/cp.jpg").getImage();
	Image zt = new ImageIcon("mapimages/zt.jpg").getImage();
	Image tie = new ImageIcon("mapimages/tie.jpg").getImage();
	// 地图的消失与否
	private boolean live = true;

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

	public void paintmap(Graphics g) {
		if (!live)
			return;
		if (ff <= 1)
			g.drawImage(cp, dx, dy, 49, 49, null);
		if (1 < ff && ff <= 3)
			g.drawImage(zt, dx, dy, 49, 49, null);
		if (ff > 3)
			g.drawImage(tie, dx, dy, 49, 49, null);

	}

	// 得到地图的矩形
	public Rectangle getRect() {
		return new Rectangle(dx, dy, 49, 49);
	}

}