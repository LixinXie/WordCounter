import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class wordcounter {
    public static void main(String[] args) throws Exception {
        System.out.println("WordCounter!");
        System.out.println("-----------------");
        System.out.println("-c 文件的字符数(不含换行符)");
        System.out.println("-w 文件的词数");
        System.out.println("-l 文件的行数");
        System.out.println("-a 文件的代码行/空行/注释行");
        System.out.println("-s 递归处理目录下符合条件的文件");
        System.out.println("-x 选择进行操作的文件");
        System.out.println("请输入文件名(若将选择-X，请输入null)：");
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.nextLine();
        System.out.println("请选择你的操作：");
        String choice = scanner.next();
        switch(choice){
            case "-c":  int characters = charcount(filename);
                        System.out.println("此文件的字符数为："+characters);
                        break;

            case "-w":  int words = wordcount(filename);
                        System.out.println("此文件的单词数为："+words);
                        break;

            case "-l":  int lines = linecount(filename);
                        System.out.println("此文件的总行数为："+lines);
                        break;

            case "-a":  int nulllines=0;
                        int codelines=0;
                        int commentlines=0;
                        Special sp = speciallinecount(filename);
                        nulllines = sp.getNulllines();
                        codelines = sp.getCodelines();
                        commentlines = sp.getCommentlines();
                        System.out.println("空行数为：" + nulllines);
                        System.out.println("代码行数为" + codelines);
                        System.out.println("注释行数为" + commentlines);
                        break;
            case "-s":  traverse(filename); break;
            case "-x":  guiwc.main(null);    break;
            default: System.out.println("输入操作错误！"); break;
        }

    }
    public static int charcount(String filename){
        //统计字符数，不包含换行
        int characters=0;
        String str = null;
        BufferedReader br = null;
        InputStreamReader reader = null;
        File file = new File(filename);
        try {
            reader = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(reader);
            while((str = br.readLine())!=null){
                characters+=str.length();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(reader!=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return characters;
    }
    public static int wordcount(String filename){
        //统计单词数
        int words=0;
        String str = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            Pattern pattern = Pattern.compile("[a-zA-Z]+");
            while((str=br.readLine())!=null){
                Matcher matcher = pattern.matcher(str);
                while(matcher.find()){
                    words++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return words;
    }
    public static int linecount(String filename){
        //统计总行数
        int lines=0;
        File file = new File(filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while(br.readLine() != null) {
                lines++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }
    public static Special speciallinecount (String filename){
        int nulllines=0;
        int codelines=0;
        int commentlines=0;
        Special sp = new Special();
        String str = null;
        File file = new File(filename);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            while((str=br.readLine())!= null) {
               if(str.length()==0){
                   nulllines++;
               }else{
                   String substr = str.trim();  //去除读到的每行字符串的首尾空格
                   if(substr.equals("{") || substr.equals("}")){
                       nulllines++;
                   } else if(substr.indexOf("//")==0 || substr.indexOf("}//")==0 || substr.indexOf("{//")==0){
                       commentlines++;
                   } else if(substr.indexOf("/*")==0 || substr.indexOf("}/*")==0 || substr.indexOf("{/*")==0){
                       commentlines++;
                       while((str=br.readLine())!= null){
                           commentlines++;
                           if(str.trim().contains("*/"))
                               break;
                       }
                   } else{
                        codelines++;
                   }
               }
            }
            sp.setNulllines(nulllines);
            sp.setCodelines(codelines);
            sp.setCommentlines(commentlines);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sp;
    }


    public static void traverse(String filename) {
        List<String> fileList = new ArrayList<>();
        fileList = getFileList(filename);
        for (String file : fileList) {
            int characters = 0;
            int words = 0;
            int lines = 0;
            int nulllines = 0;
            int codelines = 0;
            int commentlines = 0;
            Special sp = new Special();
            characters = wordcounter.charcount(file);
            words = wordcounter.wordcount(file);
            lines = wordcounter.linecount(file);
            sp = wordcounter.speciallinecount(file);
            nulllines = sp.getNulllines();
            codelines = sp.getCodelines();
            commentlines = sp.getCommentlines();

            System.out.println("文件" + file + "的信息如下：");
            System.out.println("字符总数：" + characters);
            System.out.println("单词总数：" + words);
            System.out.println("总行数：" + lines);
            System.out.println("空行数：" + nulllines);
            System.out.println("代码总行数：" + codelines);
            System.out.println("注释总行数：" + commentlines);
            System.out.println();
        }
    }
        public  static List<String> fileList = new ArrayList<>();   //构建一个全局变量的list来装递归遍历的文件/文件夹

        public  static List<String> getFileList(String path){
            File filepath = new File(path);
            File [] files = filepath.listFiles();
            if(files!=null){
                for(int i=0;i<files.length;i++){
                    String filename = files[i].getName();
                    if(files[i].isDirectory()){
                    getFileList(files[i].getAbsolutePath());
                    }else if(files[i].isFile() && files[i].canRead()){
                        String strfilename = files[i].getAbsolutePath();
                        fileList.add(strfilename);
                    }else{
                        continue;
                    }
                }
            }
            return fileList;
        }
}