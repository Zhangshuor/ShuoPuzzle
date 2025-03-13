package com.suresure.ui;

import javax.swing.*;

public class RegisterJFrame extends JFrame {
    public RegisterJFrame() {
        this.setSize(488,500);
        //设置界面标题
        this.setTitle("硕硕拼图注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置弹出位置
        this.setLocationRelativeTo(null);
        //设置游戏的关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //让界面显示出来
        this.setVisible(true);
    }
}
