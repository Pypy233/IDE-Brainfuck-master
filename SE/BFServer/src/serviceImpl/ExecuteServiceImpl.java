//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import java.util.ArrayList;
public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
     * @throws java.rmi.RemoteException
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
            String result = "";
		int [] num = new int[10000];
		char[] arrCode = code.toCharArray();
		char[] arrParam = param.toCharArray();
		int pointer = 0;
		int j = 0;
		
		for(int i = 0;i<arrCode.length;i++){
			switch(arrCode[i]){
			case('>'):
			pointer++;
			break;
			case('<'):
			pointer--;
			break;
			case('+'):
			num[pointer]++;
			break;
			case('-'):
			num[pointer]--;
			break;
			case('.'):
			result = result+(char)(num[pointer]);
			break;
			case(','):
			num[pointer] = (int)(arrParam[j]);
			j++;
			break;
			case('['):
				if((num[pointer])==0){
					i =findRight(arrCode,i);					
				}
			break;
			case(']'):
				if((num[pointer])!=0){
					i = findLeft(arrCode,i);
					
				}
			break;
			}
		}
                if(code.contains("Ook")){
                   return OokCompiler(code,param);
                    
                }

		return result;
	}

	public int findRight(char[] arrCode, int i){
		ArrayList<Character> code = new ArrayList<Character>();	
		int result = 0;
		for(char e:arrCode){
			code.add(e);
		}
		for(int m=i;m<arrCode.length;m++){
			if((arrCode[m]==']')){
				if(code.subList(i+1, m).contains('[')){
					i=m;
				}
				else{
					result = m;
					break;
				}
			}
		}

		return result;
	}
	
	public int findLeft(char[] arrCode,int i){
		ArrayList<Character> code = new ArrayList<Character>();	
		int result = 0;
		for(char e:arrCode){
			code.add(e);
		}
		for(int m=i;m>=0;m--){
			if((arrCode[m]=='[')){
				if(code.subList(m, i).contains(']')){
					i = m;
				}
				else{
					result = m;
					break;
				}
			}
		}
		return result;
	}
       public  String OokCompiler(String code,String param) throws RemoteException{
           String result = "";
		ExecuteServiceImpl bf = new ExecuteServiceImpl();
		String newCode = "";
		for(int i = 0; i < code.length() - 10; i = i + 10){
			switch(code.substring(i,i+10)){
			case("Ook. Ook? "): 
				newCode+=">";
				break;
			case("Ook? Ook. "):
				newCode+="<";
				break;
			case("Ook. Ook. "):
				newCode+="+";
				break;
			case("Ook! Ook! "):
				newCode+="-";
				break;
			case("Ook! Ook. "):
				newCode+=".";
				break;
			case("Ook. Ook! "):
				newCode+=",";
				break;
			case("Ook! Ook? "):
				newCode+="[";
				break;
			case("Ook? Ook! "):
				newCode+="]";
				break;
			}
		}
		newCode+=".";
		result = bf.execute(newCode, param);
		return result;
       }
       public static void main(String[] args) throws RemoteException{
           ExecuteServiceImpl ex = new ExecuteServiceImpl();
           String result1 = ex.execute(",+++++.", "A");
           String result2 = ex.execute("Ook. Ook! Ook. Ook? Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. Ook. "
                   + "Ook. Ook! Ook? Ook? Ook. Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook! Ook. O"
                   + "ok? Ook! Ook! Ook? Ook! Ook. Ook! Ook. Ook! Ook!"
                   + " Ook? Ook? Ook. Ook. Ook. Ook. Ook? Ook! Ook! Ook? Ook! Ook? Ook. Ook! Ook. ","4+3");
           System.out.println(result1);
          System.out.print(result2);
       }


}
