import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Explode {
	int x,y;
	private TankGame tc;
	private boolean live = true;
	public void setLive(boolean live) {
		this.live = live;
	}
	public boolean isLive() {
		return live;
	}
	
	String blow[]= {"images/1.gif","images/3.gif","images/4.gif","images/6.gif","images/7.gif",
		"images/9.gif","images/10.gif","images/8.gif","images/5.gif","images/2.gif"};
	int step=0;

	public Explode(int x,int y,TankGame tc) {
		this.x=x;
		this.y=y;
		this.tc=tc;
	}
//	自定义的画图片的方法
	public void draw(Graphics g){
		if(!live){
		tc.explode.remove(this);
			return;
		}
		if(step==blow.length){
			live=false;
			step=0;
			return;
		}
		g.drawImage(new ImageIcon(blow[step]).getImage(),x+20, y-20, null);
		step++;
		System.out.println(step);
	}
	
}
