package com.mio.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;

public class gameJFrame extends JFrame implements KeyListener, ActionListener {
    //游戏
    int[][] b=new int[4][4];
    int x=0,y=0;
    String ImagePath="image\\erciyuan\\sakurako\\";
    int step=0;
    int[][] win={
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    JMenuItem remilia=new JMenuItem("蕾米莉亚");
    JMenuItem Sakurako =new JMenuItem("大室樱子");
    JMenuItem solace=new JMenuItem("007-Solace");
    JMenuItem qff=new JMenuItem("Quest For Fire");
    JMenuItem bonus=new JMenuItem("神秘图片");
    JMenuItem restart=new JMenuItem("重新开始");
    JMenuItem reLogin=new JMenuItem("重新登录");
    JMenuItem closeGame=new JMenuItem("关闭游戏");
    JMenuItem connectMe=new JMenuItem("联系作者");
    public gameJFrame() {
        //初始化窗口
        initframe();
        //初始化菜单
        initmenu();
        //打乱图片
        initjigsaw();
        //初始化图片
        initImage();
        //最后设置可见
        this.setVisible(true);
    }
    private void showAll(){
        this.getContentPane().removeAll();
        JLabel ALL=new JLabel(new ImageIcon(ImagePath+"all.png"));
        ALL.setBounds(100,150,600,600);
        this.getContentPane().add(ALL);
        JLabel bg=new JLabel(new ImageIcon("image\\background.png"));
        bg.setBounds(0,50,800,800);
        this.getContentPane().add(bg);
        JLabel st=new JLabel("当前步数："+step);
        st.setBounds(0,-25,100,100);
        this.getContentPane().add(st);
        this.getContentPane().repaint();
    }
    private void initjigsaw(){
        int []a={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random rd=new Random();
        for(int i=0;i<a.length;i++){
            int index=rd.nextInt(a.length),tp=0;
            tp=a[i];
            a[i]=a[index];
            a[index]=tp;
        }
        int cnt=0;
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                b[i][j]=a[cnt++];
                if(b[i][j]==0){
                    x=i;
                    y=j;
                }
            }
        }
    }
    private void initImage() {
        //清除图片
        this.getContentPane().removeAll();
        if(ifwin()){
            JLabel winn=new JLabel(new ImageIcon("image\\win.png"));
            winn.setBounds(300,300,221,145);
            this.getContentPane().add(winn);
        }

        JLabel st=new JLabel("当前步数："+step);
        st.setBounds(0,-25,100,100);
        this.getContentPane().add(st);

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                //创建管理容器
                JLabel jlabel = new JLabel(new ImageIcon(ImagePath+b[i][j]+".png"));
                //设置摆放位置
                jlabel.setBounds(j*150+100,i*150+150,150,150);
                //设置边框
                jlabel.setBorder(new BevelBorder(1));
                //把容器加入frame
                this.getContentPane().add(jlabel);
            }
        }
        JLabel bg=new JLabel(new ImageIcon("image\\background.png"));
        bg.setBounds(0,50,800,800);
        this.getContentPane().add(bg);
        //刷新
        this.getContentPane().repaint();
    }
    private void initframe(){
        this.setSize(800,870);
        this.setTitle("拼图by akariza v1.0");
        //置顶
        this.setAlwaysOnTop(true);
        //居中
        this.setLocationRelativeTo(null);
        //x关闭
        this.setDefaultCloseOperation(3);
        //取消组件默认居中放置
        this.setLayout(null);
        //键盘监听
        this.addKeyListener(this);
    }
    private void initmenu(){
        //栏目与菜单
        JMenuBar jmenubar=new JMenuBar();
        JMenu functions=new JMenu("功能");
        JMenu changePicture=new JMenu("更换图片");
        JMenu albums=new JMenu("专辑封面");
        JMenu erciyuan=new JMenu("二次元");
        JMenu aboutUs=new JMenu("关于");


        JMenu help=new JMenu("游玩方式");
        JMenuItem helphelp=new JMenuItem("上下左右键移动拼图，按w直接胜利，按s展示完整图片，按m探寻边界");
        functions.add(changePicture);
        //把jmenuitem加入jmenu
        functions.add(restart);
        functions.add(reLogin);
        functions.add(closeGame);
        changePicture.add(albums);
        albums.add(solace);
        albums.add(qff);
        changePicture.add(erciyuan);
        erciyuan.add(remilia);
        erciyuan.add(Sakurako);
        changePicture.add(bonus);
        aboutUs.add(connectMe);

        help.add(helphelp);

        restart.addActionListener(this);
        reLogin.addActionListener(this);
        closeGame.addActionListener(this);
        connectMe.addActionListener(this);
        solace.addActionListener(this);
        qff.addActionListener(this);
        remilia.addActionListener(this);
        Sakurako.addActionListener(this);
        bonus.addActionListener(this);

        //把jmenu加入jmenubar
        jmenubar.add(functions);
        jmenubar.add(aboutUs);
        jmenubar.add(help);
        //把jmenubar加入到frame
        this.setJMenuBar(jmenubar);
    }
    private void showMe(){
        JDialog connectme=new JDialog();
        JLabel cm=new JLabel(new ImageIcon("image\\composer.png"));
        cm.setBounds(0,0,400,400);
        connectme.getContentPane().add(cm);
        connectme.setSize(400,400);
        connectme.setAlwaysOnTop(true);
        connectme.setLocationRelativeTo(null);
        connectme.setTitle("请用以下方式联系我");
        connectme.setModal(true);
        connectme.setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(ifwin())return;
        int code=e.getKeyCode();
        if(code==83){
            showAll();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(ifwin())return;
        int code=e.getKeyCode();
        if(code==37){
            //左
            if(y==3)return;
            b[x][y]=b[x][y+1];
            b[x][y+1]=0;
            y++;
            step++;
            initImage();
        }else if(code==38){
            //上
            if(x==3)return;
            b[x][y]=b[x+1][y];
            b[x+1][y]=0;
            x++;
            step++;
            initImage();
        }else if(code==39){
            //右
            if(y==0)return;
            b[x][y]=b[x][y-1];
            b[x][y-1]=0;
            y--;
            step++;
            initImage();
        }else if(code==40){
            //下
            if(x==0)return;
            b[x][y]=b[x-1][y];
            b[x-1][y]=0;
            x--;
            step++;
            initImage();
        }else if(code==83){
            initImage();
        }else if(code==87){
            b=new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            x=3;
            y=3;
            initImage();
        }else if(code==77){
            while(true) {
                JDialog black = new JDialog();
                JLabel bp = new JLabel(
                        "抑制黑波动的源泉" +
                        "抑制黑波动的源泉" +
                        "抑制黑波动的源泉" +
                        "抑制黑波动的源泉" +
                        "抑制黑波动的源泉" +
                        "抑制黑波动的源泉");
                bp.setBounds(0, 0, 500, 50);
                black.getContentPane().add(bp);
                black.setSize(500, 100);
                black.setAlwaysOnTop(true);
                black.setLocationRelativeTo(null);
                black.setTitle("黒波の源泉を抑える");
                black.setModal(true);
                black.setVisible(true);
            }
        }
    }
    public boolean ifwin(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                if(b[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object tp=e.getSource();
        if(tp==restart){
            step=0;
            initjigsaw();
            initImage();
        }else if(tp==reLogin){
            this.setVisible(false);
            new loginJFrame();
        }else if(tp==closeGame){
            System.exit(0);
        }else if(tp==connectMe){
            showMe();
        }else if(tp==solace){
            ImagePath="image\\album\\Solace\\";
            step=0;
            initjigsaw();
            initImage();
        }else if(tp==qff){
            ImagePath="image\\album\\Questforfire\\";
            step=0;
            initjigsaw();
            initImage();
        }else if(tp==remilia){
            ImagePath="image\\erciyuan\\remilia\\";
            step=0;
            initjigsaw();
            initImage();
        }else if(tp==Sakurako){
            ImagePath="image\\erciyuan\\sakurako\\";
            step=0;
            initjigsaw();
            initImage();
        }else if(tp==bonus){
            ImagePath="image\\bonus\\Create\\";
            step=0;
            initjigsaw();
            initImage();
        }
    }
}
