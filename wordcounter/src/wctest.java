import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class wctest {   //单元测试模块，课独立测试各个方法

    private String filename;

    {
        filename = "F:/123/1.txt";    //可更改文件路径
    }

    @Test
    public void totalchartest(){
        int num = wordcounter.charcount(filename);
        System.out.println("总字符数："+num);
    }
    @Test
    public void totalwordtest(){
        int num = wordcounter.wordcount(filename);
        System.out.println("总单词数："+num);
    }
    @Test
    public void totallinetest(){
        int num = wordcounter.linecount(filename);
        System.out.println("总行数："+num);
    }
    @Test
    public void speciallinetest(){
        Special sp = wordcounter.speciallinecount(filename);
        int num1 = sp.getNulllines();
        int num2 = sp.getCodelines();
        int num3 = sp.getCommentlines();
        System.out.println("空行："+num1+"代码行："+num2+"注释行："+num3);
    }
    @Test
    public void traversetest(){
        wordcounter.traverse(filename);
    }
    @Test
    public void getflielisttest(){
        List<String> fileList = new ArrayList<>();
        fileList = wordcounter.getFileList(filename);
        for (String s : fileList) {
            System.out.println(s);
        }
    }

}
