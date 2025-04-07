package com.suresure.ui;

import cn.hutool.core.io.FileUtil;
import com.suresure.domain.User;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RegisterJFrame extends JFrame implements MouseListener {
    ArrayList<User> allUsers;

    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField confirmPassword = new JTextField();
    JButton submit = new JButton();
    JButton reset = new JButton();

    public RegisterJFrame(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
        initFrame();
        this.setSize(488, 500);
        initFrame();
        initView();
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == submit) {
            if (username.getText().isEmpty() || password.getText().isEmpty() || confirmPassword.getText().isEmpty()) {
                showDialog("用户名和密码不能为空");
                return;
            }
            if (!password.getText().equals(confirmPassword.getText())) {
                showDialog("两次密码输入不一致");
                return;
            }
            if (!username.getText().matches("[a-zA-Z0-9]{4,16}")) {
                showDialog("用户名不符合规则");
                return;
            }
            if (!password.getText().matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-z])\\S*")) {
                showDialog("密码不符合规则，至少包含一个小写字母，一个数字，长度至少6位");
                return;
            }
            if (containUsername(username.getText())) {
                showDialog("用户名已经存在，请重新输入...");
                return;
            }
            // 注册成功后的处理逻辑
            try {
                // 1. 将用户对象转为字符串格式（username=xxx&password=xxx）
                List<String> userLines = allUsers.stream()
                        .map(user -> "username=" + user.getUsername() + "&password=" + user.getPassword())
                        .collect(Collectors.toList());
                // 2. 获取资源文件路径（自动处理开发/生产环境）
                String resourcePath = Objects.requireNonNull(getClass().getClassLoader().getResource("")).getPath();
                File targetFile = new File(resourcePath, "resource/userinfo.txt");
                // 3. 使用 Hutool 写入文件（自动创建目录）
                FileUtil.writeLines(userLines, targetFile, "UTF-8");
                showDialog("注册成功");
                this.setVisible(false);
                new LoginJFrame();
            } catch (Exception ex) {
                showDialog("保存用户信息失败：" + ex.getMessage());
            }
        } else if (e.getSource() == reset) {
            //清空三个输入框
            username.setText("");
            password.setText("");
            confirmPassword.setText("");
        }
    }

    /**
     * 判断username在集合中是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    public boolean containUsername(String username) {
        for (User allUser : allUsers) {
            if (allUser.getUsername().equals(username)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void initView() {
        //添加注册用户名的文本
        JLabel usernameText = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameText.setBounds(85, 135, 80, 20);

        //添加注册用户名的输入框
        username.setBounds(195, 134, 200, 30);

        //添加注册密码的文本
        JLabel passwordText = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordText.setBounds(97, 193, 70, 20);

        //添加密码输入框
        password.setBounds(195, 195, 200, 30);

        //添加再次输入密码的文本
        JLabel rePasswordText = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        rePasswordText.setBounds(64, 255, 95, 20);

        //添加再次输入密码的输入框
        confirmPassword.setBounds(195, 255, 200, 30);

        //注册的按钮
        submit.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        submit.setBounds(123, 310, 128, 47);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.addMouseListener(this);

        //重置的按钮
        reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        reset.setBounds(256, 310, 128, 47);
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.addMouseListener(this);

        //背景图片
        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);

        this.getContentPane().add(usernameText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(rePasswordText);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(confirmPassword);
        this.getContentPane().add(submit);
        this.getContentPane().add(reset);
        this.getContentPane().add(background);
    }

    private void initFrame() {
        //对自己的界面做一些设置。
        //设置宽高
        setSize(488, 430);
        //设置标题
        setTitle("硕硕拼图 V1.0注册");
        //取消内部默认布局
        setLayout(null);
        //设置关闭模式
        setDefaultCloseOperation(3);
        //设置居中
        setLocationRelativeTo(null);
        //设置置顶
        setAlwaysOnTop(true);
    }

    //只创建一个弹框对象
    JDialog jDialog = new JDialog();

    //因为展示弹框的代码，会被运行多次
    //所以，我们把展示弹框的代码，抽取到一个方法中。以后用到的时候，就不需要写了
    //直接调用就可以了。
    public void showDialog(String content) {
        if (!jDialog.isVisible()) {
            //把弹框中原来的文字给清空掉。
            jDialog.getContentPane().removeAll();
            JLabel jLabel = new JLabel(content);
            jLabel.setBounds(0, 0, 200, 150);
            jDialog.add(jLabel);
            //给弹框设置大小
            jDialog.setSize(200, 150);
            //要把弹框在设置为顶层 -- 置顶效果
            jDialog.setAlwaysOnTop(true);
            //要让jDialog居中
            jDialog.setLocationRelativeTo(null);
            //让弹框
            jDialog.setModal(true);
            //让jDialog显示出来
            jDialog.setVisible(true);
        }
    }


}
