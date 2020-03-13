import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class guiwc {
    public static void main(String[] args) {
        JFrame jf = new JFrame("WordCounter 请选择文件");    //创建窗体
        JPanel jp = new JPanel();
        JButton jb = new JButton("浏览");

        JFileChooser jfc = new JFileChooser(".");   //设置默认打开当前目录
        class actionlistener implements ActionListener {    //设置鼠标点击监听

            @Override
            public void actionPerformed(ActionEvent e) {    //选择文件
                int find = jfc.showOpenDialog(null);
                if(find==JFileChooser.APPROVE_OPTION){      //执行操作
                    String filepath = jfc.getSelectedFile().getAbsolutePath();
                    int totalchar = wordcounter.charcount(filepath);
                    int totalword = wordcounter.wordcount(filepath);
                    int totalline = wordcounter.linecount(filepath);
                    Special sp = wordcounter.speciallinecount(filepath);
                    int nullline = sp.getNulllines();
                    int codeline = sp.getCodelines();
                    int commentline = sp.getCommentlines();
                    JOptionPane.showMessageDialog(null,"文件"+ filepath +"信息如下：\n"+"总字符数(不含换行符)：" + totalchar +
                            "\n总词数：" + totalword + "\n总行数：" + totalline + "\n空行数：" + nullline +
                            "\n代码总行数：" + codeline + "\n注释总行数：" + commentline);
                }
            }
        }
        //对窗体各个参数进行设置
        jb.addActionListener(new actionlistener());
        jp.add(jb);
        jf.add(jp);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(450,100);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);

    }
}
