package com.suresure.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    private static final String CATEGORY_ANIMAL = "animal";
    private static final String CATEGORY_GIRL = "girl";
    private static final String CATEGORY_SPORT = "sport";
    //创建二维数组：用来管理数据，加兹安图片时会根据二维数组数据进行加载
    int[][] data = new int[4][4];
    //空白方块在二维数组中的位置
    int x;
    int y;
    String path = getPicFolder(CATEGORY_ANIMAL);
    //统计步数
    int step;

    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    JMenu functionJMenu = new JMenu("功能");
    JMenu aboutJMenu = new JMenu("关于我们");
    JMenu changePictureMenu = new JMenu("更换图片");
    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem myGitHubItem = new JMenuItem("我的GitHub");

    public GameJFrame() {
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();
        //让界面显示出来
        this.setVisible(true);
    }

    //初始化数据
    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tempArr[index] == 0) {
                    x = i;
                    y = j;
                }
                data[i][j] = tempArr[index];
                index++;
            }
        }
    }

    //初始化图片
    private void initImage() {
        //清空原本的所有图片
        this.getContentPane().removeAll();
        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
            this.getContentPane().repaint();
        }
        JLabel stepCount = new JLabel("步数: " + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);
        this.getContentPane().repaint();
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel jLabel = new JLabel(new ImageIcon(path + data[i][j] + ".jpg"));
                jLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                //给图片设置边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
                num++;
            }
        }
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        //把背景图片添加到界面当中
        this.getContentPane().add(background);
        //刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项对象 （功能 关于我们）


        //将每一个选项下面的条目添加到选项下面
        functionJMenu.add(changePictureMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(myGitHubItem);
        changePictureMenu.add(girlItem);
        changePictureMenu.add(animalItem);
        changePictureMenu.add(sportItem);
        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        myGitHubItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        //将菜单里面的两个选项添加到菜单里面
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("硕硕拼图v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置弹出位置
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //取消默认的居中防止
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_V) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            //把背景图片添加到界面当中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //如果胜利了，此方法直接结束，不能再执行下面移动的代码了
        if (victory()) {
            return;
        }

        //左 37 上 38 右 39 下 40
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            if (y < data[y].length - 1) {
                //空白块下方的数字往上移动
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                //调用方法，按最新数字加载图片
                initImage();
                step++;
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            if (x < data[x].length - 1) {
                //空白块下方的数字往上移动
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                //调用方法，按最新数字加载图片
                initImage();
                step++;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (y > 0) {
                //空白块上方的数字往下移动
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                //调用方法，按最新数字加载图片
                initImage();
                step++;
            }

        } else if (keyCode == KeyEvent.VK_DOWN) {
            if (x > 0) {
                //空白块上方的数字往下移动
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                //调用方法，按最新数字加载图片
                initImage();
                step++;
            }
        } else if (keyCode == KeyEvent.VK_V) {
            initImage();
        } else if (keyCode == KeyEvent.VK_W) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }
    }

    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == replayItem) {
            // 重新游戏：重新初始化数据并刷新界面
            step = 0;
            initData();
            initImage();
        } else if (source == reLoginItem) {
            // 重新登陆：这里只做提示，可根据需要实现登陆逻辑
            JOptionPane.showMessageDialog(this, "你确定要重新登陆吗？重新登录后游戏进度将不被保存");
            this.setVisible(false);
            new LoginJFrame();
        } else if (source == closeItem) {
            // 关闭游戏
            JOptionPane.showMessageDialog(this, "你确定要关闭游戏吗？关闭游戏后游戏进度将不被保存");
            System.exit(0);
        } else if (source == myGitHubItem) {
            // 打开 GitHub 链接
            try {
                Desktop.getDesktop().browse(new java.net.URI("https://github.com/Zhangshuor"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }else if (source == girlItem) {
            path = getPicFolder(CATEGORY_GIRL);
            step = 0;
            initData();
            initImage();

        }else if (source == animalItem) {
            path = getPicFolder(CATEGORY_ANIMAL);
            step = 0;
            initData();
            initImage();

        }else if (source == sportItem) {
            path = getPicFolder(CATEGORY_SPORT);
            step = 0;
            initData();
            initImage();
        }
    }

    private String getPicFolder(String category) {
        String rootPath = "image/" + category + "/";
        File folder = new File(rootPath);

        // 检查目录是否存在且有效
        if (!folder.exists() || !folder.isDirectory()) {
            return null;
        }

        // 获取所有子项（仅遍历一层）
        File[] files = folder.listFiles();
        if (files == null) {
            return null;
        }

        // 筛选出子文件夹
        ArrayList<Object> subDirs = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                subDirs.add(file);
            }
        }

        // 若无子文件夹则返回null
        if (subDirs.isEmpty()) {
            return null;
        }

        // 随机选择一个子文件夹
        Random random = new Random();
        File selectedDir = (File) subDirs.get(random.nextInt(subDirs.size()));

        // 拼接路径并返回（确保末尾带斜杠）
        return rootPath + selectedDir.getName() + "/";
    }
}
