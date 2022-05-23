package com.chansol.jacs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.chansol.jacs.nodes.Node;
import com.chansol.jacs.nodes.SourceUnit;

public class JACS {
	private Vector<String> extractFailList = new Vector<String>();
	private Vector<SourceUnit> extractedSourceUnit = new Vector<SourceUnit>();
	private HashMap<String,HashMap<String,Vector<Node>>> devideByTypes = new HashMap<String,HashMap<String,Vector<Node>>>();
	private HashMap<String,HashMap<Long,Node>> idNodeMap = new HashMap<String,HashMap<Long,Node>>();
	private HashSet<String> folderList = new HashSet<String>();
	private String compilerDir = "solidityCompiler\\";
	private String outputDir = " -o processing\\";
	private String compileOption = " --ast-compact-json ";
	private boolean debug = false;
	
	public JACS(String sourceDir) {
		File rootFolder = new File(sourceDir);
		folderScanner(rootFolder);
		for(String motherDir : folderList) {
			getAST(motherDir);
		}
	}
	
	public JACS(String sourceDir, boolean debug) {
		this.debug = debug;
		File rootFolder = new File(sourceDir);
		folderScanner(rootFolder);
		for(String motherDir : folderList) {
			getAST(motherDir);
		}
	}
	
	private void folderScanner(File nowdir) {
		for(File entity : nowdir.listFiles()) {
			if(entity.isDirectory()) {
				//디렉터리인 경우 재귀
				folderScanner(entity);
			}else if(entity.getName().endsWith(".sol")){
				//솔리디티 파일인 경우
				String ver = versionCheck(entity);
				if(!ver.equals("0.0.0")) {
					compileSol(versionCheck(entity),entity.getParent(),entity.getAbsolutePath());
				}else {
					extractFailList.add(entity.getAbsolutePath());
				}
			}
		}
	}
	
	private String versionCheck(File solFile) {
		boolean extracted = false;
		String version = "0.0.0";
		try {					
			BufferedReader br = new BufferedReader(new FileReader(solFile));
			while(true) {
	            String line = br.readLine();
	            if (line==null) {
	            	break;
	            }else if(line.contains("pragma ")){
	            	//프래그마 파악
	            	boolean isPragma = true;
	            	if(line.contains("//")) {
	            		System.out.println();
	            		if(line.indexOf("//") < line.indexOf("pragma ")) {
	            			isPragma = false;
	            		}
	            	}
	            	if(isPragma) {
	            		String trimmedLine = line.replace(" ","");
    	            	extracted = true;
    	            	int start = trimmedLine.indexOf(".")-1;
    	            	int end = 0;
    	            	int lessThanSign = trimmedLine.indexOf("<");
    	            	int semicolon = trimmedLine.indexOf(";");
    	            	if(lessThanSign > 0 && semicolon > lessThanSign) {
    	            		end = lessThanSign;
    	            	}else {
    	            		end = semicolon;
    	            	}
                		version = trimmedLine.substring(start,end).trim();
    	            	break;
	            	}
	            }
	        }
			
	        br.close();
	        if (!extracted) {
	        	//추출안된 파일
	        	extractFailList.add("pragmaErr : "+solFile.getAbsolutePath());
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return version;
	}
	
	private void compileSol(String ver, String motherDir, String sourceDir) {
		Runtime rt = Runtime.getRuntime();

		try {
			String compileOption;
			if(ver.contains(".8.")||ver.contains(".6.")) {
				compileOption = this.compileOption;
				if(this.debug) {
					System.out.println(compilerDir+ver+".exe"+outputDir+motherDir+compileOption+sourceDir);
				}
				Process p = rt.exec(compilerDir+ver+".exe"+outputDir+motherDir+compileOption+sourceDir);
				
				p.waitFor();
				folderList.add(motherDir);
			}
			else {
				if(this.debug) {
					System.out.println("0.8.x버전과 0.6.x 버전 이외에는 현재 지원하지 않습니다");
				}
				extractFailList.add("versionErr : "+sourceDir);
			}
		    
		} catch (IOException e) {
			extractFailList.add("versionErr(IOE) : "+sourceDir);
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void getAST(String motherDir) {
		try {
			File astDirectory = new File("processing\\"+motherDir);
			if(astDirectory.listFiles() == null) {
				extractFailList.add("fileErr : "+astDirectory.getAbsolutePath());
			}else {
				for(File ast : astDirectory.listFiles()) {
					if(ast.isFile()) {
						JSONParser jsonParser = new JSONParser();
						JSONObject jsonObj = (JSONObject)jsonParser.parse(new FileReader(ast.getAbsolutePath()));
						if(jsonObj.get("nodeType").equals("SourceUnit")) {
							Node.setDebugMode(debug);
							SourceUnit sourceUnit = new SourceUnit(jsonObj);
							extractedSourceUnit.add(sourceUnit);
							devideByTypes.put(ast.getName().substring(0,ast.getName().indexOf(".")), Node.getDevideByTypes());
							idNodeMap.put(ast.getName().substring(0,ast.getName().indexOf(".")), Node.getIdNodeMap());
							if(debug) {
								Node.getIdValidate(jsonObj.toString());
								System.out.println(ast.getName()+" 추출 성공");
							}
						}
					}
				}
			}
		} catch (IOException | ParseException | NullPointerException e) {
			System.out.println("in "+motherDir);
			System.out.println("AST 추출 실패");
			System.out.println("solc 실행이 성공했다면 solidity 코드의 컴파일 오류일 가능성이 있습니다.");
			e.printStackTrace();
		}
	}
	
	public Vector<String> getFailedList() {
		return extractFailList;
	}
	
	public Vector<SourceUnit> getSourceUnits() {
		return extractedSourceUnit;
	}
	
	public Set<String> getSourceNames(){
		return devideByTypes.keySet();
	}
	
	public HashMap<String,Vector<Node>> getAll(String sourceName){
		return devideByTypes.get(sourceName);
	}
	
	public Vector<Node> getAll(String sourceName, String typeName){
		return devideByTypes.get(sourceName).get(typeName);
	}
	public Node searchByID(String sourceName, long id) {
		return idNodeMap.get(sourceName).get(id);
	}
}
