import javax.swing.*;
import  java.awt.*;
import  java.awt.event.ActionEvent;
import  java.awt.event.ActionListener;
/**
 * @author ����
 *��������Ϸ�����˵���
 */
public class TankStart extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	JFrame jf;                                              //����jf��
	ImageIcon fm ;                                          //����ͼƬ��
	JButton stat,help,tuchu;                                //������ť��
	Image im ;                                              //����ͼ����
	JLabel jlab ;                                        //������ǩ��
	public TankStart() {                                    //���췽����Ӱ�ť�ͱ���
		fm=new ImageIcon("����.jpg");                 // �½���������
		im = (new ImageIcon("��ӥ.jpg")).getImage();          // ���ͼ��
		jlab = new JLabel(fm);
		jf=new JFrame("ʷ������̹�˴�ս");
		jf.setLayout(null);
		this.setBackground(Color.WHITE);
		//��Ӱ�ť
		stat=new JButton("��ʼ��Ϸ");
		stat.setBounds(300, 360, 100, 20);
		stat.setBorderPainted(false); // ����ʾ��ť�߿�
		stat.setToolTipText("�ֵܣ�������һ��");
		stat.addActionListener(this);
		help=new JButton("��Ϸ����");
		help.setToolTipText("���ֱؿ���");
		help.setBounds(300, 460, 100, 20);
		help.setBorderPainted(false); // ����ʾ��ť�߿�
		help.addActionListener(this);
		tuchu=new JButton("�˳���Ϸ");
		tuchu.setBounds(300,560,100,20);
		tuchu.setBorderPainted(false); // ����ʾ��ť�߿�
		tuchu.setToolTipText("������");
		tuchu.addActionListener(this);
		//����ťע�ᵽ����
		jf.add(stat);
		jf.add(help);
		jf.add(tuchu);
		jf.add(this);
		//���ñ�ǩ����
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
	
	public static void main(String[] args) {           //main����
		new TankStart();
	}
	@Override
	public void actionPerformed(ActionEvent e) {        
		// TODO Auto-generated method stub
		  if  (e.getSource()  ==  stat){               //����¼�Դ����stat��ť����ʾTankgame����
              jf.dispose();
              new TankGame();
		  }
		  else if (e.getSource() == help) { 
			 JOptionPane.showMessageDialog(jf, "������������̹���ƶ���F����������з�̹�ˣ������㽫�ᱻ����","��Ϸ����",JOptionPane.INFORMATION_MESSAGE);
		  }
		  else if(e.getSource()==tuchu) {
			  int clo=JOptionPane.showConfirmDialog(jf," ����������ľ���Ҫ�뿪��","�˳���Ϸ",JOptionPane.YES_NO_OPTION);
			  	if(clo==JOptionPane.YES_OPTION) {
					System.exit(0);
			    }
		  }
	}
}
