package com.suresure.ui;

import javax.swing.*;
import java.util.Random;

public class GameJFrame extends JFrame {
    //创建二维数组：用来管理数据，加兹安图片时会根据二维数组数据进行加载
    int[][] data = new int[4][4];

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
                data[i][j] = tempArr[index];
                index++;
            }
        }
    }

    //初始化图片
    private void initImage() {
        int num = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel jLabel = new JLabel(new ImageIcon("image/animal/animal3/" + data[i][j] + ".jpg"));
                jLabel.setBounds(j * 105, i * 105, 105, 105);
                this.getContentPane().add(jLabel);
                num++;
            }
        }
    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项对象 （功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");
        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登陆");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem myGitHubItem = new JMenuItem("我的GitHub");
        //将每一个选项下面的条目添加到选项下面
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(myGitHubItem);

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
    }
}
