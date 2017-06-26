package serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import service.IOService;

public  class IOServiceImpl implements IOService{
	@Override
	public boolean writeFile(String file, String userId, String fileName,String time) {
            
		File f = new File(userId + "_" + fileName+"_"+time);
		try {
			FileWriter fw = new FileWriter(f, false);
			fw.write(file);
			fw.flush();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
        @Override
	public String readFile(String userId, String fileName,String time) {
		String result = "";
                String line  = "";
                File f = new File(userId + "_" + fileName);
                try{
                    FileReader fileReader = new FileReader(f);
                    BufferedReader reader = new BufferedReader(fileReader);
                    try{
                        while((line = reader.readLine())!=null){
                            result+=line;
                        }
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
                }catch(FileNotFoundException ex){
                    ex.printStackTrace();
                }
		return result;
	}

	@Override
	public String readFileList(String userId) {
                String result = "";
		File file = new File("/Users/py/workspace/SE/BFServer");
                if(file.exists()==false){
                    System.out.println("No such file!");  
                }
                else{
                    result = find(userId,file);
                }
		return result;
	}
	public static String find(String userId,File file){
            String result = "";
            if(file.isDirectory()){
                File[] fileList = file.listFiles();
                for(File f:fileList){
                    find(userId,f);
                }
            }
            else{
                String name = file.getName();
                if(name.contains(userId+"_")){
                    result = "/"+name;
                }
            }
            return result;
        }
        public boolean deleteFile(String userId,String fileName){
            boolean result = true;
            File findFile = new File("/Users/py/workspace/SE/BFServer");
            if(findFile.exists()==false){
                System.out.println("No such file!");  
            }
            else{
                result = IsDeleted(findFile,fileName,userId);
            }
            return result;
        }
        public static boolean IsDeleted(File file,String fileName,String userId){
            boolean result = false;
            if(file.isDirectory()){
                File[] fileList = file.listFiles();
                for(File f:fileList){
                    IsDeleted(f,fileName,userId);
                }
            }
            else{
                String name = file.getName();
                if(name.contains(userId+"_"+fileName)){
                    File choosenFile = new File(fileName);
                    choosenFile.delete();
                    result = true;
                }
                
            }
            return result;
        }
       
}
