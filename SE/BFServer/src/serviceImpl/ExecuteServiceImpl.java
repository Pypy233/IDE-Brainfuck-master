//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;
import java.util.ArrayList;
public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
            if(code.contains(">")||code.contains("<")||code.contains("+")||
                    code.contains("-")||code.contains(".")||code.contains(",")||
                    code.contains("[")||code.contains("]")){
                return BFCompiler(code,param);
            }
            else if(code.contains("Ook")){
                return OokCompiler(code,param);
            }
            return null;
        }
        
        public static String BFCompiler(String code, String param){
		String result = "";
                char[] arrCode = code.toCharArray();
                char[] arrParam = param.toCharArray();
                int[] data = new int[10000];
                int pointer = 0;
                int index = 0;
                for(int i = 0;i<arrCode.length;i++){
                   switch(arrCode[i]){
                       case '>':
                           pointer++;
                           break;
                       case '<':
                           pointer--;
                           break;
                       case '+':
                           data[pointer]++;
                           break;
                       case '-':
                           data[pointer]--;
                           break;
                       case '.':
                           result+=(char)(data[pointer]);
                           break;
                       case ',':
                           data[pointer] = (int)(arrParam[index]);
                           index++;
                           break;
                       case '[':
                           if(data[pointer]==0){
                               i = goPre(arrCode,i);
                           }
                           break;
                       case ']':
                           if(data[pointer]!=0){
                               i = goNext(arrCode,i);
                           }
                           break;
                   } 
                }
		return result;
	}
        
        public static String OokCompiler(String code,String param){
            String result = "";
            String[] temp = code.split("Ook");
            String[] arrCode = new String[temp.length/2];
            char[] arrParam = param.toCharArray();
            int[] data = new int[10000];
            int pointer = 0;
            int index = 0;
            for(int i = 0;i<arrCode.length;i++){
                arrCode[i] = temp[2*i]+temp[2*i+1];
            }
             for(int i = 0;i<arrCode.length;i++){
                   switch(arrCode[i]){
                       case ".?":
                           pointer++;
                           break;
                       case "?.":
                           pointer--;
                           break;
                       case "..":
                           data[pointer]++;
                           break;
                       case "!!":
                           data[pointer]--;
                           break;
                       case "!.":
                           result+=(char)(data[pointer]);
                           break;
                       case ".!":
                           data[pointer] = (int)(arrParam[index]);
                           index++;
                           break;
                       case "!?":
                           if(data[pointer]==0){
                               i = goPre(arrCode,i);
                           }
                           break;
                       case "?!":
                           if(data[pointer]!=0){
                               i = goNext(arrCode,i);
                           }
                           break;
                   } 
                }
            return result;
        }
        
        
        public static int goPre(char[] arrCode,int i){
            int pos = 0;
            ArrayList<Character> list = new ArrayList<>();
            for(char e:arrCode){
                list.add(e);
            }
            for(int j = i;j<arrCode.length;j++){
                if(arrCode[j]==']'){
                    if(list.subList(i+1,j).contains("[")){
                        i = j;
                    }
                    else{
                        pos = j;
                        break;
                    }
                }
            }
            return pos;
        }
        public static int goNext(char[] arrCode,int i){
            int pos = 0;
            ArrayList<Character> list = new ArrayList<>();
            for(char e:arrCode){
                list.add(e);
            }
            for(int j = i;j>arrCode.length;j--){
                if(arrCode[j]==']'){
                    if(list.subList(j,i).contains("[")){
                        i = j;
                    }
                    else{
                        pos = j;
                        break;
                    }
                }
            }
            return pos;
        }
        public static int goPre(String[] arrCode,int i ){
            int pos = 0;
            ArrayList<String> list = new ArrayList<>();
            for(String e:arrCode){
                list.add(e);
            }
            for(int j = i;j<arrCode.length;j++){
                if(arrCode[j]=="?!"){
                    if(list.subList(i+1,j).contains("!?")){
                        i = j;
                    }
                    else{
                        pos = j;
                        break;
                    }
                }
            }
            
            return pos;
        }
        
        
         public static int goNext(String[] arrCode,int i ){
            int pos = 0;
            ArrayList<String> list = new ArrayList<>();
            for(String e:arrCode){
                list.add(e);
            }
            for(int j = i;j>=0;j--){
                if(arrCode[j]=="!?"){
                    if(list.subList(j,i).contains("?!")){
                        i = j;
                    }
                    else{
                        pos = j;
                        break;
                    }
                }
            }
            
            return pos;
        }
         public static void main(String[] args){
             String s1 = "++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.+++++++\n" +
                     "..+++.>++.<<+++++++++++++++.>.+++.------.--------.>+.>.";
             String s2 = "Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook.\n" +
"Ook! Ook. Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook! Ook? Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook?\n" +
"Ook! Ook! Ook? Ook! Ook? Ook. Ook. Ook. Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook. Ook! Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook! Ook. Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook.\n" +
"Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook! Ook? Ook? Ook. Ook. Ook.\n" +
"Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook.\n" +
"Ook? Ook. Ook? Ook. Ook? Ook. Ook? Ook. Ook! Ook. Ook. Ook. Ook. Ook. Ook. Ook.\n" +
"Ook! Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook.\n" +
"Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook!\n" +
"Ook! Ook. Ook. Ook? Ook. Ook? Ook. Ook. Ook! Ook.";
         System.out.println( BFCompiler(s1,"0"));
             
         }

}
